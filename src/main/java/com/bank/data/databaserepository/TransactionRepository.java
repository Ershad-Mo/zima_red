package com.bank.data.databaserepository;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import com.bank.data.connection.DatabaseConnection;
import com.bank.data.entity.SQLquery.TransactionSQL;
import com.bank.data.entity.Transaction;

public class TransactionRepository {

    public void save(Transaction transaction) {
        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(TransactionSQL.INSERT_TRANSACTION)) {
            stmt.setString(1, transaction.getSourceAccountNumber());
            stmt.setString(2, String.valueOf(LocalDate.now()));
            stmt.setDouble(3, transaction.getAmount());
            stmt.setString(4, transaction.getDistinationAccountNumber());
            stmt.setString(5, String.valueOf(transaction.getType()));
            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
