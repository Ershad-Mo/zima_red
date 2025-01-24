package com.bank.service.impl;

import java.security.SecureRandom;
import java.time.LocalDate;

import com.bank.data.entity.Card;
import com.bank.data.repository.CardRepository;
import com.bank.service.CardService;
import com.bank.service.exception.ActivecardExistException;
import com.bank.service.exception.CardNotFoundException;

public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final SecureRandom random;

    public CardServiceImpl() {

        this.random = new SecureRandom();
        this.cardRepository = new CardRepository();
    }

    public void createCardForCustomer(String accountNumber) {
        
        if(cardRepository.existByAccountNumber(accountNumber))
            throw new ActivecardExistException();
        
        Card card = new Card(accountNumber, generateCvv2(), generateCardNumber(), LocalDate.now().plusYears(5));
        cardRepository.save(card);
    }


    public String generateCvv2() {
        String cvv2 = "";
        do {
            for(int i = 0; i < 4; i++) cvv2 += random.nextInt(10);
        }while(cardRepository.existByCvv2(cvv2));
        return cvv2;
    }

    public String generateCardNumber() {
        StringBuilder cardNumber = new StringBuilder();
        do {
            for(int i = 0; i < 16; i++) {
                cardNumber.append(random.nextInt(10)) ;
            }
        }while(cardRepository.existByCardNumber(cardNumber.toString()));
        return cardNumber.toString();
    }

    @Override
    public String getCardcvv2(String accountNumber) {
        return cardRepository.findFirstByAccountNumber(accountNumber) 
            .orElseThrow(() -> new CardNotFoundException())
            .getCvv2();
    }

    @Override
    public LocalDate getCardExpiry(String accountNumber) {
        return cardRepository.findFirstByAccountNumber(accountNumber) 
            .orElseThrow(() -> new CardNotFoundException())
            .getExpiry();
    }

    @Override
    public String getCardNumber(String accountNumber) {
        return cardRepository.findFirstByAccountNumber(accountNumber) 
            .orElseThrow(() -> new CardNotFoundException())
            .getCardNum();
    }
}
