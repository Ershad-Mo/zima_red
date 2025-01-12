package com.bank.service.exception;

public class SenderIsAlsoReciverException extends RuntimeException {
    public SenderIsAlsoReciverException() {
        super("sender is also reciver");
    }
}
