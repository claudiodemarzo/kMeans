package data;

import java.io.Serializable;

/**
 * data.Tuple.java
 * <p>
 * Ckasse che modella una Tupla, una sequenza di coppie Attributo-Valore
 *
 * @see Item
 * @see DiscreteItem
 * @author Claudio De Marzo
 */
public class Tuple implements Serializable {

    /**
     * Array di data.Item che rappresenta la tupla
     */
    private final Item[] tuple;

    /**
     * Costruttore della classe data.Tuple
     * @param size la dimensione della tupla
     */

    Tuple(int size) {
        tuple = new Item[size];
    }

    /**
     * Restituisce la dimensione della tupla
     * @return la dimensione della tupla
     */

    public int getLength() {
        return tuple.length;
    }

    /**
     * Restituisce l'i-esimo item della tupla
     * @param i l'indice dell'item da restituire
     * @return l'i-esimo item della tupla
     */

    public Item get(int i) {
        return tuple[i];
    }

    /**
     * Aggiunge un item alla tupla
     * @param c l'item da aggiungere
     * @param i l'indice dell'item da aggiungere
     */

    void add(Item c, int i) {
        tuple[i] = c;
    }

    /**
     * Restituisce la distanza tra due tuple, calcolata come la somma delle distanze tra i singoli item
     * @param obj la tupla rispetto alla quale calcolare la distanza
     * @return la distanza tra le due tuple
     */

    public double getDistance(Tuple obj) {
        double distance = 0;
        for (int i = 0; i < tuple.length; i++) {
            distance += get(i).distance(obj.get(i).getValue());
        }
        return distance;
    }

    /**
     * Restituisce la media delle distanze tra la tupla corrente ed un insieme di tuple
     * @param data il dataset contenente tutte le tuple
     * @param clusteredData l'insieme di tuple rispetto alle quali calcolare la distanza
     * @return la media delle distanze
     */
    public double avgDistance(Data data, Integer[] clusteredData) {
        double p, sumD = 0.0;
        for (int clusteredDatum : clusteredData) {
            double d = getDistance(data.getItemSet(clusteredDatum));
            sumD += d;
        }
        p = clusteredData.length == 0 ? 0 : sumD / clusteredData.length;
        return p;
    }

    /**
     * Restituisce una rappresentazione testuale della tupla
     * @return la rappresentazione in String della tupla
     */
    @Override
    public String toString() {
        String s = "";
        for (Item i : tuple) {
            s += i.toString();
        }
        return s;
    }
}
