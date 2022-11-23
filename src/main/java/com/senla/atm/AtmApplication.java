package com.senla.atm;

import com.senla.atm.dao.AtmDao;
import com.senla.atm.dao.CardDao;
import com.senla.atm.dao.impl.AtmDaoImpl;
import com.senla.atm.dao.impl.CardDaoImpl;
import com.senla.atm.service.AtmService;
import com.senla.atm.service.CardService;

public class AtmApplication {
    public static void main(String[] args) {
        CardDao cardDao = new CardDaoImpl();
        AtmDao atmDao = new AtmDaoImpl();
        CardService cardService = new CardService(cardDao, atmDao);
        AtmService atmService = new AtmService(cardService);
        atmService.start();


//        cardDao.update(new Card("Cger", "1234-1234-1234-1234", 1234, 124.0, "10/25", true));
//        AtmFileWriter.writeToFile("123\n123", "cards_data.txt");
    }
}
