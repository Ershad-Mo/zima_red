package com.bank.service.impl;
import com.bank.data.connection.DatabaseConnection;
import com.bank.data.entity.BankAccount;
import com.bank.data.entity.Card;
import com.bank.data.entity.enumeration.TransactionType;
import com.bank.data.databaserepository.BankAccountrepository;
import com.bank.data.databaserepository.CardRepository;
import com.bank.service.CardTransferService;
import com.bank.service.TransactionService;
import com.bank.service.dto.TransactionDto;
import com.bank.service.exception.AmountNotValid;
import com.bank.service.exception.BankAccountNotFoundException;
import com.bank.service.exception.InsufficientBalanceException;
import com.bank.service.exception.SenderIsAlsoReciverException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

public class CardTransferServiceImpl implements CardTransferService {

    private final BankAccountrepository bankAccountrepository;
    private final TransactionService transactionService;
    private final CardRepository cardRepository;

    public CardTransferServiceImpl() {
        this.bankAccountrepository = new BankAccountrepository();
        this.transactionService = new TransactionServiceImpl();
        this.cardRepository = new CardRepository();


    }

    @Override
    public void transferWithCardNumber(String destinationCN, String sourceCN, Double amount) {

        if(amount <= 0)
            throw new AmountNotValid();

        if(amount.equals(null))
            throw new AmountNotValid();

        Card senderCardNumber = cardRepository.findFirstByCardNumber(sourceCN)
                .orElseThrow(BankAccountNotFoundException::new);

        BankAccount senderBankAccount = bankAccountrepository
                .findFirstByAccountNumber(senderCardNumber.getAccountNumber())
                .orElseThrow(BankAccountNotFoundException::new);

        if(amount > senderBankAccount.getBalance())
            throw new InsufficientBalanceException();

        Card destinationCardNumber = cardRepository.findFirstByCardNumber(destinationCN)
                .orElseThrow(BankAccountNotFoundException::new);

        BankAccount reciverBankAccount = bankAccountrepository
                .findFirstByAccountNumber(destinationCardNumber.getAccountNumber())
                .orElseThrow(BankAccountNotFoundException::new);

        if(reciverBankAccount.getAccountNumber().equals(senderBankAccount.getAccountNumber()))
            throw new SenderIsAlsoReciverException();

        Savepoint savepoint = null;
        Connection connection = DatabaseConnection.getConnection();
        try{
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint("before transfer");
            senderBankAccount.setBalance(senderBankAccount.getBalance() - amount);
            bankAccountrepository.updateBalance(senderBankAccount);

            reciverBankAccount.setBalance(reciverBankAccount.getBalance() + amount);
            bankAccountrepository.updateBalance(reciverBankAccount);

            TransactionDto transactionDto = new TransactionDto(senderCardNumber.getAccountNumber(),
                    destinationCardNumber.getAccountNumber(),
                    amount, TransactionType.WITHDRAW);

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
