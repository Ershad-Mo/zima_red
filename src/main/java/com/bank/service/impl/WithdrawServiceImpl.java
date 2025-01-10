package com.bank.service.impl;

import com.bank.data.entity.BankAccount;
import com.bank.data.repository.BankAccountrepository;
import com.bank.service.WithdrawService;
import com.bank.service.exception.BankAccountNotFoundException;
import com.bank.service.exception.InsufficientBalanceException;

public class WithdrawServiceImpl implements WithdrawService{
    private final BankAccountrepository bankAccountrepository;

    public WithdrawServiceImpl() {
        this.bankAccountrepository = new BankAccountrepository();
    }

    @Override
    public void withdraw(String accountNumber, double amount) {

        BankAccount bankAccount = bankAccountrepository.findFirstByAccountNumber(accountNumber)
            .orElseThrow(() -> new BankAccountNotFoundException());

            if(amount > bankAccount.getBalance())
                throw new InsufficientBalanceException(); 
                
            bankAccount.setBalance(bankAccount.getBalance() - amount);
            bankAccountrepository.save(bankAccount);
    }
}
