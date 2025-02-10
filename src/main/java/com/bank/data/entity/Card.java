package com.bank.data.entity;

import com.bank.data.entity.enumeration.CardActivity;

import java.time.LocalDate;

public class Card {

//    private int id;
    private String cvv2;
    private String cardNum;
    private LocalDate expiry;
    private String accountNumber;
    private CardActivity status = CardActivity.ISNOTACTIVE;

    public Card() {}

    public Card( String accountNumber, String cvv2, String cardNum, LocalDate expiry, CardActivity status) {
//        this.id = id;
        this.cvv2 = cvv2;
        this.cardNum = cardNum;
        this.expiry = expiry;
        this.accountNumber = accountNumber;
        this.status = status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }


    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public LocalDate getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDate expiry) {
        this.expiry = expiry;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public CardActivity getStatus() {
        return status;
    }

    public void setStatus(CardActivity status) {
        this.status = status;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
}
