package com.bank.service;

import java.time.LocalDate;

public interface CardService {

    void createCardForCustomer(String username);

    String generateCvv2();

    String generateCardNumber();

    String getCardcvv2(String customerUsername);

    LocalDate getCardExpiry(String customerUsername);

    String getCardNumber(String customerUsername);
}
