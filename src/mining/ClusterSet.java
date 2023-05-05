package mining;

import data.Data;
import data.OutOfRangeSampleSize;
import data.Tuple;

/**
 * mining.ClusterSet.java
 * <p>
 * Classe che modella un insieme di mining.Cluster
 *
 * @see Cluster
 * @author Claudio De Marzo
 */
public class ClusterSet {

    /**
     * Array di mining.Cluster
     */
    private Cluster[] C;

    /**
     * Indice dell'ultima posizione di C libero
     */
    private int i = 0;

    /**
     * Costruttore della classe mining.ClusterSet
     * @param k il numero di cluster contenuti nel Set
     */

    ClusterSet(int k) {
        C = new Cluster[k];
    }

    /**
     * Aggiunge un cluster all'insieme
     * @param c il mining.Cluster da aggiungere
     */

    void add(Cluster c) {
        C[i] = c;
        i++;
    }

    /**
     * Restituisce un cluster dal Set dato l'indice
     * @param i l'indice del cluster da restituire
     * @return il cluster richiesto
     */

    Cluster get(int i) {
        return C[i];
    }

    /**
     * Sceglie i centroidi dal dataset e inizializza i cluster nel mining.ClusterSet
     * @param data il dataset
     */

    void initializeCentroids(Data data) throws OutOfRangeSampleSize {
        int[] centroidIndexes = data.sampling(C.length);
        for (int i = 0; i < centroidIndexes.length; i++) {
            Tuple centroidI = data.getItemSet(centroidIndexes[i]);
            add(new Cluster(centroidI));
        }
    }

    /**
     * Restituisce il cluster più vicino ad una tupla
     * @param tuple la tupla
     * @return il cluster più vicino
     */

    Cluster nearestCluster(Tuple tuple) {
        Cluster nearest = null;
        double distance = Double.MAX_VALUE;
        for (int i = 0; i < this.i; i++) {
            if (tuple.getDistance(C[i].getCentroid()) < distance) {
                nearest = C[i];
                distance = tuple.getDistance(C[i].getCentroid());
            }
        }
        return nearest;
    }

    /**
     * Restituisce il cluster che contiene una certa transazione, dato l'indice di questa
     * @param id l'indice della transazione
     * @return il cluster che contiene la transazione, o null se non è contenuta in nessun cluster
     */
    Cluster currentCluster(int id) {
        for (int i = 0; i < this.i; i++)
            if (C[i].contain(id)) return C[i];
        return null;
    }

    /**
     * Aggiorna i centroidi dei cluster
     * @param data il dataset
     */

    void updateCentroids(Data data) {
        for (int i = 0; i < this.i; i++) {
            C[i].computeCentroid(data);
        }
    }

    /**
     * Restituisce una stringa fatta da ciascun centroide dell’insieme dei cluster.
     * @return la stringa richiesta
     */

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.i; i++) {
            s += "mining.Cluster " + i + " centroid: " + C[i].getCentroid().toString();
        }
        return s;
    }

    /**
     * Restituisce una stringa che descriva lo stato di ciascun cluster in C.
     * @param data il dataset
     * @return la stringa richiesta
     */
    public String toString(Data data) {
        String str = "";
        for (int i = 0; i < this.i; i++) {
            if (C[i] != null) {
                str += i + ":" + C[i].toString(data) + "\n";
            }
        }
        return str;
    }
}
