package de.andrena.equalsBuilder;

class TypeCheckingEqualityWrapper<T> implements Equality<T> {
    private final Class<T> type;
    private final SameTypeEquality<T> wrapped;

    public TypeCheckingEqualityWrapper(Class<T> type, SameTypeEquality<T> wrapped) {
        this.type = type;
        this.wrapped = wrapped;
    }

    @Override
    public boolean areEqual(T thiz, Object that) {
        if (that == null || type != that.getClass()) {
            return false;
        } else {
            return wrapped.areEqual(thiz, (T) that);
        }
    }

    @Override
    public int hashCode(T thiz) {
        return wrapped.hashCode(thiz);
    }
}
