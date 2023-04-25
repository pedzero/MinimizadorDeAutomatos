package complement;

public abstract class IndexFinder {
    // Encontrar índice em vetor de caracteres.
    public static int getCharIndex(char[] v, char value) {
        for (int i = 0; i < v.length; i++) {
            if (v[i] == value) {
                return i;
            }
        }
        return -1;
    }

    // Encontrar índice em vetor de Strings
    public static int getStringIndex(String[] v, String value) {
        for (int i = 0; i < v.length; i++) {
            if (v[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }
}
