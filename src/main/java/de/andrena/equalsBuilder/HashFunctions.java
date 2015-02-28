package de.andrena.equalsBuilder;

import java.util.function.Function;
import java.util.function.ToIntFunction;

class HashFunctions {

    public static <T> ToIntFunction<T> intArrayLengthHash(Function<T, int[]> intArrayField) {
        return e -> {
            int[] intArray = intArrayField.apply(e);
            return intArray == null ? 0 : intArray.length;
        };
    }

    public static <F> Integer nullSafeHash(F fieldValue) {
        return fieldValue == null ? null : fieldValue.hashCode();
    }
}
