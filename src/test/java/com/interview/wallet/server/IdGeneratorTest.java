package com.interview.wallet.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdGeneratorTest {

    @Test
    public void testIdGenerator(){
        IdGenerator idGenerator = new IdGenerator();
        assertNotNull(idGenerator.getId());
    }

}