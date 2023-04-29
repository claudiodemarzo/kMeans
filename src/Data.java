import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Data.java
 * <p>
 * Classe che rappresenta un insieme di esempi. Questa classe contiene un array di oggetti Object[][] che rappresenta i dati, un array di attributi Attribute[] e il numero di esempi.
 *
 * @author Claudio De Marzo
 */
public class Data {
    /**
     * Array di oggetti Object[][] che rappresenta il dataset
     */
    private Object[][] data;
    /**
     * Numero di esempi
     */
    private int numberOfExamples;
    /**
     * Array di attributi
     */
    private Attribute[] attributeSet;

    /**
     * Costruttore della classe Data. Costruisce il dataset attraverso l'inizializzazione de l'array di oggetti Object[][] data, il numero di esempi numberOfExamples e l'array di attributi attributeSet.
     */
    Data() {
        data = new Object[14][5];

        data[0][0] = "sunny";
        data[1][0] = "sunny";
        data[2][0] = "overcast";
        data[3][0] = "rain";
        data[4][0] = "rain";
        data[5][0] = "rain";
        data[6][0] = "overcast";
        data[7][0] = "sunny";
        data[8][0] = "sunny";
        data[9][0] = "rain";
        data[10][0] = "sunny";
        data[11][0] = "overcast";
        data[12][0] = "overcast";
        data[13][0] = "rain";

        data[0][1] = "hot";
        data[1][1] = "hot";
        data[2][1] = "hot";
        data[3][1] = "mild";
        data[4][1] = "cool";
        data[5][1] = "cool";
        data[6][1] = "cool";
        data[7][1] = "mild";
        data[8][1] = "cool";
        data[9][1] = "mild";
        data[10][1] = "mild";
        data[11][1] = "mild";
        data[12][1] = "hot";
        data[13][1] = "mild";

        data[0][2] = "high";
        data[1][2] = "high";
        data[2][2] = "high";
        data[3][2] = "high";
        data[4][2] = "normal";
        data[5][2] = "normal";
        data[6][2] = "normal";
        data[7][2] = "high";
        data[8][2] = "normal";
        data[9][2] = "normal";
        data[10][2] = "normal";
        data[11][2] = "high";
        data[12][2] = "normal";
        data[13][2] = "high";


        data[0][3] = "weak";
        data[1][3] = "strong";
        data[2][3] = "weak";
        data[3][3] = "weak";
        data[4][3] = "weak";
        data[5][3] = "strong";
        data[6][3] = "strong";
        data[7][3] = "weak";
        data[8][3] = "weak";
        data[9][3] = "weak";
        data[10][3] = "strong";
        data[11][3] = "strong";
        data[12][3] = "weak";
        data[13][3] = "strong";


        data[0][4] = "no";
        data[1][4] = "no";
        data[2][4] = "yes";
        data[3][4] = "yes";
        data[4][4] = "yes";
        data[5][4] = "no";
        data[6][4] = "yes";
        data[7][4] = "no";
        data[8][4] = "yes";
        data[9][4] = "yes";
        data[10][4] = "yes";
        data[11][4] = "yes";
        data[12][4] = "yes";
        data[13][4] = "no";

        // numberOfExamples
        numberOfExamples = 14;

        //explanatory Set
        attributeSet = new Attribute[5];

        String[] outLookValues = new String[3], temperatureValues = new String[3], humidityValues = new String[2], windValues = new String[2], playValues = new String[2];
        outLookValues[0] = "overcast";
        outLookValues[1] = "rain";
        outLookValues[2] = "sunny";
        attributeSet[0] = new DiscreteAttribute("Outlook", 0, outLookValues);

        temperatureValues[0] = "cool";
        temperatureValues[1] = "hot";
        temperatureValues[2] = "mild";
        attributeSet[1] = new DiscreteAttribute("Temperature", 1, temperatureValues);

        humidityValues[0] = "high";
        humidityValues[1] = "normal";
        attributeSet[2] = new DiscreteAttribute("Humidity", 2, humidityValues);

        windValues[0] = "strong";
        windValues[1] = "weak";
        attributeSet[3] = new DiscreteAttribute("Wind", 3, windValues);

        playValues[0] = "no";
        playValues[1] = "yes";
        attributeSet[4] = new DiscreteAttribute("Play", 4, playValues);
    }

