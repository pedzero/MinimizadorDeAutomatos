package minimizador;

import java.io.IOException;

public class Minimizador {

    public static void main(String[] args) throws IOException {
        String filePath = "C:/Users/Pedro/Downloads/NetBeansProjects/NetBeansProjects/auto1.dat";
        Automato a = new Automato();
        
        a.getAutomato(filePath);
    }
    
}
