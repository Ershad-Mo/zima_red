package com.bank.service.exception;

public class CustomerNotExistException extends RuntimeException {

    public CustomerNotExistException() {
        super("Customer dose not exist");
    }
    
}
