package com.bank.service.impl;

import java.security.SecureRandom;
import java.time.LocalDate;

import com.bank.data.entity.Card;
import com.bank.data.repository.CardRepository;
import com.bank.service.CardService;
import com.bank.service.exception.CardNotFoundException;

public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final SecureRandom random;

    public CardServiceImpl() {

        this.random = new SecureRandom();
        this.cardRepository = new CardRepository();
    }

    public void createCardForCustomer(String username) {
        Card card = new Card(username, generateCvv2(), generateCardNumber(), LocalDate.now());
        cardRepository.save(card);
    }


    public String generateCvv2() {
        String cvv2 = " ";
        do {
            for(int i = 0; i < 4; i++) cvv2 += random.nextInt(10);
        }while(cardRepository.existByCvv2(cvv2));
        return cvv2;
    }

    public String generateCardNumber() {
        String cardNumber = " ";
        do {
            for(int i = 0; i < 16; i++) {
                cardNumber += random.nextInt(10);
                if (cardNumber.length() % 5 == 0) cardNumber += " ";
            }
        }while(cardRepository.existByCvv2(cardNumber));
        return cardNumber;
    }

    @Override
    public String getCardcvv2(String customerUsername) {
        return cardRepository.findFirstByUsername(customerUsername) 
            .orElseThrow(() -> new CardNotFoundException())
            .getCvv2();
    }

    @Override
    public LocalDate getCardExpiry(String customerUsername) {
        return cardRepository.findFirstByUsername(customerUsername) 
            .orElseThrow(() -> new CardNotFoundException())
            .getExpiry();
    }

    @Override
    public String getCardNumber(String customerUsername) {
        return cardRepository.findFirstByUsername(customerUsername) 
            .orElseThrow(() -> new CardNotFoundException())
            .getCardNum();
    }
}
