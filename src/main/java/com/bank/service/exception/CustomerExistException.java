package com.bank.service.exception;

public class CustomerExistException extends RuntimeException {

    
    public CustomerExistException() {
        super("this customer already exist");
    }
    
}
