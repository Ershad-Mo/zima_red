package com.bank.data.repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import com.bank.data.entity.Card;


public class CardRepository {
    private static final Set<Card> cards = new HashSet<>();


    public void save(Card card) {
        cards.add(card);
    }

    public boolean existByCvv2(String cvv2){
        for(Card card : cards){
            if(card.getCvv2().equals(cvv2)){
                return true;
            }
        }
        return false;
    }

    public Optional<Card> findFirstByUsername(String username) {
        return cards.stream()
            .filter(card -> card.getUsername().equals(username))
            .findFirst();
    }
    
}
