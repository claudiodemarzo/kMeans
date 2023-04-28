/**
 * DiscreteAttribute.java
 * <p>
 * Classe che rappresenta un attributo discreto (categorico). Questa classe estende la classe astratta Attribute e include metodi per la gestione dei valori possibili dell'attributo.
 *
 * @author Claudio De Marzo
 * @see Attribute
 */
class DiscreteAttribute extends Attribute {

    /**
     * Valori possibili dell'attributo
     */
    private String[] values;

    /**
     * Costruttore della classe DiscreteAttribute
     *
     * @param name   Il nome dell'attributo
     * @param index  L'indice dell'attributo
     * @param values I valori possibili dell'attributo
     */
    DiscreteAttribute(String name, int index, String[] values) {
        super(name, index);
        this.values = values;
    }

    /**
     * Restituisce il numero di valori possibili dell'attributo (lunghezza dell'array values)
     *
     * @return Il numero di valori possibili dell'attributo
     */
    int getNumberOfDistinctValues() {
        return values.length;
    }

    /**
     * Restituisce il valore dell'attributo a partire dall'indice
     *
     * @param i L'indice del valore
     * @return Il valore dell'attributo
     */
    String getValue(int i) {
        return values[i];
    }

    int frequency(Data data, ArraySet idList, String v) {
        int count = 0;
        int[] idArray = idList.toArray();
        for(int i : idArray){
            if(data.getAttributeValue(i, getIndex()).equals(v)) count++;
        }
        return count;
    }
}
