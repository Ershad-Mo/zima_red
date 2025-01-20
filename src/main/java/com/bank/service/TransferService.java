package com.bank.service;

public interface TransferService {
    void transfer(String reciverAccountNumber, String senderAccountNumber, Double amount);
}
