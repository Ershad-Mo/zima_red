package com.bank.service;

import com.bank.data.entity.enumeration.CardActivity;

import java.time.LocalDate;
import java.util.Optional;

public interface CardService {

    void createCardForCustomer(String username);

    Optional<CardActivity> getActivity(String accountNumber);

    void InActiveMyCard(String accountNumber);

    String generateCvv2();

    String generateCardNumber();

    String getCardcvv2(String customerUsername);

    LocalDate getCardExpiry(String customerUsername);

    String getCardNumber(String customerUsername);

    String cardExistance(String accountNumber);

    String getAccountNumber(String customerUsername);
}
