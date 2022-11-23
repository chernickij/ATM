package com.senla.atm.service;

import com.senla.atm.model.Card;

public interface CardService {
    Card cardAuthentication();

    Card withdraw(Card card);

    Card putCurrency(Card card);

    void updateFileData();
}
