package com.urubuDoPix.controller;

import com.urubuDoPix.dtos.TransactionDTO;
import com.urubuDoPix.model.Transaction;
import com.urubuDoPix.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO data) throws Exception {

        Transaction newTransaction = this.transactionService.createTransaction(data);
        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        return new ResponseEntity<>(this.transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancelTransaction(@PathVariable UUID id) throws Exception {
        Optional<Transaction> optionalTransaction = this.transactionService.getTransaction(id);
        Transaction transaction;
        if (optionalTransaction.isPresent()) {
            transaction = optionalTransaction.get();
        }else throw new Exception("Transação não encontrada");
        return new ResponseEntity<>(this.transactionService.cancelTransaction(transaction), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Transaction>> getTransactionById(@PathVariable UUID id){
        Optional<Transaction> transaction = this.transactionService.getTransaction(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransactionById(@PathVariable UUID id){
        String transaction = this.transactionService.deleteTransaction(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
}
