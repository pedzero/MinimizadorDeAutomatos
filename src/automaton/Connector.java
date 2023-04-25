/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automaton;

import java.util.Queue;
import java.util.LinkedList;

import static complement.IndexFinder.getStringIndex;

/**
 *
 * @author Pedro
 */
public class Connector extends Automaton {

    private char[][] redundantTransitions;

    public boolean checkConnectivity() {
        if (unconnected == true) {
            return false;
        }

        boolean[] connected = BFS();
        unconnected = false;

        for (int i = 0; i < connected.length; i++) {
            if ((!connected[i]) && (!states[i].equals(initial))) {
                unconnected = true;
                break;
            }
        }
        return !unconnected;
    }

    private boolean[] BFS() {

        Queue<String> queue;
        boolean[] reached, connected;
        createRedundantTransitions();

        queue = new LinkedList<>();
        reached = new boolean[n];
        connected = new boolean[n];

        String currentState = initial;
        int index = getStringIndex(states, currentState);

        queue.add(currentState);
        reached[index] = true;
        while (!queue.isEmpty()) {
            currentState = queue.remove();
            index = getStringIndex(states, currentState);

            for (int j = 0; j < n; j++) {
                if (redundantTransitions[index][j] != '\0' && reached[j] == false) {
                    reached[j] = true;
                    connected[j] = true;
                    currentState = states[j];
                    queue.add(currentState);
                }
            }
        }
        return connected;
    }

    private void createRedundantTransitions() {
        redundantTransitions = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < nSimb; j++) {
                if (transitions[i][j] != null) {
                    int redundantIndex = getStringIndex(states, transitions[i][j]);
                    redundantTransitions[i][redundantIndex] = symbols[j];
                }
            }
        }
    }

}
