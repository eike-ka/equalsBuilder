package de.andrena.equalsBuilder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntArraysTest {
    static class IntArrayHolder extends ValueHolder<int[]> {
        public IntArrayHolder(int[] value) {
            super(value);
        }
    }

    private static final Equality<IntArrayHolder> INT_ARRAY_EQUALITY = Equality
            .forClass(IntArrayHolder.class)
            .deepCompares(IntArrayHolder::getValue)
            .create();

    @Test
    public void deepCompareIntArrays() {
        IntArrayHolder int1 = new IntArrayHolder(new int[]{1});
        IntArrayHolder int2 = new IntArrayHolder(new int[]{2});
        IntArrayHolder intNull = new IntArrayHolder(null);

        assertTrue(INT_ARRAY_EQUALITY.areEqual(int1, int1));
        assertTrue(INT_ARRAY_EQUALITY.areEqual(intNull, intNull));

        assertFalse(INT_ARRAY_EQUALITY.areEqual(int1, int2));
        assertFalse(INT_ARRAY_EQUALITY.areEqual(int1, intNull));
        assertFalse(INT_ARRAY_EQUALITY.areEqual(intNull, int1));
    }

    @Test
    public void hashUsesLength() {
        IntArrayHolder int1 = new IntArrayHolder(new int[]{1});
        IntArrayHolder int2 = new IntArrayHolder(new int[]{1, 1});
        IntArrayHolder intNull = new IntArrayHolder(null);

        assertEquals(1, INT_ARRAY_EQUALITY.hashCode(int1));
        assertEquals(2, INT_ARRAY_EQUALITY.hashCode(int2));
        assertEquals(0, INT_ARRAY_EQUALITY.hashCode(intNull));

    }
}
