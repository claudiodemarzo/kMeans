package data;

import mining.KmeansMiner;

/**
 * data.MainTest.java
 * <p>
 * Entry point del programma.
 * @author Dott.ssa Appice A.
 */
public class MainTest {

    /**
     * Metodo main
     * @param args argomenti da linea di comando
     */
    public static void main(String[] args) {
        Data data = new Data();
        System.out.println(data);
        int k = 3;
        KmeansMiner kmeans = new KmeansMiner(k);
        int numIter = kmeans.kmeans(data);
        System.out.println("Numero di Iterazione:" + numIter);
        System.out.println(kmeans.getC().toString(data));
    }

}
