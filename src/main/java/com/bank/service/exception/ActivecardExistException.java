package com.bank.service.exception;

public class ActivecardExistException extends RuntimeException {
    public ActivecardExistException() { 

        super("this account already has an active card");

    }
}
