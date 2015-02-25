package de.andrena.equalsBuilder;

import java.util.Objects;
import java.util.function.Function;

class EqualsCompareFieldsEquality<T, F> implements SameTypeEquality<T> {

    private final Function<T, F> field;

    public EqualsCompareFieldsEquality(Function<T, F> field) {
        this.field = field;
    }

    @Override
    public boolean areEqual(T thiz, T that) {
        return Objects.equals(field.apply(thiz), field.apply(that));
    }

    @Override
    public int hashCode(T thiz) {
        F val = field.apply(thiz);
        return val == null ? 0 : val.hashCode();
    }
}
