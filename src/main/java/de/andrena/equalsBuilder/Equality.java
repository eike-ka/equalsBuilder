package de.andrena.equalsBuilder;

/**
 * Represents a strategy for equals and hashCode implementation of objects.
 *
 * The intended use is to create a private static final instance of Equality
 * within a class and delegate the equals and hashCode implementation to
 * the quality:
 *
 * <pre>
 * @Override
 * public boolean equals(Object obj) {
 *      return EQUALITY.areEqual(this, obj);
 * }
 *
 * @Override
 * public int hashCode() {
 *      return EQUALITY.hashCode(this);
 * }
 * </pre>
 *
 * The equality instance can be created using the {@link de.andrena.equalsBuilder.EqualityBuilder} wich can
 * be created with the method {@link de.andrena.equalsBuilder.Equality#forClass(Class)}.
 */
public interface Equality<T> {

    /**
     * The equal implementation which is consistent to the {@link #hashCode(Object)}  method regarding the
     * equals-hashCode-contract.
     *
     * @return if both arguments are equal
     */
    abstract boolean areEqual(T thiz, Object that);

    /**
     * calculates a hashCode which is consistent with the {@link #areEqual(Object, Object)} method regarding
     * the equals-hashCode-contract.
     *
     * @return the hashCode of the given object
     */
    abstract int hashCode(T thiz);

    static <T> EqualityBuilder<T> forClass(Class<T> clazz) {
        return new EqualityBuilder<>(clazz);
    }
}
