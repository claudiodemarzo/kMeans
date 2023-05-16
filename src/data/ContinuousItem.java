package data;

/**
 * data.ContinuousItem.java
 * <p>
 * Classe che modella un item di tipo continuo
 *
 * @see Item
 */
public class ContinuousItem extends Item{

    /**
     * Costruttore della classe data.ContinuousItem
     * @param attribute l'attributo dell'item
     * @param value il valore assunto dall'item
     */
    ContinuousItem(Attribute attribute, Object value) {
        super(attribute, value);
    }

    /**
     * Calcola la distanza tra due item di tipo continuo
     * @param a l'oggetto rispetto al quale calcolare la distanza
     * @return la distanza tra i due item
     */
    @Override
    double distance(Object a) {
        return Math.abs(((ContinuousAttribute)this.getAttribute()).getScaledValue((double)this.getValue()) - ((ContinuousAttribute)this.getAttribute()).getScaledValue((double)a));
    }
}
