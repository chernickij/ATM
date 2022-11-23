package com.senla.atm.dao;

public interface AtmDao {
    Double getAtmBalance();

    void decreaseBalance(Double balance);

    void increaseBalance(Double balance);

    void updateAtmBalance();
}
