package com.senla.atm.utils;

import com.senla.atm.model.Card;

public class CardHelper {
    public static Card parseStringToEntity(String str){
        String[] cardValues = str.split(" ");
        Card card = new Card();
        card.setOwner(cardValues[0]);
        card.setNumber(cardValues[1]);
        card.setPin(Integer.parseInt(cardValues[2]));
        card.setCvv(Integer.parseInt(cardValues[3]));
        card.setBalance(Double.parseDouble(cardValues[4]));
        card.setValidityPeriod(cardValues[5]);
        card.setStatus(Boolean.parseBoolean(cardValues[6]));
        return card;
    }
}
