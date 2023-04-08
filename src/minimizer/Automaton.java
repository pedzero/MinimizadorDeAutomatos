package minimizer;

import java.io.*;
import java.util.Arrays;

public class Automaton {

    private int n, nSimb;
    private char[] symbols;
    private String[][] transitions;
    private String[] states;
    private String initial;
    private String[] accepting;
    private Status[][] RelationsTable;
    private Stage stage;
    private boolean nonDeterministic, unconnected;

    public enum Status {
        equivalent('\u25EF'),
        notEquivalent('\u2715'),
        denied('\u2A02'),
        undefined('\u2800');

        private final char UnicodeChar;

        Status(char unicode) {
            this.UnicodeChar = unicode;
        }

        public char getChar() {
            return UnicodeChar;
        }
    }

    public enum Stage {
        file,
        relationTable,
        checkEquivalence,
        checkInputs,
        minimized;

        public Stage next() {
            return values()[(ordinal() + 1) % values().length];
        }
    }

    public Automaton() {
        stage = Stage.file;
    }

    // Ler Autômato / Arquivo.
    public boolean getAutomato(String filePath) throws IOException {
        System.out.print("\u229E Reading file from '" + filePath + "'...");
        try (BufferedReader buffRead = new BufferedReader(new FileReader(filePath))) {

            String linha;
            short cont = -1;

            while (true) {

                linha = buffRead.readLine();
                if (linha == null) {
                    break;
                }
                // Reduz a contagem de linhas (linhas de transição).
                if ((cont == 2) && (!linha.contains(">"))) {
                    cont--;
                }
                cont++;

                // Divisão da linha em um vetor de Strings.
                String[] aux = linha.split(",");
                switch (cont) {
                    case 0 -> { // Leitura de estados.
                        n = aux.length;
                        states = new String[n];
                        System.arraycopy(aux, 0, states, 0, n);
                        continue;
                    }

                    case 1 -> { // Leitura do alfabeto.
                        nSimb = aux.length;
                        symbols = new char[nSimb];

                        for (int i = 0; i < nSimb; i++) {
                            symbols[i] = aux[i].charAt(0);
                        }
                        continue;
                    }

                    case 2 -> { // Leitura das Transições.
                        if (transitions == null) {
                            transitions = new String[n][nSimb];
                        }

                        // Define o estado de origem, símbolo e destino.
                        int origem = Character.getNumericValue(aux[0].charAt(1));
                        // Caso exista algum estado com múltiplas transições (mesmo símbolo).
                        if (transitions[origem][getCharIndex(symbols, aux[1].charAt(0))] != null) {
                            nonDeterministic = true;
                            return false;
                        }
                        transitions[origem][getCharIndex(symbols, aux[1].charAt(0))] = aux[2];
                        continue;
                    }

                    case 3 -> { // Leitura do estado inicial.
                        initial = aux[0].substring(1);
                        continue;
                    }

                    case 4 -> { // Pega estados finais.
                        accepting = new String[aux.length];

                        for (int i = 0; i < aux.length; i++) {
                            // Eliminação do '*' no primeiro estado final.
                            if (i == 0) {
                                accepting[i] = aux[i].substring(1);
                                continue;
                            }
                            accepting[i] = aux[i];
                        }
                    }
                }
            }
            // Caso de rejeição.
            if (cont != 4) {
                System.out.println("\tError! (Selected file cannot be read)");
                return false;
            }
        }
        System.out.println("\tOK");
        stage = stage.next();
        return true;
    }

    // Minimização Total.
    public boolean minimize() {
        if (!canMinimize()) {
            return false;
        }
        if (stage != Stage.minimized) {
            System.out.print("\u229E Starting Automaton Minimization...");
            getTableFromAutomaton();
            checkEquivalence();
            checkEquivalenceWithInputs();
            System.out.println("\tOK");
        }
        return true;
    }

    //Minimização Passo a Passo.
    public boolean minimizeStep() {
        if (!canMinimize()) {
            return false;
        }
        if (stage != Stage.minimized) {
            if (stage == Stage.relationTable) {
                System.out.print("\u229E Getting Relations Table...");
                getTableFromAutomaton();
            } else if (stage == Stage.checkEquivalence) {
                System.out.print("\u229E Checking Equivalence Between States...");
                checkEquivalence();
            } else if (stage == Stage.checkInputs) {
                System.out.print("\u229E Testing Inputs...");
                checkEquivalenceWithInputs();
            }
            System.out.println("\tOK");
        }
        return true;
    }

    // Criação da Tabela de Relações (equivalência).
    private void getTableFromAutomaton() {
        if (stage == Stage.relationTable) {
            RelationsTable = new Status[n - 1][];
            for (int i = 0; i < (n - 1); i++) {
                RelationsTable[i] = new Status[i + 1];
            }
            stage = stage.next();
            // Preenchimento da Tabela com valores indefinidos.
            for (int i = 0; i < (n - 1); i++) {
                for (int j = 0; j < (i + 1); j++) {
                    RelationsTable[i][j] = Status.undefined;
                }
            }
        } else {
            System.out.println("\u229E Error! Unable to Create Relations Table.");
        }
    }

