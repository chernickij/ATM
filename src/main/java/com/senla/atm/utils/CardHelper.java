package com.senla.atm.utils;

import com.senla.atm.model.Card;

public class CardHelper {
    public static Card parseStringToEntity(String str){
        String[] cardValues = str.split(" ");
        Card card = new Card();
        card.setOwner(cardValues[0]);
        card.setNumber(cardValues[1]);
        card.setPin(Integer.parseInt(cardValues[2]));
        card.setBalance(Double.parseDouble(cardValues[3]));
        card.setStatus(Boolean.parseBoolean(cardValues[4]));
        return card;
    }

    public static String parseEntityToString(Card card){
        return card.getOwner() + " "
            + card.getNumber() + " "
            + card.getPin() + " "
            + card.getBalance() + " "
            + card.isStatus();
    }
}
