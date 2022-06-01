package com.interview.wallet.server;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Generator for Wallet IDs. Mostly in its own class for testing
 */
@Component
public class IdGenerator {

    public String getId(){
        return UUID.randomUUID().toString();
    }
}
