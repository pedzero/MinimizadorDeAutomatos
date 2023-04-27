package complement;

/**
 * Implementação para encontrar índices em diferentes vetores (diferentes
 * tipos).
 *
 * @author Pedro
 */
public abstract class IndexFinder {

    /**
     * Encontra índice em vetor do tipo char.
     *
     * @param v Vetor onde o elemento será buscado.
     * @param value Elemento a ser buscado no vetor.
     * @return índice da primeira ocorrência do elemento no vetor, ou -1 caso
     * não exista.
     */
    public static int getCharIndex(char[] v, char value) {
        for (int i = 0; i < v.length; i++) {
            if (v[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Encontra índice em vetor do tipo String.
     *
     * @param v Vetor onde o elemento será buscado.
     * @param value Elemento a ser buscado no vetor.
     * @return índice da primeira ocorrência do elemento no vetor, ou -1 caso
     * não exista.
     */
    public static int getStringIndex(String[] v, String value) {
        for (int i = 0; i < v.length; i++) {
            if (v[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }
}
