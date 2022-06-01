package com.interview.wallet.model;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    final static private Item ITEM = new Item("coins", 15);

    @Test
    public void testSameObject(){


        assertEquals(ITEM, ITEM);
    }

    @Test
    public void testNullObject(){
        assertNotEquals(null, ITEM);
    }

    @Test
    public void testDifferentClass(){
        assertNotEquals(ITEM, "Item");
    }

    @Test
    public void testValuesEqual(){
        assertEquals(ITEM, new Item("coins", 15));
    }

    @Test
    public void testTypeMismatch(){
        assertNotEquals(ITEM, new Item("hearts", 15));
    }

    @Test
    public void testAmountMismatch(){
        assertNotEquals(ITEM, new Item("coins", 30));
    }

    @Test
    public void testHashCode(){
        assertEquals(Objects.hash("coins", 15), ITEM.hashCode());
    }

}