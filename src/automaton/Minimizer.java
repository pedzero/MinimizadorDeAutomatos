package automaton;

import complement.ColorfulMessage;
import static complement.ColorfulMessage.Color.printLog;
import static complement.IndexFinder.getStringIndex;

/**
 * Extende as possibilidade da classe Automaton, implementando métodos para
 * minimização de um Autômato.
 *
 * @author Pedro
 */
public class Minimizer extends Automaton {

    private Status[][] RelationsTable;

    /**
     * Valores correspondentes aos estados possíveis em cada relação de
     * equivalência. Importante para diversificar o uso e melhorar o mapeamento
     * relação - caracter usado.
     */
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

    /**
     * Etapa da minimização de um Autômato. A cada chamada do método, uma nova
     * etapa da minimização é feita.
     *
     * @return true para passo bem sucedido, false caso contrário.
     */
    public boolean minimizeStep() {
        if (!canMinimize()) {
            return false;
        }
        switch (stage) {
            case relationTable -> {
                printLog("Getting Relations Table...");
                generateRelationsTable();
            }
            case checkEquivalence -> {
                printLog("Checking Equivalence Between States...");
                checkEquivalence();
            }
            case checkInputs -> {
                printLog("Testing Inputs...");
                checkEquivalenceWithInputs();
            }
        }
        return true;
    }

    /**
     * Processo de minimização de um Autômato completo.
     *
     * @return true para minimização bem sucedida, false caso contrário.
     */
    public boolean minimize() {
        if (!canMinimize()) {
            return false;
        }

        printLog("Starting Automaton Minimization...");
        generateRelationsTable();
        checkEquivalence();
        checkEquivalenceWithInputs();

        return true;
    }

    /**
     * Verificação de estados equivalentes (estados vazios).
     *
     * @return true para verificação bem sucedida, false caso contrário.
     */
    private boolean checkEquivalence() {
        if (stage != Automaton.Stage.checkEquivalence) {
            printLog("Error! Unable to Check Equivalence.", ColorfulMessage.Color.red);
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

    /**
     * Verificação de equivalência entre estados por meio do teste de entradas.
     * Implementação para combinação de uma e duas entradas. (conjunto de
     * entradas)
     *
     * @return true para verificação bem sucedida, false caso contrário.
     */
    private boolean checkEquivalenceWithInputs() {
        if (stage != Automaton.Stage.checkInputs) {
            printLog("Error! Unable to Check Inputs.", ColorfulMessage.Color.red);
            return false;
        }
        for (int i = 0; i < (n - 1); i++) {
            for (int j = 0; j < (i + 1); j++) {
                for (int k = 0; k < nSymbols; k++) {
                    if (RelationsTable[i][j] == Status.notEquivalent) {
                        continue;
                    }
                    String nextState1 = transitions[i + 1][k];
                    String nextState2 = transitions[j][k];

                    if (stateIsFinal(nextState1) != stateIsFinal(nextState2)) {
                        RelationsTable[i][j] = Status.denied;
                        break;
                    }

                    for (int l = 0; l < nSymbols; l++) {
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

    /**
     * Criação da tabela de relações de equivalência. A tabela criada respeita
     * os limites, ou seja, possui apenas o tamanho necessário de acordo com o
     * Autômato.
     */
    private void generateRelationsTable() {
        if (stage == Automaton.Stage.relationTable) {
            RelationsTable = new Status[n - 1][];
            for (int i = 0; i < (n - 1); i++) {
                RelationsTable[i] = new Status[i + 1];
            }

            for (int i = 0; i < (n - 1); i++) {
                for (int j = 0; j < (i + 1); j++) {
                    RelationsTable[i][j] = Status.undefined;
                }
            }
            stage = stage.next();
        } else {
            printLog("Error! Unable to Create Relations Table.", ColorfulMessage.Color.red);
        }
    }

    /**
     * Verificação se um Autômato é minimizável.
     *
     * @return true para minimizável, false caso contrário.
     */
    private boolean canMinimize() {
        if (!isDeterministic()) {
            printLog("Error! Unable to Minimize (Non-Deterministic)", ColorfulMessage.Color.red);
            return false;
        } else if (!checkTransitions()) {
            printLog("Error! One or more states have no transitions", ColorfulMessage.Color.red);
            return false;
        } else if (stage == Stage.file || stage == Stage.minimized) {
            return false;
        }
        return true;
    }

    /**
     * Checagem para ausência de transições no Autômato. Ele é considerado não
     * Minimizável.
     *
     * @return true para minimizável, false caso contrário.
     */
    private boolean checkTransitions() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < nSymbols; j++) {
                if (transitions[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public Status[][] getRelationsTable() {
        return RelationsTable;
    }
}
