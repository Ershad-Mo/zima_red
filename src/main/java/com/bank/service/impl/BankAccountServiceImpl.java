package com.bank.service.impl;

import java.security.SecureRandom;
import java.time.LocalDate;

import com.bank.data.entity.BankAccount;
import com.bank.data.databaserepository.BankAccountrepository;
import com.bank.service.BankAccountService;
import com.bank.service.exception.BankAccountNotFoundException;

public class BankAccountServiceImpl implements BankAccountService {
    private final SecureRandom random;
    private final BankAccountrepository bankAccountrepository; 

    public BankAccountServiceImpl() {
        this.random = new SecureRandom();
        this.bankAccountrepository = new BankAccountrepository();
    }
    
    @Override
    public void createBankAccountForCustomer(String customerUsername){
        BankAccount bankAccount = new BankAccount(null, customerUsername,
         generateAccountNumber(),
          LocalDate.now(), 
          0d);

          bankAccountrepository.save(bankAccount);
    }

    private String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();
        for(int i = 0; i < 5; i++) accountNumber.append(String.valueOf(random.nextInt(10)));
        return accountNumber.toString();
    }

    @Override
    public double getCustomerbalance(String customerUsername) {
        return bankAccountrepository.findFirstByUsername(customerUsername) 
            .orElseThrow(BankAccountNotFoundException::new)
            .getBalance();
    }

    @Override
    public String getAccountNumber(String customerUsername) {
        return bankAccountrepository.findFirstByUsername(customerUsername) 
            .orElseThrow(BankAccountNotFoundException::new)
            .getAccountNumber();
    }
}
