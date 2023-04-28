public class ClusterSet {
    private Cluster[] C;
    private int i = 0;

    ClusterSet(int k) {
        C = new Cluster[k];
    }

    void add(Cluster c) {
        C[i] = c;
        i++;
    }

    Cluster get(int i) {
        return C[i];
    }

    void initializeCentroids(Data data) {
        int[] centroidIndexes = data.sampling(C.length);
        for (int i = 0; i < centroidIndexes.length; i++) {
            Tuple centroidI = data.getItemSet(centroidIndexes[i]);
            add(new Cluster(centroidI));
        }
    }

    Cluster nearestCluster(Tuple tuple) {
        Cluster nearest = null;
        double distance = Double.MAX_VALUE;
        for (int i = 0; i < this.i; i++) {
            if (tuple.getDistance(C[i].getCentroid()) < distance){
                nearest = C[i];
                distance = tuple.getDistance(C[i].getCentroid());
            }
        }
        return nearest;
    }

    Cluster currentCluster(int id) {
        for (int i = 0; i < this.i; i++)
            if (C[i].contain(id)) return C[i];
        return null;
    }

    void updateCentroids(Data data) {
        for (int i = 0; i < this.i; i++) {
            C[i].computeCentroid(data);
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.i; i++) {
            s += "Cluster " + i + " centroid: " + C[i].getCentroid().toString();
        }
        return s;
    }

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
