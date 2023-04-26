/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automaton;

import java.io.*;
import complement.ColorfulMessage.Color;
import static complement.ColorfulMessage.Color.printLog;
import static complement.IndexFinder.*;

/**
 *
 * @author Pedro
 */
public abstract class Automaton {

    protected int n, nSimb;
    protected char[] symbols;
    protected String initial;
    protected String[] accepting;
    protected String[] states;
    protected String[][] transitions;
    protected Stage stage;
    protected boolean nonDeterministic, unconnected;

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
                        nSimb = aux.length;
                        symbols = new char[nSimb];

                        for (int i = 0; i < nSimb; i++) {
                            symbols[i] = aux[i].charAt(0);
                        }
                        continue;
                    }

                    case 2 -> {
                        if (transitions == null) {
                            transitions = new String[n][nSimb];
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

    public Stage getStage() {
        return stage;
    }

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
}
