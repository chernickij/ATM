package com.senla.atm;

import com.senla.atm.model.Card;
import com.senla.atm.utils.FileReader;
import com.senla.atm.utils.CardHelper;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String CARDS_FILE_NAME = "cards_data.txt";

    public static void main(String[] args) {
        List<String> cardsString = FileReader.readFile(CARDS_FILE_NAME);
        List<Card> cards = new ArrayList<>();
        for (String cardStr : cardsString){
            cards.add(CardHelper.parseStringToEntity(cardStr));
        }

        for (Card card : cards){
            System.out.println(card);
        }
    }
}
