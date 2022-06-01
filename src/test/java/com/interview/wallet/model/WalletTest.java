package com.interview.wallet.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    final static private Item ITEM = new Item("coins", 15);
    final static private Wallet WALLET = new Wallet("1234", "test", List.of(ITEM));

    @Test
    public void testSameObject(){


        assertEquals(WALLET, WALLET);
    }

    @Test
    public void testNullObject(){
        assertNotEquals(null, WALLET);
    }

    @Test
    public void testDifferentClass(){
        assertNotEquals(WALLET, "wallet");
    }

    @Test
    public void testValuesEqual(){
        assertEquals(WALLET, new Wallet("1234", "test", List.of(ITEM)));
    }

    @Test
    public void testIdMismatch(){
        assertNotEquals(WALLET, new Wallet("nope", "test", List.of(ITEM)));
    }

    @Test
    public void testNameMismatch(){
        assertNotEquals(WALLET, new Wallet("1234", "otherTest", List.of(ITEM)));
    }

    @Test
    public void testItemsMismatch(){
        assertNotEquals(WALLET, new Wallet("1234", "test", new ArrayList<>()));
    }

    @Test
    public void testHashCode(){
        assertEquals(Objects.hash("1234", "test", List.of(ITEM)), WALLET.hashCode());
    }

}