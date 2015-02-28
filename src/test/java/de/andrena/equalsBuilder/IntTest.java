package de.andrena.equalsBuilder;

import org.junit.Test;

import static org.junit.Assert.*;

public class IntTest {
    static class IntHolder {
        private static final Equality<IntHolder> EQUALITY = Equality
                .forClass(IntHolder.class)
                .compares(IntHolder::getIntValue)
                .create();

        private final int intValue;

        IntHolder(int intValue) {
            this.intValue = intValue;
        }

        public int getIntValue() {
            return intValue;
        }

        @Override
        public boolean equals(Object obj) {
            return EQUALITY.areEqual(this, obj);
        }

        @Override
        public int hashCode() {
            return EQUALITY.hashCode(this);
        }
    }

    @Test
    public void comparesIntValues() {
        IntHolder i1 = new IntHolder(1);
        IntHolder i1_ = new IntHolder(1);
        IntHolder i2 = new IntHolder(2);

        assertTrue(i1.equals(i1));
        assertTrue(i1.equals(i1_));
        assertFalse(i1.equals(i2));
    }

    @Test
    public void intValueAsHash() {
        IntHolder i1 = new IntHolder(1);
        IntHolder i2 = new IntHolder(2);

        assertEquals(1, i1.hashCode());
        assertEquals(2, i2.hashCode());
    }
}
