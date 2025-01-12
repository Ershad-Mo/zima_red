package com.bank.data.repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.bank.data.entity.BankAccount;

public class BankAccountrepository {
    private final static Set<BankAccount> bankAccounts = new HashSet<>();

    public void save(BankAccount bankAccount){
        bankAccounts.add(bankAccount);
    }

    public Optional<BankAccount> findFirstByUsername(String username) {
        return bankAccounts.stream()
            .filter(bankAccount -> bankAccount.getCustomerUsername().equals(username))
            .findFirst();
    }

    public Optional<BankAccount> findFirstByAccountNumber(String accountNumber) {
        return bankAccounts.stream()
            .filter(bankAccount -> bankAccount.getAccountNumber().equals(accountNumber))
            .findFirst();
    }

}
