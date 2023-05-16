package mining;

import data.Data;
import data.OutOfRangeSampleSize;
import mining.Cluster;
import mining.ClusterSet;

import java.io.*;

/**
 * mining.KmeansMiner.java
 * <p>
 * Classe che implementa l'algoritmo di clustering K-means
 *
 * @author Claudio De Marzo
 */
public class KmeansMiner {
    /**
     * Oggetto che contiene l'insieme dei cluster
     */
    private final ClusterSet C;

    /**
     * Costruttore della classe mining.KmeansMiner
     * @param k il numero di cluster da creare
     */

    public KmeansMiner(int k) {
        C = new ClusterSet(k);
    }

    /**
     * Costruttore della classe mining.KmeansMiner. Carica l'insieme dei cluster da file.
     * @param fileName la path del file dal quale caricare il clusterset
     * @throws IOException se si verifica un errore durante la lettura del file
     * @throws ClassNotFoundException se il ClassLoader non riesce a trovare la classe ClusterSet
     */
    public KmeansMiner(String fileName) throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        C = (ClusterSet) ois.readObject();
        ois.close();
    }

    /**
     * Restituisce l'insieme dei cluster
     * @return l'insieme dei cluster
     */
    public ClusterSet getC() {
        return C;
    }

    /**
     * Esegue l'algoritmo di clustering K-means
     * @param data il dataset su cui eseguire l'algoritmo
     * @return il numero di iterazioni necessarie per raggiungere la convergenza
     * @throws OutOfRangeSampleSize se il numero di cluster e maggiore del numero di transazioni nel dataset
     */

    public int kmeans(Data data) throws OutOfRangeSampleSize {
        int numberOfIterations = 0; //STEP 1
        C.initializeCentroids(data);
        boolean changedCluster;
        do {
            numberOfIterations++;
            //STEP 2
            changedCluster = false;
            for (int i = 0; i < data.getNumberOfExamples(); i++) {
                Cluster nearestCluster = C.nearestCluster(data.getItemSet(i));
                Cluster oldCluster = C.currentCluster(i);
                boolean currentChange = nearestCluster.addData(i);
                if (currentChange)
                    changedCluster = true;
                //rimuovo la tupla dal vecchio cluster
                if (currentChange && oldCluster != null)
                    //il nodo va rimosso dal suo vecchio cluster
                    oldCluster.removeTuple(i);
            }
            //STEP 3
            C.updateCentroids(data);

        } while (changedCluster);
        return numberOfIterations;
    }

    /**
     * Salva l'insieme dei cluster su file
     * @param fileName la path del file su cui salvare il clusterset
     * @throws IOException se si verifica un errore durante la scrittura del file
     */

    public void salva(String fileName) throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName, false));
        oos.writeObject(C);
        oos.close();
    }
}
