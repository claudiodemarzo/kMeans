package data;

import java.sql.SQLException;
import java.util.*;

import database.*;

/**
 * Data.java
 * <p>
 * Classe che rappresenta un insieme di esempi. Questa classe contiene un Lista di oggetti Example che rappresenta il dataset, una Lista di Attribute e il numero di esempi.
 *
 * @author Claudio De Marzo
 */
public class Data {
    /**
     * Lista di oggetti Example che rappresenta il dataset
     */
    private List<Example> data;
    /**
     * Numero di esempi
     */
    private final int numberOfExamples;
    /**
     * Lista degli attributi
     */
    private final List<Attribute> attributeSet;

    /**
     * Costruttore della classe Data. Costruisce il dataset attraverso l'inizializzazione della Lista di oggetti Examples data, il numero di esempi numberOfExamples e la lista di attributi attributeSet.
     */
    public Data(String table) {
        DbAccess db = new DbAccess();
        try {
            db.initConnection();
        } catch (DatabaseConnectionException e) {
            e.printStackTrace();
        }
        TableData td = new TableData(db);
        TableSchema ts = null;
        try {
            ts = new TableSchema(db, table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            data = td.getDistinctTransazioni(table);
        } catch (SQLException | EmptySetException e) {
            e.printStackTrace();
        }


        /*Example[] examples = new Example[14];
        for (int i = 0; i < examples.length; i++) {
            examples[i] = new Example();
        }

        examples[0].add("sunny");
        examples[0].add(37.5);
        examples[0].add("high");
        examples[0].add("weak");
        examples[0].add("no");

        examples[1].add("sunny");
        examples[1].add(38.7);
        examples[1].add("high");
        examples[1].add("strong");
        examples[1].add("no");

        examples[2].add("overcast");
        examples[2].add(37.5);
        examples[2].add("high");
        examples[2].add("weak");
        examples[2].add("yes");

        examples[3].add("rain");
        examples[3].add(20.5);
        examples[3].add("high");
        examples[3].add("weak");
        examples[3].add("yes");

        examples[4].add("rain");
        examples[4].add(20.7);
        examples[4].add("normal");
        examples[4].add("weak");
        examples[4].add("yes");

        examples[5].add("rain");
        examples[5].add(21.2);
        examples[5].add("normal");
        examples[5].add("strong");
        examples[5].add("no");

        examples[6].add("overcast");
        examples[6].add(20.5);
        examples[6].add("normal");
        examples[6].add("strong");
        examples[6].add("yes");

        examples[7].add("sunny");
        examples[7].add(21.2);
        examples[7].add("high");
        examples[7].add("weak");
        examples[7].add("no");

        examples[8].add("sunny");
        examples[8].add(21.2);
        examples[8].add("normal");
        examples[8].add("weak");
        examples[8].add("yes");

        examples[9].add("rain");
        examples[9].add(19.8);
        examples[9].add("normal");
        examples[9].add("weak");
        examples[9].add("yes");

        examples[10].add("sunny");
        examples[10].add(3.5);
        examples[10].add("normal");
        examples[10].add("strong");
        examples[10].add("yes");

        examples[11].add("overcast");
        examples[11].add(5.6);
        examples[11].add("high");
        examples[11].add("strong");
        examples[11].add("yes");

        examples[12].add("overcast");
        examples[12].add(3.5);
        examples[12].add("normal");
        examples[12].add("weak");
        examples[12].add("yes");

        examples[13].add("rain");
        examples[13].add(3.2);
        examples[13].add("high");
        examples[13].add("strong");
        examples[13].add("no");

        data = new ArrayList<>(new TreeSet<>(Arrays.asList(examples)));*/
        // numberOfExamples
        numberOfExamples = data.size();

        //explanatory Set
        attributeSet = new LinkedList<>();

        for (int i = 0; i < ts.getNumberOfAttributes(); i++) {
            if (ts.getColumn(i).isNumber()) {
                try {
                    attributeSet.add(new ContinuousAttribute(ts.getColumn(i).getColumnName(), i, (float) td.getAggregateColumnValue(table, ts.getColumn(i), QUERY_TYPE.MIN), (float) td.getAggregateColumnValue(table, ts.getColumn(i), QUERY_TYPE.MAX)));
                } catch (SQLException | NoValueException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    attributeSet.add(new DiscreteAttribute(ts.getColumn(i).getColumnName(), i, td.getDistinctColumnValues(table, ts.getColumn(i)).toArray(new String[0])));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        /*String[] outLookValues = new String[3], humidityValues = new String[2], windValues = new String[2], playValues = new String[2];
        outLookValues[0] = "overcast";
        outLookValues[1] = "rain";
        outLookValues[2] = "sunny";
        Arrays.sort(outLookValues);
        attributeSet.add(new DiscreteAttribute("Outlook", 0, outLookValues));

        attributeSet.add(new ContinuousAttribute("Temperature", 1, 3.2, 38.7));

        humidityValues[0] = "high";
        humidityValues[1] = "normal";
        Arrays.sort(humidityValues);
        attributeSet.add(new DiscreteAttribute("Humidity", 2, humidityValues));

        windValues[0] = "strong";
        windValues[1] = "weak";
        Arrays.sort(windValues);
        attributeSet.add(new DiscreteAttribute("Wind", 3, windValues));

        playValues[0] = "no";
        playValues[1] = "yes";
        Arrays.sort(playValues);
        attributeSet.add(new DiscreteAttribute("Play", 4, playValues));*/
    }

    /**
     * Restituisce il numero di esempi
     *
     * @return Il numero di esempi
     */
    public int getNumberOfExamples() {
        return numberOfExamples;
    }

    /**
     * Restituisce il numero degli attributi
     *
     * @return Il numero degli attributi
     */
    public int getNumberOfExplanatoryAttributes() {
        return attributeSet.size();
    }

    /**
     * Restituisce lo schema del dataset
     *
     * @return Array di data.Attribute che rappresenta lo schema del dataset
     */
    List<Attribute> getAttributeSchema() {
        return attributeSet;
    }

    /**
     * Restituisce il valore di un attributo di un esempio
     *
     * @param exampleIndex   Indice dell'esempio
     * @param attributeIndex Indice dell'attributo
     * @return Il valore dell'attributo
     */
    public Object getAttributeValue(int exampleIndex, int attributeIndex) {
        return data.get(exampleIndex).get(attributeIndex);
    }

    /**
     * Restituisce una Tupla che contiene i valori degli attributi di un esempio
     *
     * @param index l'indice dell'esempio
     * @return la Tupla che contiene i valori degli attributi di un esempio
     */
    public Tuple getItemSet(int index) {
        Tuple tuple = new Tuple(attributeSet.size());
        for (int i = 0; i < attributeSet.size(); i++)
            tuple.add(attributeSet.get(i) instanceof DiscreteAttribute ? new DiscreteItem(attributeSet.get(i), data.get(index).get(i)) : new ContinuousItem(attributeSet.get(i), data.get(index).get(i)), i);
        return tuple;
    }

    /**
     * Esegue il passo 1 dell'algoritmo KMeans. Sceglie k centroidi in modo casuale
     *
     * @param k il numero di centroidi da generare
     * @return un array di k int che rappresentano gli indici di riga delle transazioni scelte come centroidi
     * @throws OutOfRangeSampleSize se k non è compreso tra 1 e il numero di transazioni
     */

    public int[] sampling(int k) throws OutOfRangeSampleSize {
        if (k <= 0 || k > getNumberOfExamples())
            throw new OutOfRangeSampleSize("Il numero dei centroidi deve essere compreso tra 1 e " + getNumberOfExamples() + ".");
        int[] centroidIndexes = new int[k];
        //choose k random different centroids in data.
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        for (int i = 0; i < k; i++) {
            boolean found;
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

    /**
     * Verifica se due transazioni sono uguali
     *
     * @param i indice della prima transazione
     * @param j indice della seconda transazione
     * @return true se le due transazioni sono uguali, false altrimenti
     */

    private boolean compare(int i, int j) {
        Tuple t1 = getItemSet(i), t2 = getItemSet(j);
        for (int k = 0; k < t1.getLength(); k++)
            if (!t1.get(k).equals(t2.get(k)))
                return false;
        return true;
    }

    /**
     * Calcola il centroide rispetto ad un attributo, dato un Set di indici di riga
     *
     * @param idList    il Set di indici da considerare
     * @param attribute l'attributo sul cui calcolare il centroide
     * @return il valore del centroide rispetto ad attribute
     */

    Object computePrototype(Set<Integer> idList, Attribute attribute) {
        if (attribute instanceof ContinuousAttribute)
            return computePrototype(idList, (ContinuousAttribute) attribute);
        else
            return computePrototype(idList, (DiscreteAttribute) attribute);
    }

    /**
     * Determina il valore che occorre più frequentemente per un DiscreteAttribute nel sottoinsieme di dati individuato da idList
     *
     * @param idList    il Set di indici da considerare
     * @param attribute il DiscreteAttribute sul cui calcolare il centroide
     * @return il valore del centroide rispetto ad attribute
     */
    private String computePrototype(Set<Integer> idList, DiscreteAttribute attribute) {
        Map<Object, Integer> counterMap = new HashMap<>();
        for (String attrVal : attribute) {
            counterMap.put(attrVal, attribute.frequency(this, idList, attrVal));
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
     * Determina il valore che occorre più frequentemente per un ContinuousAttribute nel sottoinsieme di dati individuato da idList
     *
     * @param idList    il Set di indici da considerare
     * @param attribute il ContinuousAttribute sul cui calcolare il centroide
     * @return il valore del centroide rispetto ad attribute
     */
    private Object computePrototype(Set<Integer> idList, ContinuousAttribute attribute) {
        double sum = 0;
        for (Integer i : idList)
            sum += (double) getAttributeValue(i, attribute.getIndex());
        return sum / idList.size();
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
