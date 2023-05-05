package mining;

import data.Data;
import data.Tuple;
import utility.ArraySet;

/**
 * mining.Cluster.java
 * <p>
 * Classe che modella un cluster
 *
 * @author Dott.ssa Appice A.
 */
public class Cluster {
    /**
     * Centroide del cluster
     */
    private Tuple centroid;

    /**
     * Insieme di degli indici (dal dataset) di transazioni che appartengono al cluster
     */
    private ArraySet clusteredData;

    /**
     * Costruttore della classe mining.Cluster
     * @param centroid il centroide del cluster da costruire
     */

    Cluster(Tuple centroid) {
        this.centroid = centroid;
        clusteredData = new ArraySet();

    }

    /**
     * Restituisce la Tupla che corrisponde al centroide del cluster
     * @return il centroide del cluster
     */

    Tuple getCentroid() {
        return centroid;
    }

    /**
     * Calcola il centroide del cluster
     * @param data il dataset
     */

    void computeCentroid(Data data) {
        for (int i = 0; i < centroid.getLength(); i++) {
            centroid.get(i).update(data, clusteredData);
        }
    }

    /**
     * Aggiunge una transazione al cluster
     * @param id l'id (dal dataset) della transazione da aggiungere
     * @return true se la transazione è cambiata di cluster, false altrimenti
     */
    boolean addData(int id) {
        return clusteredData.add(id);

    }

    /**
     * Verifica se una transazione è clusterizzata nell'array corrente
     * @param id l'id della transazione da verificare
     * @return true se la transazione è clusterizzata nell'array corrente, false altrimenti
     */
    boolean contain(int id) {
        return clusteredData.get(id);
    }

    /**
     * Rimuove una transazione dal cluster
     * @param id l'id della transazione da rimuovere
     */
    void removeTuple(int id) {
        clusteredData.delete(id);

    }

    /**
     * Crea una rappresentazione in String del cluster, evidenziandone il centroide
     * @return la rappresentazione in String del cluster
     */
    public String toString() {
        String str = "Centroid=(";
        for (int i = 0; i < centroid.getLength(); i++)
            str += centroid.get(i);
        str += ")";
        return str;

    }

    /**
     * Crea una rappresentazione in String del cluster, includendo, oltre al centroide, anche le transazioni che vi appartengono
     * @param data il dataset
     * @return la rappresentazione in String del cluster
     */
    public String toString(Data data) {
        String str = "Centroid=(";
        for (int i = 0; i < centroid.getLength(); i++)
            str += centroid.get(i) + " ";
        str += ")\nExamples:\n";
        int array[] = clusteredData.toArray();
        for (int i = 0; i < array.length; i++) {
            str += "[";
            for (int j = 0; j < data.getNumberOfExplanatoryAttributes(); j++)
                str += data.getAttributeValue(array[i], j) + " ";
            str += "] dist=" + getCentroid().getDistance(data.getItemSet(array[i])) + "\n";

        }
        str += "\nAvgDistance=" + getCentroid().avgDistance(data, array);
        return str;

    }

}
