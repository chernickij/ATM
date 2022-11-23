package com.senla.atm.service;

import com.senla.atm.model.Card;

import java.util.Scanner;

public class AtmService {
    private Scanner scanner = new Scanner(System.in);
    private CardService cardService;

    public AtmService(CardService cardService) {
        this.cardService = cardService;
    }

    public void start() {
        //
        Card card = cardService.cardAuthentication();
        boolean isRepeat = true;
        while (isRepeat) {
            printMenu();
            String selectedItem = scanner.nextLine();
            switch (selectedItem) {
                case "1":
                    System.out.println(cardService.checkBalance(card));
                    break;
                case "2":
                    System.out.println(cardService.withdraw(card));
                    break;
                case "3":
                    System.out.println(cardService.putCurrency(card));
                    break;
                case "0":
                    System.out.println("Good bye.");
                    stop();
                    break;
                default:
//                throw new Exception();
            }
        }
    }

    public void stop() {
        cardService.updateFileData();
        System.exit(0);
    }

    private void printMenu() {
        System.out.println("MENU SELECTION. PLEASE, ENTER YOUR OPTION.");
        System.out.println("Options:");
        System.out.println("1. Check card balance.");
        System.out.println("2. Withdraw money from the account.");
        System.out.println("3. Put currency on the balance.");
        System.out.println("0. Exit.");
    }
}
