package com.senla.atm.service.impl;

import com.senla.atm.dao.AtmDao;
import com.senla.atm.exception.CardBlockedException;
import com.senla.atm.exception.CardNotFoundException;
import com.senla.atm.service.AtmService;
import com.senla.atm.service.CardService;
import com.senla.atm.service.CardSessionService;

import java.util.Scanner;

public class AtmServiceImpl implements AtmService {
    private final Scanner scanner = new Scanner(System.in);
    private final CardService cardService;

    private final AtmDao atmDao;

    public AtmServiceImpl(CardService cardService, AtmDao atmDao) {
        this.cardService = cardService;
        this.atmDao = atmDao;
    }

    @Override
    public void runAtm() {
        while (true) {
            try {
                printMenu();
                String selectedItem = scanner.nextLine();
                switch (selectedItem) {
                    case "1":
                        CardSessionService cardSession = new CardSessionServiceImpl(cardService);
                        cardSession.initSession();
                        break;
                    case "0":
                        stop();
                        break;
                }
            } catch (CardNotFoundException | CardBlockedException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void stop() {
        System.out.println("Good bye.");
        cardService.updateFileData();
        atmDao.updateAtmBalance();
        System.exit(0);
    }

    private void printMenu() {
        System.out.println("ROOT MENU SELECTION. PLEASE, ENTER YOUR OPTION.");
        System.out.println("Options:");
        System.out.println("1. Card auth.");
        System.out.println("0. Exit.");
    }
}
