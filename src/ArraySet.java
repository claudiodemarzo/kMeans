import java.util.Arrays;

/**
 * ArraySet.java
 * <p>
 * Classe utilitaria che implementa un insieme di interi tramite un array di booleani.
 * Una volta istanziata la classe, vi è all'interno un array di booleani ed un intero che rappresenta la dimensione dell'insieme.
 * È possibile aggiungere, rimuovere, controllare la presenza di un elemento e ottenere la dimensione dell'insieme.
 * È possibile inoltre convertire l'oggetto in questione in un int[] tramite il metodo toArray(), ed utilizzare tale array per indicizzare il dataset.
 *
 * @author Dott.ssa Appice A.
 */
public class ArraySet {
    /**
     * Array di booleani. Se set[i] == true, allora l'insieme contiene l'elemento i.
     */
    private boolean[] set;

    /**
     * Dimensione dell'insieme. Corrisponde all'ultimo indice dell'array set in cui è presente un elemento.
     */
    private int size = 0;

    /**
     * Costruttore di default. Inizializza l'array di boolean a 50 elementi, tutti false.
     */
    ArraySet() {
        set = new boolean[50];
        for (int i = 0; i < set.length; i++)
            set[i] = false;
    }

    /**
     *  Aggiunge un indice, impostando a true l'elemento set[i]. Se l'indice è maggiore della dimensione dell'insieme, allora l'insieme viene ingrandito.
     *
     * @param i l'indice da aggiungere all'insieme
     * @return true se aggiungendo l'elemento si varia lo stato dell'oggetto ArraySet, false altrimenti
     */
    boolean add(int i) {
        if (i >= set.length) {
            //enlarge the set
            boolean[] temp = new boolean[set.length * 2];
            Arrays.fill(temp, false);
            System.arraycopy(set, 0, temp, 0, set.length);
            set = temp;
        }
        boolean added = set[i];
        set[i] = true;
        if (i >= size)
            size = i + 1;
        return !added;


    }

    /**
     *  Rimuove un indice, impostando a false l'elemento set[i].
     *
     * @param i L'indice da rimuovere dall'insieme
     * @return true se rimuovendo l'elemento si varia lo stato dell'oggetto ArraySet, false altrimenti
     */
    boolean delete(int i) {
        if (i < size) {
            boolean deleted = set[i];
            set[i] = false;
            if (i == size - 1) {
                //update size
                int j;
                for (j = size - 1; j >= 0 && !set[j]; j--) ;

                size = j + 1;
            }

            return deleted;
        }
        return false;
    }

    /**
     * Controlla se l'insieme contiene l'indice i.
     * @param i L'indice da controllare
     * @return true se l'insieme contiene l'indice i, false altrimenti
     */
    boolean get(int i) {
        return set[i];
    }

    /**
     * Converte l'oggetto ArraySet in un array di interi, contenente gli indici degli elementi dell'insieme, ovvero le posizioni dell'array che sono state settate a true.
     *
     * @return L'ArraySet convertito in int[]
     */

    int[] toArray() {
        int a[] = new int[0];
        for (int i = 0; i < size; i++) {
            if (get(i)) {
                int[] temp = new int[a.length + 1];
                System.arraycopy(a, 0, temp, 0, a.length);
                a = temp;
                a[a.length - 1] = i;
            }
        }
        return a;
    }

    /**
     * Restituisce la dimensione dell'insieme.
     * @return La dimensione dell'insieme
     */

    int size() {
        return size;
    }
}
