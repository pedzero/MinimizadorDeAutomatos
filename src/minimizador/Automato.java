package minimizador;

import java.io.*;

public class Automato {
    
    private int n, nSimb;
    private char[][] transicoes;
    private char[] simbolos;
    private char inicial;
    private char[] finais;
    private boolean nonDeterministic, unconnected;
    
    // Método para encontrar caractere em um vetor.
    private static int getPosVetor(char[] vet, char valor) {
        for (int i = 0; i < vet.length; i++) {
            if (vet[i] == valor) {
                return i;
            }
        }
        return -1;
    }
    
    // Método para ler arquivo e armazenar o automato.
    public boolean getAutomato(String filePath) throws IOException {
        try (BufferedReader buffRead = new BufferedReader(new FileReader(filePath))) {

            String linha;
            short cont = -1;

            while (true) {

                linha = buffRead.readLine();
                if (linha == null) {
                    break;
                }
                // reduz a contagem de linha para as linhas de transição.
                if ((cont == 2) && (!linha.contains(">"))) {
                    cont--;
                }
                cont++;
                
                // Divisão da linha em um vetor de String.
                String[] aux = linha.split(",");
                switch (cont) {
                    case 0 -> { // Pega tamanho (número de estados).
                        n = aux.length;
                        continue;
                    }

                    case 1 -> { // Pega número de símbolos.
                        nSimb = aux.length;
                        simbolos = new char[nSimb];

                        for (int i = 0; i < nSimb; i++) {
                            simbolos[i] = aux[i].charAt(0);
                        }
                        continue;
                    }

                    case 2 -> { // Pega transições.
                        if (transicoes == null) {
                            transicoes = new char[n][nSimb];
                        }
                        
                        // Define o estado de origem, símbolo e destino.
                        int origem = Character.getNumericValue(aux[0].charAt(1));
                        // Caso exista algum estado com múltiplas transições (mesmo símbolo).
                        if (transicoes[origem][getPosVetor(simbolos, aux[1].charAt(0))] != 0) {
                            nonDeterministic = true;
                            return false;
                        }
                        transicoes[origem][getPosVetor(simbolos, aux[1].charAt(0))] = aux[2].charAt(1);
                        continue;
                    }

                    case 3 -> { // Pega estado inicial.
                        inicial = aux[0].charAt(2);
                        continue;
                    }

                    case 4 -> { // Pega estados finais.
                        finais = new char[aux.length];

                        for (int i = 0; i < aux.length; i++) {
                            // Eliminação do '*' no primeiro estado final.
                            if (i == 0) {
                                finais[i] = aux[i].charAt(2);
                                continue;
                            }
                            finais[i] = aux[i].charAt(1);
                        }
                    }
                }
            }
            // Caso falte algo no arquivo.
            if (cont != 4) { 
                return false;
            }
        }
        return true;
    }
    
    public boolean deterministic(){
        return !nonDeterministic;
    }
    
    public boolean connected(){
        return !unconnected;
    }
}