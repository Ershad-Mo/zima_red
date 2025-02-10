package com.bank.data.databaserepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.bank.data.connection.DatabaseConnection;
import com.bank.data.entity.BankAccount;
import com.bank.data.entity.Card;
import com.bank.data.entity.SQLquery.BankAccountSQL;
import com.bank.data.entity.enumeration.CardActivity;

public class BankAccountrepository {

    public void save(BankAccount bankAccount){
        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(BankAccountSQL.INSERT_BANK_ACCOUNT);) {
            stmt.setString(1, bankAccount.getCustomerUsername());
            stmt.setString(2, bankAccount.getAccountNumber());
            stmt.setString(3, String.valueOf(LocalDate.now()));
            stmt.setString(4, String.valueOf(bankAccount.getBalance()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateBalance(BankAccount bankAccount){
        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(BankAccountSQL.UPDATE_BALANCE);) {
            stmt.setDouble(1, bankAccount.getBalance());
            stmt.setInt(2, bankAccount.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Optional<BankAccount> findFirstByUsername(String username) {
        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(BankAccountSQL.FIND_BANK_ACCOUNT_BY_USERNAME)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                Integer id = rs.getInt("id");
                String accountNumber = rs.getString("account_number");
                String customerUsername = rs.getString("customer_username");
                String creationDate = rs.getString("creation_date");
                double balance = rs.getDouble("balance");
                BankAccount bankAccount = new BankAccount(id, customerUsername, accountNumber, LocalDate.parse(creationDate, formatter), balance);
                return Optional.of(bankAccount);
            }
            return Optional.empty();


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Optional<BankAccount> findFirstByAccountNumber(String accountNumber) {
        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(BankAccountSQL.FIND_BANK_ACCOUNT_BY_ACCOUNT_NUMBER)) {
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                Integer id = rs.getInt("id");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String accountNum = rs.getString("account_number");
                String customerUsername = rs.getString("customer_username");
                String creationDate = rs.getString("creation_date");
                double balance = rs.getDouble("balance");
                BankAccount bankAccount = new BankAccount(id, customerUsername, accountNum, LocalDate.parse(creationDate, formatter), balance);
                return Optional.of(bankAccount);
            }
            return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
