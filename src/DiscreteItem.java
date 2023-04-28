public class DiscreteItem extends Item{
    DiscreteItem(Attribute attribute, Object value) {
        super(attribute, value);
    }

    @Override
    double distance(Object a) {
        return getValue().equals(a) ? 0 : 1;
    }
}
