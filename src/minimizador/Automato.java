package minimizador;

import java.io.*;
import java.util.Scanner;

class Estado {

    public int id;
}

class Transicao {

    public int id;
    public int ant;
    public int next;
}

public class Automato {

    public int n;
    public String simbolos[];
    public Transicao transicoes[];
    public int inicial;
    public int finais[];
    public Estado estados[];

    public Automato() {
        this.n = 0;
    }

    public void getAutomato(String filePath) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(filePath));
        String linha = "";
        this.n = 0;
        while (linha != null) {
            String[] aux = linha.split(",");
            if(this.n == 0) {
                
            }
            n = aux.length;
                for (int i = 0; i < n; i++) {
                    System.out.println(aux[i]);
                }
            

            linha = buffRead.readLine();
        }
        buffRead.close();
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
