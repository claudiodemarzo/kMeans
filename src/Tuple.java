public class Tuple {
    private Item[] tuple;

    Tuple(int size) {
        tuple = new Item[size];
    }

    int getLength() {
        return tuple.length;
    }

    Item get(int i) {
        return tuple[i];
    }

    void add(Item c, int i) {
        tuple[i] = c;
    }

    double getDistance(Tuple obj) {
        double distance = 0;
        for (int i = 0; i < tuple.length; i++) {
            distance += tuple[i].distance(obj.get(i).getValue());
        }
        return distance;
    }

    double avgDistance(Data data, int[] clusteredData) {
        double p = 0.0, sumD = 0.0;
        for (int i = 0; i < clusteredData.length; i++) {
            double d = getDistance(data.getItemSet(clusteredData[i]));
            sumD += d;
        }
        p = sumD / clusteredData.length;
        return p;
    }

    @Override
    public String toString() {
        String s = "";
        for (Item i : tuple) {
            s += i.toString();
        }
        return s;
    }
}
