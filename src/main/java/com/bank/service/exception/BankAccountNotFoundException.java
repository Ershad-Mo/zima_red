package com.bank.service.exception;

public class BankAccountNotFoundException extends RuntimeException {

    public BankAccountNotFoundException() {
        super("bank account not found");
    }
    
}
