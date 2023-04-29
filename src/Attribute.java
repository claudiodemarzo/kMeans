/**
 * Attribute.java
 * <p>
 * Classe astratta che rappresenta un attributo
 *
 * @author Claudio De Marzo
 */
abstract class Attribute {
    /**
     * Nome dell'attributo
     */
    private String name;
    /**
     * Indice dell'attributo
     */
    private int index;

    /**
     * Costruttore della classe Attribute
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
