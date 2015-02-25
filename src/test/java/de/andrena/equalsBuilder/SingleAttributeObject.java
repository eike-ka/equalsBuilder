package de.andrena.equalsBuilder;

public class SingleAttributeObject {
    private static final Equality<SingleAttributeObject> EQUALITY = Equality
            .forClass(SingleAttributeObject.class)
            .compares(SingleAttributeObject::getVal)
            .create();

    private String val;

    public SingleAttributeObject(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
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
