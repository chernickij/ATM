package com.senla.atm.service.impl;

import com.senla.atm.model.Card;
import com.senla.atm.service.CardService;
import com.senla.atm.service.CardSessionService;

import java.util.Scanner;

public class CardSessionServiceImpl implements CardSessionService {
    private final Scanner scanner = new Scanner(System.in);
    private final CardService cardService;

    public CardSessionServiceImpl(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void initSession() {
        Card card = cardService.cardAuthentication();
        boolean isRepeat = card != null;
        while (isRepeat) {
            printMenu();
            String selectedItem = scanner.nextLine();
            switch (selectedItem) {
                case "1":
                    System.out.println("You have " + card.getBalance() + " currency.");
                    break;
                case "2":
                    card = cardService.withdraw(card);
                    break;
                case "3":
                    card = cardService.putCurrency(card);
                    break;
                case "0":
                    System.out.println("Logout and back to root menu.");
                    isRepeat = false;
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println("MENU SELECTION. PLEASE, ENTER YOUR OPTION.");
        System.out.println("Options:");
        System.out.println("1. Check card balance.");
        System.out.println("2. Withdraw money from the account.");
        System.out.println("3. Put currency on the balance.");
        System.out.println("0. Logout and back to root menu.");
    }
}
