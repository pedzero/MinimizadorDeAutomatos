package minimizer;

import java.io.IOException;

public class Minimizer {

    public static void main(String[] args) throws IOException {
        String filePath = "./automaton/auto2.dat";
        Automaton a = new Automaton();

        System.out.println(a.getAutomato(filePath));
        
        a.getTableFromAutomaton();
        a.checkEquivalence();
        a.printData();
        System.out.println("");
        a.printRTable();
        System.out.println("");
        a.checkInputs();
        a.printRTable();
    }

}
