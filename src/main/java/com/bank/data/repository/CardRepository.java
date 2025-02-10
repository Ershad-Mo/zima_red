package com.bank.data.repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.bank.data.entity.BankAccount;
import com.bank.data.entity.Card;

public class CardRepository {
    private static final Set<Card> cards = new HashSet<>();


    public void save(Card card) {
        cards.add(card);
    }

    public boolean existByCvv2(String cvv2){
        return cards.stream().anyMatch(card -> card.getCvv2().equals(cvv2));
    }

    public boolean existByCardNumber(String cardNumber){
        return cards.stream().anyMatch(card -> card.getCardNum().equals(cardNumber));
    }

    public boolean existByAccountNumber(String accountNumber){
        return cards.stream().anyMatch(card -> card.getAccountNumber().equals(accountNumber));
    }

    public Optional<Card> findFirstByAccountNumber(String accountNumber) {
        return cards.stream()
            .filter(card -> card.getAccountNumber().equals(accountNumber))
            .findFirst();
    }

    public Optional<Card> findFirstByCardNumber(String cardNumber) {
        return cards.stream()
                .filter(card -> card.getCardNum().equals(cardNumber))
                .findFirst();
    }
}
