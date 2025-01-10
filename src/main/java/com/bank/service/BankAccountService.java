package com.bank.service;

public interface BankAccountService {
    void createBankAccountForCustomer(String customerUsername);
    
    double getCustomerbalance(String customerUsername);

    String getAccountNumber(String customerUsername);
    
}
