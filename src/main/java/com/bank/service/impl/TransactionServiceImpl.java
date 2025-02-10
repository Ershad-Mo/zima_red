package com.bank.service.impl;

import java.util.UUID;

import com.bank.data.entity.Transaction;
import com.bank.data.databaserepository.TransactionRepository;
import com.bank.service.TransactionService;
import com.bank.service.dto.TransactionDto;
import com.bank.service.mapper.TransactionMapper;

public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl() {
        this.transactionRepository = new TransactionRepository();
    }

    @Override
    public void createTransaction(TransactionDto transactionDto) {
    
        Transaction transaction = TransactionMapper.getInstance().toTransaction(transactionDto);
        transaction.setId(generateTransactionId());

         transactionRepository.save(transaction);
    }

    public String generateTransactionId() {
        return UUID.randomUUID().toString();
    }



    
}
