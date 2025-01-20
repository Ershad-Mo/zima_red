package com.bank.data.repository;

import java.util.HashSet;
import java.util.Set;

import com.bank.data.entity.Transaction;

public class TransactionRepository {
    private final static Set<Transaction> transactions = new HashSet<>();

    public void save(Transaction transaction) {
        transactions.add(transaction);
    }
}
