package com.senla.atm.dao;

import com.senla.atm.model.Card;

import java.util.Optional;
import java.util.Set;

public interface CardDao {
    Set<Card> getAll();

    Optional<Card> getByNumber(String number);

    Card update(Card card);

    void updateFileData();
}
