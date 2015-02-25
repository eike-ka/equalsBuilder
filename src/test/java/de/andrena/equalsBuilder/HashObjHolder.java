package de.andrena.equalsBuilder;

public class HashObjHolder {
    private static final Equality<HashObjHolder> EQUALITY = Equality
            .forClass(HashObjHolder.class)
            .compares(HashObjHolder::getAttr2)
            .compares(HashObjHolder::getAttr1)
            .create();

    private final ObjWithHash attr1;
    private final ObjWithHash attr2;

    public HashObjHolder(ObjWithHash attr1, ObjWithHash attr2) {
        this.attr1 = attr1;
        this.attr2 = attr2;
    }

    public ObjWithHash getAttr1() {
        return attr1;
    }

    public ObjWithHash getAttr2() {
        return attr2;
    }

    @Override
    public int hashCode() {
        return EQUALITY.hashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EQUALITY.areEqual(this, obj);
    }
}
