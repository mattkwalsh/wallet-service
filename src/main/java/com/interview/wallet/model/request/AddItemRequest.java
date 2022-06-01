package com.interview.wallet.model.request;

import javax.validation.constraints.*;

/**
 * Request object for adding an item to a wallet
 */
public class AddItemRequest {

    @NotNull
    @Pattern(regexp = "coins|hearts|bombs|erasers|notes")
    private String type;

    @NotNull
    @Min(1)
    @Max(1000000)
    private Integer amount;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
