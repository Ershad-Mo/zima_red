package com.bank.service;

public interface CardTransferService {
    void transferWithCardNumber(String destination, String source, Double amount);
}
