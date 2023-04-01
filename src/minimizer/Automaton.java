package minimizer;

import java.io.*;
import java.util.Arrays;

public class Automaton {

    private int n, nSimb;
    private char[] symbols;
    private char[][] transitions;
    private String[] states;
    private String initial;
    private String[] accepting;
    private State[][] RTable;
    private boolean nonDeterministic, unconnected;
    private enum State {
        Equivalent,
        possiblyEquivalent,
        notEquivalent
    }

    // Encontrar índice em vetor de caracteres.
    private static int getIndex(char[] vet, char valor) {
        for (int i = 0; i < vet.length; i++) {
            if (vet[i] == valor) {
                return i;
            }
        }
        return -1;
    }

    // Ler autômato de arquivo e armazenar no programa.
    public boolean getAutomato(String filePath) throws IOException {
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
                            transitions = new char[n][nSimb];
                        }

                        // Define o estado de origem, símbolo e destino.
                        int origem = Character.getNumericValue(aux[0].charAt(1));
                        // Caso exista algum estado com múltiplas transições (mesmo símbolo).
                        if (transitions[origem][getIndex(symbols, aux[1].charAt(0))] != 0) {
                            nonDeterministic = true;
                            return false;
                        }
                        transitions[origem][getIndex(symbols, aux[1].charAt(0))] = aux[2].charAt(1);
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
                return false;
            }
        }
        return true;
    }
    
    // Criação da Tabela de Relações (equivalência).
    public void getTableFromAutomaton() {
        RTable = new State[n - 1][];
        for (int i = 0; i < (n - 1); i++) {
            RTable[i] = new State[i + 1];
        }
    }
    
    // Verifica se um estado é final.
    private boolean stateIsFinal(String state) {
        for (String s : accepting) {
            if (state.equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    // Verifica os estados não equivalentes (estados vazios).
    public void checkNonEquivalence() {
        for (int i = 0; i < (n - 1); i++) {
            for (int j = 0; j < (i + 1); j++) {
                if ((stateIsFinal(states[i + 1])) != (stateIsFinal(states[j]))) {
                    RTable[i][j] = State.notEquivalent;
                } else {
                    RTable[i][j] = State.possiblyEquivalent;
                }
            }
        }
    }

//    public void printData() {
//        System.out.println("Tam: " + n + " TamSimb: " + nSimb);
//        System.out.println("Symbols" + Arrays.toString(symbols));
//        System.out.println("Initial: " + initial);
//        System.out.println("Finals: " + Arrays.toString(accepting));
//    }
//
//    public void printRTable() {
//        for (int i = 0; i < (n - 1); i++) {
//            for (int j = 0; j < (i + 1); j++) {
//                System.out.print(RTable[i][j] + "\t");
//            }
//            System.out.println("");
//        }
//    }

    public boolean isDeterministic() {
        return !nonDeterministic;
    }

    public boolean isConnected() {
        return !unconnected;
    }
}