package minimizador;

import java.io.IOException;

public class Minimizador {

    public static void main(String[] args) throws IOException {
        String filePath = "./auto.dat";
        Automato a = new Automato();

        System.out.println(a.getAutomato(filePath));
    }

}
