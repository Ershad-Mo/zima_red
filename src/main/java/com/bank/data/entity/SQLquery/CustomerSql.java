package com.bank.data.entity.SQLquery;

public final class CustomerSql {

    public static final String INSERT_CUSTOMER
            = "insert into customer (username, password) values(?, ?)";

    public static final String EXIST_CUSTOMER_BY_USERNAME
            = "select count(id) as count from customer where username=?";

    public static final String SELECT_CUSTOMER_BY_USERNAME_AND_PASSWORD
            = "select count(id) as count from customer where username=? and password=?";

}

//public static final String TABLE_NAME = "customer";

