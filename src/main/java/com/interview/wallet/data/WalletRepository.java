package com.interview.wallet.data;

import com.interview.wallet.model.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Enables a Wallet-specific version of a MongoRepository
 */
public interface WalletRepository extends MongoRepository<Wallet, String> {

}
