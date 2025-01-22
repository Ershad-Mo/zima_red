package com.bank.service;

import com.bank.service.dto.TransactionDto;

public interface TransactionService {
    void createTransaction(TransactionDto transactionDto);
}
