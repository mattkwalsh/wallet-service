package com.interview.wallet.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Request object for creating a new wallet
 */
public class WalletCreateRequest {

    @NotNull
    @Size(min = 1, max = 128)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
