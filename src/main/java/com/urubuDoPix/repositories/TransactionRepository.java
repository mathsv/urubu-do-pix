package com.urubuDoPix.repositories;

import com.urubuDoPix.model.Transaction;
import com.urubuDoPix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Optional<Transaction> findTransactionById(UUID id);
}
