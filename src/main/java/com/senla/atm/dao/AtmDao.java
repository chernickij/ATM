package com.senla.atm.dao;

import com.senla.atm.model.BalanceOperation;

public interface AtmDao {
    Double getAtmBalance();

    void updateBalance(BalanceOperation operation, Double sum);

    void updateAtmBalance();
}
