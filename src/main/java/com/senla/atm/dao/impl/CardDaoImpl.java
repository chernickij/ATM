package com.senla.atm.dao.impl;

import com.senla.atm.dao.CardDao;
import com.senla.atm.model.Card;
import com.senla.atm.utils.fileWriter;
import com.senla.atm.utils.CardHelper;
import com.senla.atm.utils.FileReader;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CardDaoImpl implements CardDao {
    private static final String CARDS_FILE_NAME = "cards_data.txt";
    private static final String ATM_BALANCE_FILE_NAME = "atm_balance.txt";

    private final Set<Card> cards;

    public CardDaoImpl() {
        this.cards = FileReader.readFile(CARDS_FILE_NAME).stream().map(CardHelper::parseStringToEntity).collect(Collectors.toSet());
    }

    @Override
    public Set<Card> getAll() {
        return this.cards;
    }

    public void updateFileData() {
        String line = cards.stream().map(card -> CardHelper.parseEntityToString(card) + "\n").collect(Collectors.joining());
        fileWriter.writeToFile(line, CARDS_FILE_NAME);
    }

    public void update(Card newCard) {
        // Номер карты явл уникальным
        Card existedCard = this.getByNumber(newCard.getNumber())
            .orElseThrow(
                //todo not found ex
            );
        this.cards.remove(existedCard);
        this.cards.add(newCard);
    }

    @Override
    public Optional<Card> getByNumber(String number) {
        return this.cards.stream().filter(card -> card.getNumber().equals(number)).findFirst();
    }
}
