package com.interview.wallet.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

/**
 * Model object for storing data in Mongo
 */
@Document("wallets")
public class Wallet {

    public Wallet(){

    }

    public Wallet(String id, String name, List<Item> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }

    @Id
    private String id;

    private String name;

    private List<Item> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Objects.equals(id, wallet.id) && Objects.equals(name, wallet.name) && Objects.equals(items, wallet.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, items);
    }
}
