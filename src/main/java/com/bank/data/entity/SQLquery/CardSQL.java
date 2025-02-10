package com.bank.data.entity.SQLquery;

public final class CardSQL {
    public static final String INSERT_CARD =
            "insert into card (cvv2, card_number, expiry, account_number, status) values (?, ?, ?, ?, ?)";

    public static final String EXIST_BY_CVV2
            = "select count(id) as count from card where cvv2 = ?";

    public static final String EXIST_BY_CARD_NUMBER
            = "select count(id) as count from card where card_number = ?";

    public static final String FIND_FIRST_BY_ACCOUNT_NUMBER
            = "select * from card where account_number = ?";

    public static final String FIND_FIRST_BY_CARD_NUMBER
            = "select * from card where card_number = ?";

    public static final String EXIST_BY_ACCOUNT_NUMBER
            = "select count(id) as count from card where account_number = ?";

}