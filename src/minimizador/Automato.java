package minimizador;

import java.io.*;

public class Automato {

    public int n, nSimb;
    public char[][] transicoes;
    public char[] simbolos;
    public char inicial;
    public char[] finais;

    private static int getPosVetor(char[] vet, char valor) {
        for (int i = 0; i < vet.length; i++) {
            if (vet[i] == valor) {
                return i;
            }
        }
        return -1;
    }

    public boolean getAutomato(String filePath) throws IOException {
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
                        continue;
                    }

                    case 1 -> {
                        this.nSimb = aux.length;
                        simbolos = new char[this.nSimb];
                        
                        for (int i = 0; i < this.nSimb; i++) {
                            simbolos[i] = aux[i].charAt(0);
                        }
                        continue;
                    }

                    case 2 -> {
                        if (this.transicoes == null) {
                            this.transicoes = new char[this.n][this.nSimb];
                        }

                        int origem = Character.getNumericValue(aux[0].charAt(1));
                        this.transicoes[origem][getPosVetor(simbolos, aux[1].charAt(0))] = aux[2].charAt(1);
                        continue;
                    }

                    case 3 -> {
                        this.inicial = aux[0].charAt(2);
                        continue;
                    }

                    case 4 -> {
                        this.finais = new char[aux.length];
                        
                        for (int i = 0; i < aux.length; i++) {
                            if (i == 0) {
                                this.finais[i] = aux[i].charAt(2);
                                continue;
                            }
                            this.finais[i] = aux[i].charAt(1);
                            continue;
                        }
                    }
                }
            }
            if (cont != 4) {
                return false;
            }
        }
        return true;
    }
}

//class ManipuladorArquivo {
//
//    public static void leitor(String path) throws IOException {
//        BufferedReader buffRead = new BufferedReader(new FileReader(path));
//        String linha = "";
//        while (true) {
//            if (linha != null) {
//                System.out.println(linha);
//
//            } else {
//                break;
//            }
//            linha = buffRead.readLine();
//        }
//        buffRead.close();
//    }
//
//    public static void escritor(String path) throws IOException {
//        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
//        String linha = "";
//        Scanner in = new Scanner(System.in);
//        System.out.println("Escreva algo: ");
//        linha = in.nextLine();
//        buffWrite.append(linha + "\n");
//        buffWrite.close();
//    }
//
//}
