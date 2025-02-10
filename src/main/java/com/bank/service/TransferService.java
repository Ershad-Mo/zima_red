package com.bank.service;

public interface TransferService {
    void transferWithBankAccount(String reciverAccountNumber, String senderAccountNumber, Double amount);
}
