package com.interview.wallet.controller;

import com.interview.wallet.model.Item;
import com.interview.wallet.model.Wallet;
import com.interview.wallet.model.request.AddItemRequest;
import com.interview.wallet.model.request.WalletCreateRequest;
import com.interview.wallet.server.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }

    @PostMapping("/wallet")
    public Wallet createWallet(@Valid @RequestBody WalletCreateRequest request) {
        return walletService.createWallet(request.getName());
    }

    @GetMapping("/wallet/{id}")
    public Wallet getWallet(@Valid @PathVariable String id){
        return walletService.getWallet(id);
    }

    @PutMapping ("/wallet/{id}")
    public Item addItem( @PathVariable String id, @Valid @RequestBody AddItemRequest request) {
        return walletService.addItem(id, request);
    }

    @GetMapping("/wallet/{id}/type/{type}")
    public Item getItem(@PathVariable @Pattern(regexp = "coins|hearts|bombs|erasers|noes") String id, @Valid @PathVariable String type) {
        return walletService.getItem(id, type);
    }
}
