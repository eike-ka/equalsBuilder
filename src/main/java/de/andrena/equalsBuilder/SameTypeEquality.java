package de.andrena.equalsBuilder;

/**
 * Strategy for doing a (partial)equality check for two objects of the same type and a
 * corresponding hashCode calculation which must be consistent regarding equals-hashCode-contract.
 */
public interface SameTypeEquality<T>  {
    boolean areEqual(T thiz, T that);
    int hashCode(T thiz);

    final SameTypeEquality NULL = new SameTypeEquality() {

        @Override
        public boolean areEqual(Object thiz, Object that) {
            return true;
        }

        @Override
        public int hashCode(Object thiz) {
            return 0;
        }
    };

    static <T> SameTypeEquality<T> nullEquality() {
        return (SameTypeEquality<T>) NULL;
    }
}
