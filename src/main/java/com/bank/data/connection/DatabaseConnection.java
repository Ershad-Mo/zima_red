package com.bank.data.connection;

import java.sql.*;
import java.util.Objects;

public class DatabaseConnection {
    private static Connection connection;

    private DatabaseConnection() {}

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return connection;
    }

    public static Connection getConnection() {
        if (Objects.isNull(connection)) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zima_red", "root", "Ershadmmdy");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return connection;
    }
}
