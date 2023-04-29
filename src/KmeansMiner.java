/**
 * KmeansMiner.java
 * <p>
 * Classe che implementa l'algoritmo di clustering K-means
 *
 * @author Claudio De Marzo
 */
public class KmeansMiner {
    /**
     * Oggetto che contiene l'insieme dei cluster
     */
    private ClusterSet C;

    /**
     * Costruttore della classe KmeansMiner
     * @param k il numero di cluster da creare
     */

    KmeansMiner(int k) {
        C = new ClusterSet(k);
    }

    /**
     * Restituisce l'insieme dei cluster
     * @return l'insieme dei cluster
     */
    ClusterSet getC() {
        return C;
    }

    /**
     * Esegue l'algoritmo di clustering K-means
     * @param data il dataset su cui eseguire l'algoritmo
     * @return il numero di iterazioni necessarie per raggiungere la convergenza
     */

    int kmeans(Data data) {
        int numberOfIterations = 0; //STEP 1
        C.initializeCentroids(data);
        boolean changedCluster = false;
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
}
