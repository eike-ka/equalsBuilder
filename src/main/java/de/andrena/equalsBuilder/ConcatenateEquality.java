package de.andrena.equalsBuilder;

class ConcatenateEquality<T> implements SameTypeEquality<T> {

    private final SameTypeEquality<T> equality1;
    private final SameTypeEquality<T> equality2;

    public ConcatenateEquality(SameTypeEquality<T> equality1, SameTypeEquality<T> equality2) {
        this.equality1 = equality1;
        this.equality2 = equality2;
    }

    @Override
    public boolean areEqual(T thiz, T that) {

        return equality1.areEqual(thiz, that) && equality2.areEqual(thiz, that);
    }

    @Override
    public int hashCode(T thiz) {
        return 31 * equality1.hashCode(thiz) + equality2.hashCode(thiz);
    }
}
