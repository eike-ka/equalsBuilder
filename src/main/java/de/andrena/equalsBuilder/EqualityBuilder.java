package de.andrena.equalsBuilder;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/**
 * Builder for {@link de.andrena.equalsBuilder.Equality} instances. Use
 * {@link de.andrena.equalsBuilder.Equality#forClass(Class)} to create a new builder instance.
 */
public class EqualityBuilder<T> {
    private final Class<T> type;
    private final BiPredicate<T,T> equalsFunction;
    private final Optional<ToIntFunction<T>> hashFunction;

    private final HashAggregator hashAggregator = HashAggregator.DEFAULT;


    EqualityBuilder(Class<T> type, BiPredicate<T, T> equalsFunction, Optional<ToIntFunction<T>> hashFunction) {
        this.type = type;
        this.equalsFunction = equalsFunction;
        this.hashFunction = hashFunction;
    }


    EqualityBuilder(Class<T> type) {
        this(type,  (a, b) -> true, Optional.empty());
    }

    EqualityBuilder(Class<T> type, BiPredicate<T, T> equalsFunction, ToIntFunction<T> hashFunction) {
        this(type, equalsFunction, Optional.of(hashFunction));
    }


    /**
     * Adds the comparison of a field. The provided function must return the value of the field for a given instance.
     * This includes a null-safe equals check of the field in the equality calculation and includes the hashCode of
     * the field in the overall hashCode calculation.
     */
    public <F> EqualityBuilder<T> compares(Function<T, F> field) {
        return
                appendEqualsFunction(compareFieldBy(field, Objects::equals))
                .appendHashFunction(hashFieldBy(field, HashFunctions::nullSafeHash));
    }

    /**
     * Adds comparison of a int field. The int fields are compared using the == operator. The value of this field
     * is included in the hash function.
     */
    public EqualityBuilder<T> compares(ToIntFunction<T> intField) {
        return appendEqualsFunction((a, b) -> intField.applyAsInt(a) == intField.applyAsInt(b))
              .appendHashFunction(e -> intField.applyAsInt(e));
    }

    public EqualityBuilder<T> deepCompares(Function<T, int[]> intArrayField) {
        return appendEqualsFunction((a, b) -> Arrays.equals(intArrayField.apply(a), intArrayField.apply(b)))
                .appendHashFunction(HashFunctions.intArrayLengthHash(intArrayField));
    }

    /**
     * Create the Equality instance. This should be usually stored in a static final field of the class
     * which is compared.
     */
    public Equality<T> create() {
        return new EqualityImpl<>(
                type,
                equalsFunction,
                hashFunction.orElse(e -> 0));
    }


    private <F> ToIntFunction<T> hashFieldBy(Function<T, F> field, ToIntFunction<F> fieldHashFunction) {
        return e -> fieldHashFunction.applyAsInt(field.apply(e));
    }

    private <F> BiPredicate<T, T> compareFieldBy(Function<T, F> field, BiPredicate<F, F> comparisonFunction) {
        return (a,b) -> comparisonFunction.test(field.apply(a), field.apply(b));
    }

    private EqualityBuilder<T> appendEqualsFunction(BiPredicate<T, T> additionalEqualsFunction) {
        BiPredicate<T, T> newEqualsFunction = equalsFunction.and(additionalEqualsFunction);
        return new EqualityBuilder<>(type, newEqualsFunction, hashFunction);
    }

    private EqualityBuilder<T> appendHashFunction(ToIntFunction<T> additionalHashFunction) {
        ToIntFunction<T> combinedHashFunction = hashAggregator.aggregateHashFunctions(hashFunction, additionalHashFunction);
        return new EqualityBuilder<>(type, equalsFunction, combinedHashFunction);
    }

}
