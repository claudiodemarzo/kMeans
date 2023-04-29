public abstract class Item {

    private Attribute attribute;
    private Object value;

    Item(Attribute attribute, Object value) {
        this.attribute = attribute;
        this.value = value;
    }

    Attribute getAttribute() {
        return attribute;
    }

    Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    abstract double distance(Object a);

    void update(Data data, ArraySet clusteredData) {
        value = data.computePrototype(clusteredData, attribute);
    }
}
