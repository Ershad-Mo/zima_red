package com.bank.service.dto;

import com.bank.data.entity.enumeration.TransactionType;

public class TransactionDto {
    private final String sourceAccountNumber;
    private final String distinationAccountNumber;
    private final double amount;
    private final TransactionType type;
    
    

    public TransactionDto(String sourceAccountNumber, String distinationAccountNumber, double amount,
            TransactionType type) {
        this.sourceAccountNumber = sourceAccountNumber;
        this.amount = amount;
        this.distinationAccountNumber = distinationAccountNumber;
        this.type = type;
    }

    public TransactionType getType() {
        return type;
    }

    public String getSourceAccountnumber() {
        return sourceAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getDistinationAccountNumber() {
        return distinationAccountNumber;
    }

}
