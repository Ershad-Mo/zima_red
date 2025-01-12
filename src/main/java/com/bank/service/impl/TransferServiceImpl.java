package com.bank.service.impl;
import com.bank.data.entity.BankAccount;
import com.bank.data.repository.BankAccountrepository;
import com.bank.service.TransferService;
import com.bank.service.exception.BankAccountNotFoundException;
import com.bank.service.exception.InsufficientBalanceException;
import com.bank.service.exception.SenderIsAlsoReciverException;

public class TransferServiceImpl implements TransferService{

    private final BankAccountrepository bankAccountrepository;

    public TransferServiceImpl() {
        this.bankAccountrepository = new BankAccountrepository();
    }

    @Override
    public void transfer(String reciverAccountNumber, String senderAccountNumber, double amount) {

        BankAccount senderBankAccount = bankAccountrepository.findFirstByAccountNumber(senderAccountNumber)
            .orElseThrow(() -> new BankAccountNotFoundException());

        BankAccount reciverBankAccount = bankAccountrepository.findFirstByAccountNumber(reciverAccountNumber)
            .orElseThrow(() -> new BankAccountNotFoundException());

        if(amount > senderBankAccount.getBalance())
            throw new InsufficientBalanceException();
            
        if(reciverBankAccount.getAccountNumber().equals(senderBankAccount.getAccountNumber()))
            throw new SenderIsAlsoReciverException();
            
        senderBankAccount.setBalance(senderBankAccount.getBalance() - amount);
        bankAccountrepository.save(senderBankAccount);

        reciverBankAccount.setBalance(reciverBankAccount.getBalance() + amount);
        bankAccountrepository.save(reciverBankAccount);
            
    }
}
