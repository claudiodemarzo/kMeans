package data;

import java.util.HashSet;
import java.util.Set;

/**
 * data.Item.java
 * <p>
 * Classe che modella una coppia generica attributo-valore
 *
 * @author Claudio De Marzo
 */
public abstract class Item {
    /**
     * Attributo dell'item
     */
    private final Attribute attribute;

    /**
     * Valore dell'item
     */
    private Object value;

    /**
     * Costruttore della classe data.Item
     * @param attribute attributo dell'item
     * @param value valore dell'item
     */

    Item(Attribute attribute, Object value) {
        this.attribute = attribute;
        this.value = value;
    }

    /**
     * Restituisce l'attributo dell'item
     * @return l'attributo dell'item
     */

    Attribute getAttribute() {
        return attribute;
    }

    /**
     * Restituisce il valore dell'item
     * @return il valore dell'item
     */
    Object getValue() {
        return value;
    }

    /**
     * Restituisce una rappresentazione testuale dell'item
     * @return una rappresentazione testuale dell'item, che coincide con il valore dell'item
     */

    @Override
    public String toString() {
        return value.toString();
    }

    /**
     * Restituisce la distanza tra due item
     * @param a l'oggetto rispetto al quale calcolare la distanza
     * @return la distanza tra i due item
     */

    abstract double distance(Object a);


    /**
     * Aggiorna il valore dell'item, calcolandolo come il prototipo dell'attributo rispetto al clusteredData
     * @param data il dataset
     * @param clusteredData l'insieme degli indici delle transazioni appartenenti al cluster in considerazione
     */
    public void update(Data data, Set<Integer> clusteredData) {
        value = data.computePrototype(clusteredData, attribute);
    }
}
