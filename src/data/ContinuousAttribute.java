package data;

/**
 * data.ContinuousAttribute.java
 * <p>
 * Rappresenta un attributo continuo (numerico). Questa classe estende la classe astratta data.Attribute e include metodi per la normalizzazione dell'attributo nell'Intervallo [0,1] al fine da rendere confrontabili attributi aventi domini diversi.
 *
 * @author Claudio De Marzo
 * @see Attribute
 */
class ContinuousAttribute extends Attribute {
    /**
     * Valore massimo del dominio dell'attributo
     */
    private final double max;
    /**
     * Valore minimo del dominio dell'attributo
     */
    private final double min;

    /**
     * Costruttore della classe data.ContinuousAttribute
     *
     * @param name  Il nome dell'attributo
     * @param index L'indice dell'attributo
     * @param min   Il valore minimo del dominio dell'attributo
     * @param max   Il valore massimo del dominio dell'attributo
     */
    ContinuousAttribute(String name, int index, double min, double max) {
        super(name, index);
        this.max = max;
        this.min = min;
    }

    /**
     * Calcola e restituisce il valore normalizzato del parametro
     * passato in input. La normalizzazione ha come codominio l'intervallo [0,1]. La
     * normalizzazione di v è quindi calcolata come v'= (v - min) / (max - min)
     *
     * @param v Il valore da normalizzare
     * @return Il valore normalizzato
     */
    double getScaledValue(double v) {
        return (v - min) / (max - min);
    }
}