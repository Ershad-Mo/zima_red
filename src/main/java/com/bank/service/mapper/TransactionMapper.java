package com.bank.service.mapper;

import java.util.Objects;

import com.bank.data.entity.Transaction;
import com.bank.service.dto.TransactionDto;

public class TransactionMapper {
    private static TransactionMapper instance;

    private TransactionMapper() {
        
    }

    public static TransactionMapper getInstance() {
        if (Objects.isNull(instance))
            instance = new TransactionMapper();
        
        return instance;
    }

    public Transaction toTransaction(TransactionDto transactionDto) {
        return new Transaction(
            transactionDto.getSourceAccountnumber(), 
            transactionDto.getDistinationAccountNumber(),
            transactionDto.getAmount(),
            transactionDto.getType());
    }
}
