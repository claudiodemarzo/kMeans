/**
 * DiscreteAttribute.java
 * <p>
 * Classe che rappresenta un attributo discreto (categorico). Questa classe estende la classe astratta Attribute e include metodi per la gestione dei valori possibili dell'attributo.
 * @see Attribute
 *
 * @author Claudio De Marzo
 */
public class DiscreteAttribute extends Attribute{

    /**
     * Valori possibili dell'attributo
     */
    private String[] values;

    /**
     * Costruttore della classe DiscreteAttribute
     * @param name Il nome dell'attributo
     * @param index L'indice dell'attributo
     * @param values I valori possibili dell'attributo
     */
    public DiscreteAttribute(String name, int index, String[] values) {
        super(name, index);
        this.values = values;
    }

    /**
     * Restituisce il numero di valori possibili dell'attributo (lunghezza dell'array values)
     * @return Il numero di valori possibili dell'attributo
     */
    int getNumberOfDistinctValues(){
        return values.length;
    }

    /**
     * Restituisce il valore dell'attributo a partire dall'indice
     * @param i L'indice del valore
     * @return Il valore dell'attributo
     */
    String getValue(int i){
        return values[i];
    }
}
