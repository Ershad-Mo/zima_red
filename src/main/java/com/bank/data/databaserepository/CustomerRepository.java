package com.bank.data.databaserepository;

import com.bank.data.connection.DatabaseConnection;
import com.bank.data.entity.Customer;
import com.bank.data.entity.SQLquery.CustomerSql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepository {


    public void save(Customer customer) {
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(CustomerSql.INSERT_CUSTOMER)) {
            stmt.setString(1, customer.getUserName());
            stmt.setString(2, customer.getPassWord());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public boolean existByUsernameAndPassword(String username, String password) {
        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(CustomerSql.SELECT_CUSTOMER_BY_USERNAME_AND_PASSWORD)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
              return rs.getInt("count") > 0;
            }

         return false;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public boolean existByUsername(String username) {
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(CustomerSql.EXIST_CUSTOMER_BY_USERNAME)) {
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("count") > 0;
            }

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
