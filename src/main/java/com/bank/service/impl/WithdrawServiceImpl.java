package com.bank.service.impl;

import com.bank.data.entity.BankAccount;
import com.bank.data.entity.enumeration.TransactionType;
import com.bank.data.databaserepository.BankAccountrepository;
import com.bank.service.TransactionService;
import com.bank.service.WithdrawService;
import com.bank.service.dto.TransactionDto;
import com.bank.service.exception.AmountNotValid;
import com.bank.service.exception.BankAccountNotFoundException;
import com.bank.service.exception.InsufficientBalanceException;

public class WithdrawServiceImpl implements WithdrawService{
    private final BankAccountrepository bankAccountrepository;
    private final TransactionService transactionService;
    

    public WithdrawServiceImpl() {
        this.bankAccountrepository = new BankAccountrepository();
        this.transactionService = new TransactionServiceImpl();
    }

    @Override
    public void withdraw(String accountNumber, Double amount) {

        if(amount <= 0)
            throw new AmountNotValid();

        if(amount.equals(null))
            throw new AmountNotValid();

        BankAccount bankAccount = bankAccountrepository.findFirstByAccountNumber(accountNumber)
            .orElseThrow(() -> new BankAccountNotFoundException());

            if(amount > bankAccount.getBalance())
                throw new InsufficientBalanceException(); 
                
            bankAccount.setBalance(bankAccount.getBalance() - amount);
            bankAccountrepository.updateBalance(bankAccount);

            TransactionDto transactionDto = new TransactionDto(accountNumber, accountNumber, amount, TransactionType.WITHDRAW);
            transactionService.createTransaction(transactionDto);
    }
}
