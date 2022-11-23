package com.senla.atm;

import com.senla.atm.dao.AtmDao;
import com.senla.atm.dao.CardDao;
import com.senla.atm.dao.impl.AtmDaoImpl;
import com.senla.atm.dao.impl.CardDaoImpl;
import com.senla.atm.service.AtmService;
import com.senla.atm.service.CardService;
import com.senla.atm.service.impl.AtmServiceImpl;
import com.senla.atm.service.impl.CardServiceImpl;

public class AtmApplication {
    public static void main(String[] args) {
        CardDao cardDao = new CardDaoImpl();
        AtmDao atmDao = new AtmDaoImpl();

        CardService cardService = new CardServiceImpl(cardDao, atmDao);
        AtmService atmService = new AtmServiceImpl(cardService, atmDao);
        atmService.runAtm();
    }
}
