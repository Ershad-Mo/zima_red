package com.bank.data.entity.SQLquery;

public final class BankAccountSQL {
    public static final String INSERT_BANK_ACCOUNT =
            "insert into bank_account (customer_username, account_number, creation_date, balance) values(?, ?, ?, ?)";

    public static final String UPDATE_BALANCE =
            "update bank_account set balance = ? where id = ?";

    public static final String FIND_BANK_ACCOUNT_BY_USERNAME =
            "select * from bank_account where customer_username = ?";

    public static final String FIND_BANK_ACCOUNT_BY_ACCOUNT_NUMBER =
            "select * from bank_account where account_number = ?";

}
