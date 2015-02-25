package de.andrena.equalsBuilder;

import org.junit.Test;

import static org.junit.Assert.*;

public class EqualityBuilderTest {

    @Test
    public void comparesField() {
        SingleAttributeObject a = new SingleAttributeObject("a");
        SingleAttributeObject b = new SingleAttributeObject("b");

        assertTrue(a.equals(a));
        assertFalse(a.equals(b));
    }


    @Test
    public void differentTypesAreNotEqual() {
        SingleAttributeObject a = new SingleAttributeObject("a");
        //anonymous class to have a different type
        SingleAttributeObject aa = new SingleAttributeObject("a") {};

        assertTrue(a.equals(a));
        assertFalse(a.equals(aa));
    }


    @Test
    public void comparesTwoFields() {
        TwoAttributesObject a1 = new TwoAttributesObject("a", 1);
        TwoAttributesObject a2 = new TwoAttributesObject("a", 2);
        TwoAttributesObject b1 = new TwoAttributesObject("b", 1);

        assertTrue(a1.equals(a1));
        assertFalse(a1.equals(b1));

        assertTrue(b1.equals(b1));
        assertFalse(a1.equals(a2));
    }

    @Test
    public void handlesNull() {
        SingleAttributeObject a = new SingleAttributeObject(null);
        SingleAttributeObject b = new SingleAttributeObject("b");

        assertTrue(a.equals(a));
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }

    @Test
    public void hashCodes() {
        ObjWithHash hash0 = new ObjWithHash(0);
        ObjWithHash hash1 = new ObjWithHash(1);
        ObjWithHash hash2 = new ObjWithHash(2);

        assertEquals(1, new HashObjHolder(hash1, hash0).hashCode());
        assertEquals(2, new HashObjHolder(hash2, hash0).hashCode());

        assertEquals(1 + 1 * 31, new HashObjHolder(hash1, hash1).hashCode());
        assertEquals(2 + 2 * 31, new HashObjHolder(hash2, hash2).hashCode());
    }

}