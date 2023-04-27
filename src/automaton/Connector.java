package automaton;

import static complement.ColorfulMessage.Color.printLog;
import java.util.Queue;
import java.util.LinkedList;

import static complement.IndexFinder.getStringIndex;

/**
 * Extende as possibilidade da classe Automaton, implementando métodos para
 * verificação de um Autômato conexo.
 *
 * @author Pedro
 */
public class Connector extends Automaton {

    private char[][] completeTransitions;
    private boolean[] connected;

    /**
     * Verifica se o objeto instanciado é conexo.
     *
     * @return true para Autômato conexo, false caso contrário.
     */
    public boolean checkConnectivity() {
        if (unconnected == true) {
            return false;
        }

        boolean[] conections = BFS();
        unconnected = false;

        for (int i = 0; i < conections.length; i++) {
            if ((!conections[i]) && (!states[i].equals(initial))) {
                unconnected = true;
                break;
            }
        }
        return !unconnected;
    }

    /**
     * Getter para os estados conexos do Autômato.
     *
     * @return Vetor com estados alcançáveis (verificação por meio do índice).
     */
    public boolean[] getConnections() {
        if (connected == null) {
            return BFS();
        }
        return connected;
    }

    /**
     * Algoritmo Breadth-First-Search genérico para verificação de conectividade
     * entre estados.
     *
     * @return Vetor com estados alcançáveis (verificação por meio do índice).
     */
    private boolean[] BFS() {

        createCompleteTransitions();

        printLog("Checking Connectivity...");

        Queue<String> queue;
        String currentState;
        int index;
        boolean[] reached;

        queue = new LinkedList<>();
        reached = new boolean[n];
        connected = new boolean[n];
        currentState = initial;
        index = getStringIndex(states, currentState);

        queue.add(currentState);
        reached[index] = true;
        while (!queue.isEmpty()) {
            currentState = queue.remove();
            index = getStringIndex(states, currentState);

            for (int j = 0; j < n; j++) {
                if (completeTransitions[index][j] != '\0' && reached[j] == false) {
                    reached[j] = true;
                    connected[j] = true;
                    currentState = states[j];
                    queue.add(currentState);
                }
            }
        }
        return connected;
    }

    /**
     * Criação de uma tabela com dimensões estados por estados, onde cada valor
     * é o símbolo correspondente à transição entre esses estados. Necessário
     * por conta da implementação do BFS.
     */
    private void createCompleteTransitions() {

        printLog("Creating new Transitions Table...");
        completeTransitions = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < nSymbols; j++) {
                if (transitions[i][j] != null) {
                    int redundantIndex = getStringIndex(states, transitions[i][j]);
                    completeTransitions[i][redundantIndex] = symbols[j];
                }
            }
        }
    }
}
