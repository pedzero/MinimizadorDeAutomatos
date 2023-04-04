package minimizer;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Minimizer {

    public static void main(String[] args) throws IOException {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        String filePath = "./automaton/auto3.dat";
        Automaton a = new Automaton();

        a.getAutomato(filePath);
        a.minimize();
  
        a.printRelationsTable();
    }
}
