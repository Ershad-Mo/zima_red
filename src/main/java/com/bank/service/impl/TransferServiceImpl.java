package com.bank.service.impl;
import com.bank.data.connection.DatabaseConnection;
import com.bank.data.entity.BankAccount;
import com.bank.data.entity.enumeration.TransactionType;
import com.bank.data.databaserepository.BankAccountrepository;
import com.bank.service.TransactionService;
import com.bank.service.TransferService;
import com.bank.service.dto.TransactionDto;
import com.bank.service.exception.AmountNotValid;
import com.bank.service.exception.BankAccountNotFoundException;
import com.bank.service.exception.InsufficientBalanceException;
import com.bank.service.exception.SenderIsAlsoReciverException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

public class TransferServiceImpl implements TransferService{

    private final BankAccountrepository bankAccountrepository;
    private final TransactionService transactionService;

    public TransferServiceImpl() {
        this.bankAccountrepository = new BankAccountrepository();
        this.transactionService = new TransactionServiceImpl();
    }

    @Override
    public void transferWithBankAccount(String destination, String source, Double amount) {

        if(amount <= 0)
            throw new AmountNotValid();

        if(amount.equals(null))
            throw new AmountNotValid();

        BankAccount senderBankAccount = bankAccountrepository.findFirstByAccountNumber(source)
            .orElseThrow(BankAccountNotFoundException::new);

        if(amount > senderBankAccount.getBalance())
            throw new InsufficientBalanceException();

        BankAccount reciverBankAccount = bankAccountrepository.findFirstByAccountNumber(destination)
            .orElseThrow(BankAccountNotFoundException::new);
            
        if(reciverBankAccount.getAccountNumber().equals(senderBankAccount.getAccountNumber()))
            throw new SenderIsAlsoReciverException();

        Savepoint savepoint = null;
        Connection connection = DatabaseConnection.getConnection();
        try {
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint("beforeTransfer");
            senderBankAccount.setBalance(senderBankAccount.getBalance() - amount);
            bankAccountrepository.updateBalance(senderBankAccount);

            reciverBankAccount.setBalance(reciverBankAccount.getBalance() + amount);
            bankAccountrepository.updateBalance(reciverBankAccount);

            TransactionDto transactionDto = new TransactionDto(source, destination, amount, TransactionType.WITHDRAW);
            transactionService.createTransaction(transactionDto);
            connection.commit();
        } catch (SQLException e) {
            if (savepoint != null) {
                try {
                   connection.rollback(savepoint);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
