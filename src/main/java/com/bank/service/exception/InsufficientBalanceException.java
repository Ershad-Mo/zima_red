package com.bank.service.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("insufficient Balance");
    }
}
