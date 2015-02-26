package de.andrena.equalsBuilder;

public class ValueHolder<T> {
    private final T value;

    public ValueHolder(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
