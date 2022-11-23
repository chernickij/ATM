package com.senla.atm.dao;

import com.senla.atm.model.Card;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CardDao {
    Set<Card> getAll();

    Optional<Card> getByNumber(String number);

    void update(Card card);

    void updateFileData();
}
