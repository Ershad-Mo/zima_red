package com.bank.data.databaserepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.bank.data.connection.DatabaseConnection;
import com.bank.data.entity.Card;
import com.bank.data.entity.SQLquery.CardSQL;
import com.bank.data.entity.enumeration.CardActivity;

public class CardRepository {

    public void save(Card card) {
        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(CardSQL.INSERT_CARD)) {
        stmt.setString(1, card.getCvv2());
        stmt.setString(2, card.getCardNum());
        stmt.setString(3, String.valueOf(LocalDate.now().plusYears(5)) );
        stmt.setString(4, card.getAccountNumber());
        stmt.setString(5, String.valueOf(card.getStatus()));
        stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existByCvv2(String cvv2){
        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(CardSQL.EXIST_BY_CVV2)) {
            stmt.setString(1, cvv2);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                return rs.getInt("count") > 0;
            }

            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existByCardNumber(String cardNumber){
        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(CardSQL.EXIST_BY_CARD_NUMBER)) {
            stmt.setString(1, cardNumber);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                return rs.getInt("count") > 0;
            }

            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Card> findFirstByAccountNumber(String accountNumber) {
        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(CardSQL.FIND_FIRST_BY_ACCOUNT_NUMBER)) {
            stmt.setString(1, accountNumber);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
//                int id = rs.getInt("id");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                int cvv2 = rs.getInt("cvv2");
                String expiry = rs.getString("expiry");
                String cardNum = rs.getString("card_number");
                String accountNum = rs.getString("account_number");
                String status = rs.getString("status");
                Card card = new Card(accountNum, String.valueOf(cvv2), cardNum, LocalDate.parse(expiry, formatter), CardActivity.valueOf(status));
                return Optional.of(card);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Optional<Card> findFirstByCardNumber(String cardNumber) {
        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(CardSQL.FIND_FIRST_BY_CARD_NUMBER)) {
            stmt.setString(1, cardNumber);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
//                int id = rs.getInt("id");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                int cvv2 = rs.getInt("cvv2");
                String expiry = rs.getString("expiry");
                String cardNum = rs.getString("card_number");
                String accountNum = rs.getString("account_number");
                String status = rs.getString("status");
                Card card = new Card(accountNum, String.valueOf(cvv2), cardNum, LocalDate. parse(expiry, formatter), CardActivity.valueOf(status));
                return Optional.of(card);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existByAccountNumber(String accountNumber){
        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(CardSQL.EXIST_BY_ACCOUNT_NUMBER)) {
            stmt.setString(1, accountNumber);

            ResultSet rs = stmt.executeQuery();

            if(rs.next())
                return rs.getInt("count") > 0;
            return false;

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

