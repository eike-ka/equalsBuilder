package de.andrena.equalsBuilder;

import java.util.Optional;
import java.util.function.ToIntFunction;

public interface HashAggregator {
    static final HashAggregator DEFAULT = (currentHash, newValue) -> 31 * currentHash + newValue;

    int aggregate(int currentHash, int newValue);

    default <T> ToIntFunction<T> aggregateHashFunctions(Optional<ToIntFunction<T>> optionalOldHashFunction,
                                                       ToIntFunction<T> additionalHashFunction) {
        return optionalOldHashFunction
                .map(oldHashFunction -> aggregateFunctions(oldHashFunction, additionalHashFunction))
                .orElse(additionalHashFunction);
    }

    default <T> ToIntFunction<T> aggregateFunctions(ToIntFunction<T> f1, ToIntFunction<T> f2) {
        return e -> aggregate(f1.applyAsInt(e), f2.applyAsInt(e));
    }
}
