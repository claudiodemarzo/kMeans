package data;

public class ContinuousItem extends Item{
    ContinuousItem(Attribute attribute, Object value) {
        super(attribute, value);
    }
    @Override
    double distance(Object a) {
        return Math.abs(((ContinuousAttribute)this.getAttribute()).getScaledValue((double)this.getValue()) - ((ContinuousAttribute)this.getAttribute()).getScaledValue((double)a));
    }
}
