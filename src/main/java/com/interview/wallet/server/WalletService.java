package com.interview.wallet.server;

import com.interview.wallet.data.WalletRepository;
import com.interview.wallet.model.Item;
import com.interview.wallet.model.Wallet;
import com.interview.wallet.model.request.AddItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class WalletService {

    private final WalletRepository walletRepository;
    private final IdGenerator idGenerator;

    @Autowired
    public WalletService(WalletRepository walletRepository, IdGenerator idGenerator) {
        this.walletRepository = walletRepository;
        this.idGenerator = idGenerator;
    }

    public Wallet createWallet(String name) {
        Wallet wallet = new Wallet();
        wallet.setName(name);
        wallet.setItems(new ArrayList<>());
        wallet.setId(idGenerator.getId());
        return walletRepository.insert(wallet);
    }

    public Wallet getWallet(String id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        return wallet.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Item addItem(String id, AddItemRequest request) {
        Wallet wallet = getWallet(id);
        // See if we can find the item type. If the item type already exists,
        // update it. Else add a new one.
        Optional<Item> optionalItem = wallet.getItems().stream()
                .filter(item -> request.getType().equals(item.getType())).findFirst();
        Item returnedItem;
        if (optionalItem.isPresent()) {
            returnedItem = optionalItem.get();
            returnedItem.setAmount(request.getAmount());
        } else {
            returnedItem = new Item(request.getType(), request.getAmount());
            wallet.getItems().add(returnedItem);
        }
        walletRepository.save(wallet);
        return returnedItem;
    }

    public Item getItem(String id, String itemType) {
        Wallet wallet = getWallet(id);
        return wallet.getItems().stream()
                .filter(item -> itemType.equals(item.getType()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item type not found"));
    }
}
