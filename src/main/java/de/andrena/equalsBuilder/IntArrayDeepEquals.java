package de.andrena.equalsBuilder;

import java.util.Arrays;
import java.util.function.Function;

class IntArrayDeepEquals<T> implements SameTypeEquality<T> {
    private final Function<T, int[]> intArrayField;

    public IntArrayDeepEquals(Function<T, int[]> intArrayField) {
        this.intArrayField = intArrayField;
    }

    @Override
    public boolean areEqual(T thiz, T that) {
        return Arrays.equals(intArrayField.apply(thiz), intArrayField.apply(that));
    }

    @Override
    public int hashCode(T thiz) {
        int[] intArray = intArrayField.apply(thiz);
        return intArray == null ? 0 : intArray.length;
    }
}
