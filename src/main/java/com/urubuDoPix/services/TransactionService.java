package com.urubuDoPix.services;

import com.urubuDoPix.dtos.TransactionDTO;
import com.urubuDoPix.model.Transaction;
import com.urubuDoPix.model.User;
import com.urubuDoPix.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

        this.validateTransaction(sender, data.amount());

        sender.setBalance(sender.getBalance().subtract(data.amount()));
        receiver.setBalance(receiver.getBalance().add(data.amount()));
        this.transactionRepository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        return newTransaction;
    }

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getBalance().compareTo(amount)<0){
            throw new Exception("Saldo insuficiente!");
        }
    }

    public Optional<Transaction> getTransaction(UUID id){
        return this.transactionRepository.findTransactionById(id);
    }

    public List<Transaction> getAllTransactions(){
        return this.transactionRepository.findAll();
    }

    public String deleteTransaction(UUID id) {
        this.transactionRepository.deleteById(id);
        return "Transação deletada!";
    }
}
