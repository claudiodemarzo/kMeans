package data;

/**
 * OutOfRangeSampleSize.java
 * <p>
 * Eccezione che viene lanciata quando il numero di cluster inserito dall'utente è maggiore del numero di tuple del dataset
 *
 * @see Data
 */
public class OutOfRangeSampleSize extends Exception {

    /**
     * Costruttore della classe OutOfRangeSampleSize
     */
    OutOfRangeSampleSize(String msg) {
        super(msg);
    }
}
