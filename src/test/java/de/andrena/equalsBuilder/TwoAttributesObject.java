package de.andrena.equalsBuilder;

public class TwoAttributesObject {
    private static final Equality<TwoAttributesObject> EQUALITY = Equality
            .forClass(TwoAttributesObject.class)
            .compares(TwoAttributesObject::getVal)
            .compares(TwoAttributesObject::getIntegerVal)
            .create();

    private final String val;
    private final Integer integerVal;

    public TwoAttributesObject(String val, Integer integerVal) {
        this.val = val;
        this.integerVal = integerVal;
    }

    public String getVal() {
        return val;
    }

    public Integer getIntegerVal() {
        return integerVal;
    }

    @Override
    public boolean equals(Object obj) {
        return EQUALITY.areEqual(this, obj);
    }

    @Override
    public int hashCode() {
        return EQUALITY.hashCode(this);
    }
}
