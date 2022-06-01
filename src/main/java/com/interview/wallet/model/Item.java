package com.interview.wallet.model;

import java.util.Objects;

/**
 * Model object for storing data in Mongo
 */
public class Item {

    public Item(){

    }
    public Item(String type, Integer amount) {
        this.type = type;
        this.amount = amount;
    }

    private String type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(type, item.type) && Objects.equals(amount, item.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, amount);
    }
}
