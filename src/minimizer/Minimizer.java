package minimizer;

import java.io.IOException;

public class Minimizer {

    public static void main(String[] args) throws IOException {
        String filePath = "./auto.dat";
        Automaton a = new Automaton();

        System.out.println(a.getAutomato(filePath));
        
        a.getTableFromAutomaton();
        a.checkNonEquivalence();
//        a.printData();
//        a.printRTable();
    }

}
