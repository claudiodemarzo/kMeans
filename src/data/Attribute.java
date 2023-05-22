package data;

import java.io.Serializable;

/**
 * data.Attribute.java
 * <p>
 * Classe astratta che rappresenta un attributo
 *
 * @author Claudio De Marzo
 */
abstract class Attribute implements Serializable {
    /**
     * Nome dell'attributo
     */
    private final String name;
    /**
     * Indice dell'attributo
     */
    private final int index;

    /**
     * Costruttore della classe data.Attribute
     *
     * @param name  il nome dell'attributo
     * @param index l'indice dell'attributo
     */
    Attribute(String name, int index) {
        this.name = name;
        this.index = index;
    }

    /**
     * Restituisce il nome dell'attributo
     *
     * @return il nome dell'attributo
     */
    String getName() {
        return name;
    }

    /**
     * Restituisce l'indice dell'attributo
     *
     * @return l'indice dell'attributo
     */
    int getIndex() {
        return index;
    }

    /**
     * Restituisce una rappresentazione testuale dell'attributo
     *
     * @return una rappresentazione testuale dell'attributo, che coincide con il nome dell'attributo
     */
    @Override
    public String toString() {
        return name;
    }

}
