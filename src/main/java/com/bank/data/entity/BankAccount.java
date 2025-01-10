package com.bank.data.entity;

import java.time.LocalDate;
import java.util.Objects;

public class BankAccount {

    private final String customerUsername;
    private final String accountNumber;
    private final LocalDate creationDate;
    private double balance;

    public BankAccount(String customerUsername, String accountNumber, LocalDate creationDate, double balance) {
        this.customerUsername = customerUsername;
        this.accountNumber = accountNumber;
        this.creationDate = creationDate;
        this.balance = balance;
    }


    public String getCustomerUsername() {
        return customerUsername;
    }
    
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public LocalDate getDate() {
        return creationDate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(accountNumber, that.accountNumber);
    }

}
