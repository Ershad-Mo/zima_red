package com.bank.service.impl;

import com.bank.data.entity.BankAccount;
import com.bank.data.entity.enumeration.TransactionType;
import com.bank.data.databaserepository.BankAccountrepository;
import com.bank.service.DepositService;
import com.bank.service.TransactionService;
import com.bank.service.dto.TransactionDto;
import com.bank.service.exception.AmountNotValid;
import com.bank.service.exception.BankAccountNotFoundException;

public class DepositServiceImpl implements DepositService {

    private final BankAccountrepository bankAccountrepository;
    private final TransactionService transactionService;

    public DepositServiceImpl() {
        this.transactionService = new TransactionServiceImpl();
        this.bankAccountrepository = new BankAccountrepository();
    }

    
    @Override
    public void deposit(String accountNumber, Double amount) {

        if(amount <= 0)
            throw new AmountNotValid();

        if(amount.equals(null))
            throw new AmountNotValid();

        BankAccount bankAccount = bankAccountrepository.findFirstByAccountNumber(accountNumber)
            .orElseThrow(() -> new BankAccountNotFoundException());

            bankAccount.setBalance(bankAccount.getBalance() + amount);
            bankAccountrepository.updateBalance(bankAccount);

            TransactionDto transactionDto = new TransactionDto(accountNumber, accountNumber, amount, TransactionType.WITHDRAW);
            transactionService.createTransaction(transactionDto);
    }
}
