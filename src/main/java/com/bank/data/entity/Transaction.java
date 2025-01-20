package com.bank.data.entity;

import java.time.LocalDate;

public class Transaction {
    private final String customerUsername;
    private final String accountNumber;
    private final LocalDate creationDate;

    public Transaction(String customerUsername, String accountNumber, LocalDate creationDate) {
        this.customerUsername = customerUsername;
        this.accountNumber = accountNumber;
        this.creationDate = creationDate;
    }
}
