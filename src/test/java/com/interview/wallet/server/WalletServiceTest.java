package com.interview.wallet.server;

import com.interview.wallet.data.WalletRepository;
import com.interview.wallet.model.Item;
import com.interview.wallet.model.Wallet;
import com.interview.wallet.model.request.AddItemRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WalletServiceTest {

    private static final String WALLET_NAME = "test_wallet";
    private static final String ID = "cebef578-7a28-4b59-b595-05807d47a560";
    private static final String TYPE = "coins";
    private static final String SECOND_TYPE = "hearts";
    private static final Integer AMOUNT = 25;
    private static final Integer NEW_AMOUNT = 30;
    @Mock
    private WalletRepository walletRepository;
    @Mock
    private IdGenerator idGenerator;

    @Test
    public void testCreateWallet() {
        // Setup
        when(idGenerator.getId()).thenReturn(ID);

        // Run
        WalletService service = new WalletService(walletRepository, idGenerator);
        service.createWallet(WALLET_NAME);

        // Validate
        verify(walletRepository).insert(new Wallet(ID, WALLET_NAME, new ArrayList<>()));
    }

    @Test
    public void testGetWallet() {
        // Setup
        Wallet expectedWallet = new Wallet(ID, WALLET_NAME, new ArrayList<>());
        when(walletRepository.findById(anyString())).thenReturn(Optional.of(expectedWallet));

        // Run
        WalletService service = new WalletService(walletRepository, idGenerator);
        Wallet result = service.getWallet(ID);

        // Validate
        assertEquals(expectedWallet, result);
        verify(walletRepository).findById(ID);
    }

    @Test
    public void testGetWalletNotFound() {
        // Setup
        when(walletRepository.findById(anyString())).thenReturn(Optional.empty());

        // Run
        WalletService service = new WalletService(walletRepository, idGenerator);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> service.getWallet(ID));

        // Validate
        assertEquals(exception.getStatus(), HttpStatus.NOT_FOUND);
        verify(walletRepository).findById(ID);
    }

    @Test
    public void testAddItemNew() {
        //Setup
        Wallet foundWallet = new Wallet(ID, WALLET_NAME, new ArrayList<>());
        Wallet expectedWallet = new Wallet(ID, WALLET_NAME, List.of(new Item(TYPE, AMOUNT)));
        when(walletRepository.findById(anyString())).thenReturn(Optional.of(foundWallet));

        AddItemRequest request = new AddItemRequest();
        request.setType(TYPE);
        request.setAmount(AMOUNT);

        // Run
        WalletService service = new WalletService(walletRepository, idGenerator);
        service.addItem(ID, request);

        // Validate
        verify(walletRepository).findById(ID);
        verify(walletRepository).save(expectedWallet);
    }

    @Test
    public void testAddItemAlreadyExists() {
        //Setup
        Wallet foundWallet = new Wallet(ID, WALLET_NAME, new ArrayList<>(List.of(new Item(TYPE, AMOUNT))));
        Wallet expectedWallet = new Wallet(ID, WALLET_NAME, new ArrayList<>(List.of(new Item(TYPE, NEW_AMOUNT))));
        when(walletRepository.findById(anyString())).thenReturn(Optional.of(foundWallet));

        AddItemRequest request = new AddItemRequest();
        request.setType(TYPE);
        request.setAmount(NEW_AMOUNT);

        // Run
        WalletService service = new WalletService(walletRepository, idGenerator);
        service.addItem(ID, request);

        // Validate
        verify(walletRepository).findById(ID);
        verify(walletRepository).save(expectedWallet);
    }

    @Test
    public void testAddItemMissingWallet() {
        // Setup
        when(walletRepository.findById(anyString())).thenReturn(Optional.empty());

        AddItemRequest request = new AddItemRequest();
        request.setType(TYPE);
        request.setAmount(NEW_AMOUNT);

        // Run
        WalletService service = new WalletService(walletRepository, idGenerator);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> service.addItem(ID, request));

        // Validate
        assertEquals(exception.getStatus(), HttpStatus.NOT_FOUND);
        verify(walletRepository).findById(ID);
        verify(walletRepository, never()).save(any());

    }

    @Test
    public void testGetItem() {
        // Setup
        Item expectedItem = new Item(TYPE, AMOUNT);
        Wallet foundWallet = new Wallet(ID, WALLET_NAME, new ArrayList<>(List.of(expectedItem)));
        when(walletRepository.findById(anyString())).thenReturn(Optional.of(foundWallet));

        // Run
        WalletService service = new WalletService(walletRepository, idGenerator);
        Item result = service.getItem(ID, TYPE);

        // Validate
        assertEquals(expectedItem, result);
        verify(walletRepository).findById(ID);

    }

    @Test
    public void testGetItemWalletNotFound() {
        // Setup
        when(walletRepository.findById(anyString())).thenReturn(Optional.empty());

        // Run
        WalletService service = new WalletService(walletRepository, idGenerator);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> service.getItem(ID, TYPE));

        // Validate
        verify(walletRepository).findById(ID);
    }

    @Test
    public void testGetItemNotFound() {
        // Setup
        Item expectedItem = new Item(SECOND_TYPE, AMOUNT);
        Wallet foundWallet = new Wallet(ID, WALLET_NAME, new ArrayList<>(List.of(expectedItem)));
        when(walletRepository.findById(anyString())).thenReturn(Optional.of(foundWallet));

        // Run
        WalletService service = new WalletService(walletRepository, idGenerator);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> service.getItem(ID, TYPE));

        // Validate
        verify(walletRepository).findById(ID);
    }


}