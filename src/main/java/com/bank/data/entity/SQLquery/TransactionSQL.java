package com.bank.data.entity.SQLquery;

import com.bank.data.entity.enumeration.TransactionType;


public final class TransactionSQL {
    public static final String INSERT_TRANSACTION
            = "insert into transaction" +
            "(source_account, date, amount, destination_account, type)" +
            " values (?, ?, ?, ?, ?)";
}
