package com.senla.atm.dao.impl;

import com.senla.atm.dao.AtmDao;
import com.senla.atm.utils.fileWriter;
import com.senla.atm.utils.FileReader;

public class AtmDaoImpl implements AtmDao {
    private static final String ATM_BALANCE_FILE_NAME = "atm_balance.txt";
    private double atmBalance;

    public AtmDaoImpl(){
        this.atmBalance = FileReader.readAtmBalance(ATM_BALANCE_FILE_NAME);
    }

    @Override
    public Double getAtmBalance() {
        return atmBalance;
    }

    @Override
    public void decreaseBalance(Double balance) {
        this.atmBalance = atmBalance - balance;
    }

    @Override
    public void increaseBalance(Double balance) {
        this.atmBalance = atmBalance + balance;
    }

    @Override
    public void updateAtmBalance() {
        fileWriter.writeToFile(String.valueOf(atmBalance), ATM_BALANCE_FILE_NAME);
    }
}
