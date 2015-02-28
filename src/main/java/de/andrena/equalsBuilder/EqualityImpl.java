package de.andrena.equalsBuilder;

import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;

class EqualityImpl<T> implements Equality<T> {
    private final Class<T> type;
    private final ToIntFunction<T> hashFunction;
    private final BiPredicate<T, T> equalsFunction;

    EqualityImpl(Class<T> type, BiPredicate<T, T> equalsFunction, ToIntFunction<T> hashFunction) {
        this.type = type;
        this.hashFunction = hashFunction;
        this.equalsFunction = equalsFunction;
    }

    @Override
    @SuppressWarnings("unchecked") //checked by hasSameType method
    public boolean areEqual(T thiz, Object other) {
        return hasSameType(other) && equalsFunction.test(thiz, (T) other);
    }

    private boolean hasSameType(Object that) {
        return that != null && type == that.getClass();
    }

    @Override
    public int hashCode(T thiz) {
        return hashFunction.applyAsInt(thiz);
    }
}
