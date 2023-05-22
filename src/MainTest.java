import data.Data;
import data.OutOfRangeSampleSize;
import keyboardinput.Keyboard;
import mining.KmeansMiner;

import java.io.IOException;

/**
 * MainTest.java
 * <p>
 * Entry point del programma.
 *
 * @author Dott.ssa Appice A.
 */
public class MainTest {

    /**
     * Metodo main
     *
     * @param args argomenti da linea di comando
     */
    public static void main(String[] args) {
        Data data = new Data();
        System.out.println(data);
        boolean repeat;
        do {
            int k;
            boolean err;
            KmeansMiner kmeans = null;
            int numIter = 0;
            do {
                err = false;
                System.out.println("Vuoi caricare i cluster da file? (y/n)");
                String answer = Keyboard.readString();
                if (answer.equalsIgnoreCase("y")) {
                    System.out.print("Inserisci il nome del file: ");
                    String fileName = Keyboard.readString();
                    try {
                        kmeans = new KmeansMiner(fileName);
                    } catch (IOException | ClassNotFoundException exception) {
                        System.out.println(exception.getMessage());
                        err = true;
                    }
                } else {
                    System.out.print("Inserisci il numero di cluster k: ");
                    k = Keyboard.readInt();
                    kmeans = new KmeansMiner(k);
                }
                try {
                    numIter = kmeans.kmeans(data);
                } catch (OutOfRangeSampleSize exception) {
                    System.out.println(exception.getMessage());
                    err = true;
                }
            } while (err);
            System.out.println("Numero di Iterazione:" + numIter);
            System.out.println(kmeans.getC().toString(data));
            System.out.println("Vuoi salvare i cluster su file? (y/n)");
            String answer = Keyboard.readString();
            if (answer.equalsIgnoreCase("y")) {
                System.out.print("Inserisci il nome del file: ");
                String fileName = Keyboard.readString();
                try {
                    kmeans.salva(fileName);
                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                }
            }
            System.out.println("Vuoi ripetere l'esecuzione? (y/n)");
            answer = Keyboard.readString();
            repeat = answer.equalsIgnoreCase("y");
        } while (repeat);

    }

}
