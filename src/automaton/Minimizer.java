/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automaton;

import complement.ColorfulMessage;
import static complement.ColorfulMessage.Color.printLog;
import static complement.IndexFinder.getStringIndex;

/**
 *
 * @author Pedro
 */
public class Minimizer extends Automaton {

    private Status[][] RelationsTable;

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

    //Minimização Passo a Passo.
    public boolean minimizeStep() {
        if (!canMinimize()) {
            return false;
        }
        switch (stage) {
            case relationTable -> {
                printLog("Getting Relations Table...");
                getTableFromAutomaton();
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

    // Minimização Total.
    public boolean minimize() {
        if (!canMinimize()) {
            return false;
        }

        printLog("Starting Automaton Minimization...");
        getTableFromAutomaton();
        checkEquivalence();
        checkEquivalenceWithInputs();

        return true;
    }

    // Verificação de estados não equivalentes (estados vazios).
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

    // Verificação de estados obtidos para entradas T = 1 e T = 2 são equivalentes.
    private boolean checkEquivalenceWithInputs() {
        if (stage != Automaton.Stage.checkInputs) {
            printLog("Error! Unable to Check Inputs.", ColorfulMessage.Color.red);
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

    // Criação da Tabela de Relações (equivalência).
    private void getTableFromAutomaton() {
        if (stage == Automaton.Stage.relationTable) {
            RelationsTable = new Status[n - 1][];
            for (int i = 0; i < (n - 1); i++) {
                RelationsTable[i] = new Status[i + 1];
            }

            // Preenchimento da Tabela com valores indefinidos.
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

    // Verificar se um autômato pode ser minimizado.
    private boolean canMinimize() {
        if (!isDeterministic()) {
            printLog("Error! Unable to Minimize (Non-Deterministic)", ColorfulMessage.Color.red);
            return false;
        } else if (!isConnected()) {
            printLog("Error! Unable to Minimize (Not Connected)", ColorfulMessage.Color.red);
            return false;
        } else if (stage == Stage.file || stage == Stage.minimized) {
            return false;
        }
        return true;
    }

    public Status[][] getRelationsTable() {
        return RelationsTable;
    }
}
