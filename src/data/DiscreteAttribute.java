package data;

import java.util.*;

/**
 * DiscreteAttribute.java
 * <p>
 * Classe che rappresenta un attributo discreto (categorico). Questa classe estende la classe astratta Attribute e include metodi per la gestione dei valori possibili dell'attributo.
 *
 * @author Claudio De Marzo
 * @see Attribute
 */
class DiscreteAttribute extends Attribute implements Iterable<String> {

    /**
     * Valori possibili dell'attributo
     */
    private final TreeSet<String> values;

    /**
     * Costruttore della classe data.DiscreteAttribute
     *
     * @param name      Il nome dell'attributo
     * @param index     L'indice dell'attributo
     * @param valuesArr I valori possibili dell'attributo
     */
    DiscreteAttribute(String name, int index, String[] valuesArr) {
        super(name, index);
        values = new TreeSet<>();
        values.addAll(Arrays.asList(valuesArr));
    }

    /**
     * Restituisce il numero di valori possibili dell'attributo (lunghezza dell'array values)
     *
     * @return Il numero di valori possibili dell'attributo
     */
    int getNumberOfDistinctValues() {
        return values.size();
    }

    /**
     * Restituisce il numero di volte che un determinato valore compare in un insieme di esempi rispetto all'attributo corrente.
     *
     * @param data   il dataset sul quale contare le occorrenze
     * @param idList l'insieme di esempi sul quale contare le occorrenze
     * @param v      il valore di cui contare le occorrenze
     * @return il numero di volte che il valore v compare nell'insieme di esempi idList rispetto all'attributo corrente
     */

    int frequency(Data data, Set<Integer> idList, String v) {
        int count = 0;
        for (int i : idList) {
            if (data.getAttributeValue(i, getIndex()).equals(v)) count++;
        }
        return count;
    }

    /**
     * Restituisce l'oggetto Iterator che permette di iterare sui valori possibili dell'attributo
     *
     * @return l'oggetto Iterator
     * @see Iterator
     */
    @Override
    public Iterator<String> iterator() {
        return values.iterator();
    }
}