    // Verificação de estados não equivalentes (estados vazios).
    private boolean checkEquivalence() {
        if (stage != Stage.checkEquivalence) {
            System.out.println("\u229E Error! Unable to Check Equivalence.");
            return false;
        }
        for (int i = 0; i < (n - 1); i++) {
            for (int j = 0; j < (i + 1); j++) {
                if ((stateIsFinal(states[i + 1])) != (stateIsFinal(states[j]))) {
                    RelationsTable[i][j] = Status.notEquivalent;
                } else {
                    RelationsTable[i][j] = Status.equivalent;
                }
            }
        }
        stage = stage.next();
        return true;
    }

    // Verificação de estados obtidos para entradas T = 1 e T = 2 são equivalentes.
    private boolean checkEquivalenceWithInputs() {
        if (stage != Stage.checkInputs) {
            System.out.println("\u229E Error! Unable to Check Inputs.");
            return false;
        }
        for (int i = 0; i < (n - 1); i++) {
            for (int j = 0; j < (i + 1); j++) {
                for (int k = 0; k < nSimb; k++) { // Teste para cada entrada possível.
                    if (RelationsTable[i][j] == Status.notEquivalent) { // Ignora estados não equivalentes.
                        continue;
                    }
                    String nextState1 = transitions[i + 1][k];
                    String nextState2 = transitions[j][k];

                    // Checagem para T = 1.
                    if (stateIsFinal(nextState1) != stateIsFinal(nextState2)) {
                        RelationsTable[i][j] = Status.denied;
                        break;
                    }
                    // Combinação de entradas para T = 2.
                    for (int l = 0; l < nSimb; l++) {
                        String newNextState1 = transitions[getStringIndex(states, nextState1)][l];
                        String newNextState2 = transitions[getStringIndex(states, nextState2)][l];

                        // Checagem para T = 2.
                        if (stateIsFinal(newNextState1) != stateIsFinal(newNextState2)) {
                            RelationsTable[i][j] = Status.denied;
                            break;
                        }
                    }
                }
            }
        }
        stage = stage.next();
        return true;
    }

    // Verificação de estado final.
    private boolean stateIsFinal(String state) {
        for (String s : accepting) {
            if (state.equals(s)) {
                return true;
            }
        }
        return false;
    }

    // Verificar se um autômato pode ser minimizado.
    private boolean canMinimize() {
        if (!isDeterministic()) {
            System.out.println("\u229E Error! Unable to Minimize (Non-Deterministic)");
            return false;
        } else if (!isConnected()) {
            System.out.println("\u229E Error! Unable to Minimize (Not Connected)");
            return false;
        } else if (stage == Stage.file) {
            return false;
        }
        return true;
    }

    public int getNumberOfStates() {
        return n;
    }

    public String[][] getTransitions() {
        return transitions;
    }

    // Correção dos nomes dos estados para os casos em que são iniciais/finais.
    public String[] getStates() {
        String[] completeStates = new String[n];
        for (int i = 0; i < n; i++) {
            if (initial.equals(states[i])) {
                completeStates[i] = "\u25B8" + states[i];
            } else if (stateIsFinal(states[i])) {
                completeStates[i] = "\u2B52" + states[i];
            } else {
                completeStates[i] = states[i];
            }
        }
        return completeStates;
    }

    public Status[][] getRelationsTable() {
        return RelationsTable;
    }

    public Stage getStage() {
        return stage;
    }

    // Retorna se é um AFD.
    public boolean isDeterministic() {
        return !nonDeterministic;
    }

    // Retorna se é um autômato conexo.
    public boolean isConnected() {
        return !unconnected;
    }

    // Encontrar índice em vetor de caracteres.
    private int getCharIndex(char[] v, char value) {
        for (int i = 0; i < v.length; i++) {
            if (v[i] == value) {
                return i;
            }
        }
        return -1;
    }

    // Encontrar índice em vetor de Strings
    private int getStringIndex(String[] v, String value) {
        for (int i = 0; i < v.length; i++) {
            if (v[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public void printData() {

        System.out.println("→N. of States.: " + n);
        System.out.println("→N. of Symbols: " + nSimb + " - " + Arrays.toString(symbols));
        System.out.println("→Initial State: \u25B8" + initial);
        System.out.println("→Final States.: \u2B52" + Arrays.toString(accepting));
        System.out.println("→Transitions..:");
        for (int i = 0; i < nSimb; i++) {
            System.out.print("\t" + symbols[i]);
        }
        System.out.println("");
        for (int i = 0; i < n; i++) {
            System.out.print(states[i]);
            for (int j = 0; j < nSimb; j++) {
                System.out.print("\t" + transitions[i][j]);
            }
            System.out.println("");
        }
        System.out.println("-----------------------");
    }

    public void printRelationsTable() {
        String[] completeStates = getStates();
        System.out.println("     →Eq. Relations Table←     ");
        for (int i = 0; i < (n - 1); i++) {
            System.out.print(" " + completeStates[i + 1]);
            for (int j = 0; j < (i + 1); j++) {
                System.out.print("\t" + RelationsTable[i][j].getChar());
            }
            System.out.println("");
        }
        for (int i = 0; i < (n - 1); i++) {
            System.out.print(" " + completeStates[i + 1]);
        }
        System.out.println("\n-------------------------------");
    }
}
