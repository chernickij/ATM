package com.senla.atm.dao.impl;

import com.senla.atm.dao.AtmDao;
import com.senla.atm.model.BalanceOperation;
import com.senla.atm.utils.FileWriter;
import com.senla.atm.utils.FileReader;

public class AtmDaoImpl implements AtmDao {
    private static final String ATM_BALANCE_FILE_NAME = "atm_balance.txt";
    private double atmBalance;

    public AtmDaoImpl(){
        this.atmBalance = Double.parseDouble(FileReader.readFile(ATM_BALANCE_FILE_NAME).get(0));
    }

    @Override
    public Double getAtmBalance() {
        return atmBalance;
    }

    @Override
    public void updateBalance(BalanceOperation operation, Double sum) {
        if (operation.equals(BalanceOperation.INCREASE)) {
            this.atmBalance = atmBalance + sum;
        } else if (operation.equals(BalanceOperation.DECREASE)) {
            this.atmBalance = atmBalance - sum;
        } else {
            //todo smth went wrong exception
        }
    }

    @Override
    public void updateAtmBalance() {
        FileWriter.writeToFile(String.valueOf(atmBalance), ATM_BALANCE_FILE_NAME);
    }
}
