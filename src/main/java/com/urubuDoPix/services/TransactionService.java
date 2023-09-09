package com.urubuDoPix.services;

import com.urubuDoPix.dtos.TransactionDTO;
import com.urubuDoPix.model.Transaction;
import com.urubuDoPix.model.User;
import com.urubuDoPix.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Transaction createTransaction(TransactionDTO data) throws Exception {
        User sender = this.userService.findUserById(data.sender_id());
        User receiver = this.userService.findUserById(data.receiver_id());

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(data.amount());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(data.amount()));
        receiver.setBalance(receiver.getBalance().add(data.amount()));
        this.transactionRepository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        return newTransaction;
    }
}
