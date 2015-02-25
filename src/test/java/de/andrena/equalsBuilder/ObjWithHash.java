package de.andrena.equalsBuilder;

/**
 * Simple utility to create objects with specified hash value. Used for hash calculation tests.
 */
public class ObjWithHash {
    private final int hash;

    public ObjWithHash(int hash) {
        this.hash = hash;
    }

    @Override
    public int hashCode() {
        return hash;
    }
}
