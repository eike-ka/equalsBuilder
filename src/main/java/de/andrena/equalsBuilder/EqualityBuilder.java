package de.andrena.equalsBuilder;

import java.util.function.Function;

/**
 * Builder for {@link de.andrena.equalsBuilder.Equality} instances. Use
 * {@link de.andrena.equalsBuilder.Equality#forClass(Class)} to create a new builder instance.
 */
public class EqualityBuilder<T> {
    private final SameTypeEquality<T> equality;
    private final Class<T> type;

    EqualityBuilder(Class<T> type) {
        this(type, SameTypeEquality.nullEquality());
    }

    private EqualityBuilder(Class<T> type, SameTypeEquality<T> equality) {
        this.type = type;
        this.equality = equality;
    }

    /**
     * Adds the comparision of a field. The provided function must return the value of the field for a given instance.
     * This includes a nullsafe equals check of the field in the equality calculation and includes the hashCode of
     * the field in the overall hashCode calculation.
     */
    public <F> EqualityBuilder<T> compares(Function<T, F> field) {
        return compares(new EqualsCompareFieldsEquality<>(field));
    }

    public EqualityBuilder<T> deepCompares(Function<T, int[]> intArrayField) {
        return compares(new IntArrayDeepEquals<>(intArrayField));
    }

    /**
     * Adds a custom equal/hashCode calculation which will be included in the overall calculations.
     */
    public EqualityBuilder<T> compares(SameTypeEquality<T> newEqualityComponent) {
        return new EqualityBuilder<>(type, new ConcatenateEquality<>(equality, newEqualityComponent));
    }

    /**
     * Create the Equality instance. This should be usually stored in a static final field of the class
     * which is compared.
     */
    public Equality<T> create() {
        return new TypeCheckingEqualityWrapper<>(type, equality);
    }

}
