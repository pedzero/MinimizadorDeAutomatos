package automaton;

import java.io.*;
import complement.ColorfulMessage.Color;
import static complement.ColorfulMessage.Color.printLog;
import static complement.IndexFinder.*;
import java.util.Arrays;

/**
 * Esta classe implementa uma estrutura básica para a representação de um
 * Autômato. O padrão utilizado favorece Autômatos determinísticos e conexos
 * (AFDs).
 *
 * @author Pedro
 */
public abstract class Automaton {

    protected int n, nSymbols;
    protected char[] symbols;
    protected String initial;
    protected String[] accepting;
    protected String[] states;
    protected String[][] transitions;
    protected Stage stage;
    protected boolean nonDeterministic, unconnected;

    /**
     * Stage descreve em qual estado o Autômato se encontra com com ênfase nos
     * estágios da minimização de autômato. Implementado para suportar
     * minimização passo a passo e checagem de erros.
     */
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

    /**
     * Leitura de um arquivo padronizado para armazenar o autômato no objeto
     * instanciado.
     *
     * @return true para leitura bem sucedida, false caso contrário.
     */
    public boolean readAutomaton(String filePath) throws IOException {

        printLog("Reading file from '" + filePath + "'...");
        try (BufferedReader buffRead = new BufferedReader(new FileReader(filePath))) {

            String linha;
            short cont = -1;

            while (true) {

                linha = buffRead.readLine();
                if (linha == null) {
                    break;
                }
                if ((cont == 2) && (!linha.contains(">"))) {
                    cont--;
                }
                cont++;

                String[] aux = linha.split(",");
                switch (cont) {
                    case 0 -> {
                        n = aux.length;
                        states = new String[n];
                        System.arraycopy(aux, 0, states, 0, n);
                        continue;
                    }

                    case 1 -> {
                        nSymbols = aux.length;
                        symbols = new char[nSymbols];

                        for (int i = 0; i < nSymbols; i++) {
                            symbols[i] = aux[i].charAt(0);
                        }
                        continue;
                    }

                    case 2 -> {
                        if (transitions == null) {
                            transitions = new String[n][nSymbols];
                        }

                        int origin = getStringIndex(states, aux[0]);

                        if (transitions[origin][getCharIndex(symbols, aux[1].charAt(0))] != null) {
                            nonDeterministic = true;
                            return false;
                        }
                        transitions[origin][getCharIndex(symbols, aux[1].charAt(0))] = aux[2];
                        continue;
                    }

                    case 3 -> {
                        initial = aux[0].substring(1);
                        continue;
                    }

                    case 4 -> {
                        accepting = new String[aux.length];

                        for (int i = 0; i < aux.length; i++) {

                            if (i == 0) {
                                accepting[i] = aux[i].substring(1);
                                continue;
                            }
                            accepting[i] = aux[i];
                        }
                    }
                }
            }

            if (cont != 4) {
                printLog("Error! (Selected file cannot be read)", Color.red);
                return false;
            }
        }
        stage = stage.next();
        return true;
    }

    protected boolean stateIsFinal(String state) {
        for (String s : accepting) {
            if (state.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public int getNumberOfStates() {
        return n;
    }

    public int getNumberOfSymbols() {
        return nSymbols;
    }

    public boolean isInitial(int index) {
        return initial.equals(states[index]);
    }

    public Stage getStage() {
        return stage;
    }

    public String[][] getTransitions() {
        return transitions;
    }

    /**
     * Criação de um vetor que contém os estados com símbolos especiais, que
     * indicam se um estado é inicial/final.
     *
     * @return Sequência de estados (String) padronizados.
     */
    public String[] getStates() {
        String[] completeStates = new String[n];
        for (int i = 0; i < n; i++) {
            if (initial.equals(states[i]) && stateIsFinal(states[i])) {
                completeStates[i] = "\u2B52" + "\u25B8" + states[i];
            } else if (initial.equals(states[i])) {
                completeStates[i] = "\u25B8" + states[i];
            } else if (stateIsFinal(states[i])) {
                completeStates[i] = "\u2B52" + states[i];
            } else {
                completeStates[i] = states[i];
            }
        }
        return completeStates;
    }

    public boolean isDeterministic() {
        return !nonDeterministic;
    }

    public boolean isConnected() {
        return !unconnected;
    }

    /**
     * Tradução do Autômato para uma String em HTML. Cria uma tabela que combina
     * todas as informações essenciais do Autômato.
     *
     * @return Uma String que representa um código HTML.
     */
    public String automatonToHTML() {
        StringBuilder str = new StringBuilder();

        str.append("<style>\n");
        str.append("th, td {\n");
        str.append("    border: 1px solid black;\n");
        str.append("    padding: 4px;\n");
        str.append("    text-align: center;\n");
        str.append("}\n");
        str.append("th {\n");
        str.append("    font-weight: bold;\n");
        str.append("}\n");
        str.append("</style>\n");
        str.append("<table>\n");
        str.append("<tr><th>Estados</th><td>");
        str.append(Arrays.toString(states));
        str.append("</td></tr>\n");
        str.append("<tr><th>Inicial</th><td>");
        str.append(initial);
        str.append("</td></tr>\n");
        str.append("<tr><th>Finais</th><td>");
        str.append(Arrays.toString(accepting));
        str.append("</td></tr>\n");
        str.append("<tr><th>AFD</th><td>");
        str.append(isDeterministic() ? "Sim" : "Não");
        str.append("</td></tr>\n");
        str.append("<tr><th>Conexo</th><td>");
        str.append(isConnected() ? "Sim" : "Não");
        str.append("</td></tr>\n");
        str.append("<tr><th>Transições</th><td>\n");
        str.append("<table>\n");

        str.append("<tr><th></th>");
        for (int i = 0; i < nSymbols; i++) {
            str.append("<th>").append(symbols[i]).append("</th>");
        }
        str.append("</tr>\n");

        for (int i = 0; i < n; i++) {
            str.append("<tr><td>").append(states[i]).append("</td>");
            for (int j = 0; j < nSymbols; j++) {
                str.append("<td>").append(transitions[i][j]).append("</td>");
            }
            str.append("</tr>\n");
        }
        str.append("</table></td></tr>\n");
        str.append("</table>\n");

        return str.toString();
    }
}
