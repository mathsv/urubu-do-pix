package com.urubuDoPix.controller;

import com.urubuDoPix.dtos.TransactionDTO;
import com.urubuDoPix.model.Transaction;
import com.urubuDoPix.model.User;
import com.urubuDoPix.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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
}
