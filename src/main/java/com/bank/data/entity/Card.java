package com.bank.data.entity;

import java.time.LocalDate;

public class Card {
    
    private String username;
    private String cvv2;
    private String cardNum;
    private LocalDate expiry;


    public Card(String username, String cvv2, String cardNum, LocalDate expiry) {
        this.username = username;
        this.cvv2 = cvv2;
        this.cardNum = cardNum;
        this.expiry = expiry;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public void findByUsername(Customer userName){

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

    
    
}
