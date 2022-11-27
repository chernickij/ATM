package com.senla.atm.utils;

import com.senla.atm.model.Card;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class CardHelper {
    public static Card parseStringToEntity(String str) {
        String[] cardValues = str.split(" ");
        Card card = new Card();
        card.setOwner(cardValues[0]);
        card.setNumber(cardValues[1]);
        card.setPin(Integer.parseInt(cardValues[2]));
        card.setBalance(Double.parseDouble(cardValues[3]));
        card.setBlocked(Boolean.parseBoolean(cardValues[4]));
        card.setBlockTime(!cardValues[5].equals("null") ? LocalDateTime.parse(cardValues[5]) : null);
        return card;
    }

    public static String parseEntityToString(Card card) {
        return card.getOwner() + " "
            + card.getNumber() + " "
            + card.getPin() + " "
            + card.getBalance() + " "
            + card.isBlocked() + " "
            + card.getBlockTime();
    }

    public static boolean checkNumberFormat(String cardNumber) {
        return Pattern.matches("^\\d{4}-\\d{4}-\\d{4}-\\d{4}$", cardNumber);
    }

    public static boolean checkPinFormat(int pin) {
        int numDigits = String.valueOf(pin).length();
        return numDigits == 4;
    }
}
