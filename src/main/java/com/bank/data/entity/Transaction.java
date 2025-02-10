package com.bank.data.entity;

import java.time.LocalDateTime;

import com.bank.data.entity.enumeration.TransactionType;

public class Transaction {
    private  String id;
    private final String sourceAccountNumber;
    private final LocalDateTime creationTime;
    private final double amount;
    private final String distinationAccountNumber;
    private final TransactionType type;

    public Transaction(String id, String sourceAccountNumber, LocalDateTime creationTime, double amount,
            String distinationAccountNumber, TransactionType type) {
        this.id = id;
        this.sourceAccountNumber = sourceAccountNumber;
        this.creationTime = creationTime;
        this.amount = amount;
        this.distinationAccountNumber = distinationAccountNumber;
        this.type = type;
    } 

    public Transaction( String sourceAccountNumber, String distinationAccountNumber, double amount, TransactionType type) {
        this(null, sourceAccountNumber, LocalDateTime.now(), amount, distinationAccountNumber, type);
    }

    public void setId(String id) {
        this.id = id;
    }


    public TransactionType getType() {
        return type;
    }

    public String getDistinationAccountNumber() {
        return distinationAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public String getId() {
        return id;
    }
}