    /**
     * Restituisce il numero di esempi
     *
     * @return Il numero di esempi
     */
    int getNumberOfExamples() {
        return numberOfExamples;
    }

    /**
     * Restituisce il numero degli attributi
     *
     * @return Il numero degli attributi
     */
    int getNumberOfExplanatoryAttributes() {
        return attributeSet.length;
    }

    /**
     * Restituisce lo schema del dataset
     *
     * @return Array di Attribute che rappresenta lo schema del dataset
     */
    Attribute[] getAttributeSchema() {
        return attributeSet;
    }

    /**
     * Restituisce il valore di un attributo di un esempio
     *
     * @param exampleIndex   Indice dell'esempio
     * @param attributeIndex Indice dell'attributo
     * @return Il valore dell'attributo
     */
    Object getAttributeValue(int exampleIndex, int attributeIndex) {
        return data[exampleIndex][attributeIndex];
    }

    Tuple getItemSet(int index) {
        Tuple tuple = new Tuple(attributeSet.length);
        for (int i = 0; i < attributeSet.length; i++)
            tuple.add(new DiscreteItem(attributeSet[i], (String) data[index][i]), i);
        return tuple;
    }

    int[] sampling(int k) {
        int[] centroidIndexes = new int[k];
        //choose k random different centroids in data.
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        for (int i = 0; i < k; i++) {
            boolean found = false;
            int c;
            do {
                found = false;
                c = rand.nextInt(getNumberOfExamples());
                // verify that centroid[c] is not equal to a centroide already stored in CentroidIndexes
                for (int j = 0; j < i; j++)
                    if (compare(centroidIndexes[j], c)) {
                        found = true;
                        break;
                    }
            }
            while (found);
            centroidIndexes[i] = c;
        }
        return centroidIndexes;
    }

    private boolean compare(int i, int j) {
        Tuple t1 = getItemSet(i), t2 = getItemSet(j);
        for (int k = 0; k < t1.getLength(); k++)
            if (!t1.get(k).equals(t2.get(k)))
                return false;
        return true;
    }

    Object computePrototype(ArraySet idList, Attribute attribute) {
        return computePrototype(idList, (DiscreteAttribute) attribute);
    }

    String computePrototype(ArraySet idList, DiscreteAttribute attribute) {
        Map<Object, Integer> counterMap = new HashMap<>();
        for (int i = 0; i < attribute.getNumberOfDistinctValues(); i++) {
            counterMap.put(attribute.getValue(i), attribute.frequency(this, idList, attribute.getValue(i)));
        }
        Object proto = null;
        int maxVal = Integer.MIN_VALUE;
        for (Map.Entry<Object, Integer> entry : counterMap.entrySet())
            if (entry.getValue() > maxVal) {
                maxVal = entry.getValue();
                proto = entry.getKey();
            }
        return (String) proto;
    }

    /**
     * Restituisce la rappresentazione in stringa del dataset
     *
     * @return Il dataset sotto forma di stringa
     */
    @Override
    public String toString() {
        String s = "";
        for (Attribute val : getAttributeSchema()) {
            s += val.getName() + ",";
        }
        s += "\n";

        for (int i = 0; i < getNumberOfExamples(); i++) {
            s += (i + 1) + ":";
            for (int j = 0; j < getNumberOfExplanatoryAttributes(); j++) {
                s += getAttributeValue(i, j) + ",";
            }
            s += "\n";
        }
        return s;
    }
}
