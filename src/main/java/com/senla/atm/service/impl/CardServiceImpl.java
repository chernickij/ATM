package com.senla.atm.service.impl;

import static com.senla.atm.utils.CardHelper.checkNumberFormat;
import static com.senla.atm.utils.CardHelper.checkPinFormat;

import com.senla.atm.dao.AtmDao;
import com.senla.atm.dao.CardDao;
import com.senla.atm.model.BalanceOperation;
import com.senla.atm.model.Card;

import com.senla.atm.service.CardService;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class CardServiceImpl implements CardService {
    private Scanner scanner = new Scanner(System.in);
    private final CardDao cardDao;
    private final AtmDao atmDao;

    public CardServiceImpl(CardDao cardDao, AtmDao atmDao) {
        this.cardDao = cardDao;
        this.atmDao = atmDao;
    }

    @Override
    public Card cardAuthentication() {
        System.out.println("Please, enter card number in «ХХХХ-ХХХХ-ХХХХ-ХХХХ» format or Enter 0 to return to the menu.");
        String cardNumber = inputCardNumber();
        if (Objects.equals(cardNumber, "0")) {
            return null;
        }
        Card userCard = cardDao.getByNumber(cardNumber).orElse(null);
        if(userCard.isBlocked()) {
            System.out.println("You cannot login, your card is blocked.");
            return null;
        }
        if (checkCardPin(userCard) == 0) {
            return null;
        }
        return userCard;
    }

    public Card withdraw(Card card) {
        System.out.println("Enter the amount of currency you want to withdraw.");
        double withdrawMoney = inputWithdraw(card);
        if (atmDao.getAtmBalance() <= withdrawMoney) {
            System.out.println("There are not enough funds in the ATM.");
            return card;
        }
        card.setBalance(card.getBalance() - withdrawMoney);
        atmDao.updateBalance(BalanceOperation.DECREASE, withdrawMoney);

        card = cardDao.update(card);

        System.out.println("You have successfully withdrawn " + withdrawMoney + " currency.");
        return card;
    }

    public Card putCurrency(Card card) {
        System.out.println("Enter the amount of currency you want to put on the balance.");
        double deposit = inputDeposit();
        card.setBalance(card.getBalance() + deposit);
        cardDao.update(card);
        atmDao.updateBalance(BalanceOperation.INCREASE, deposit);
        System.out.println("You have successfully put " + deposit + " currency on your balance.");
        return card;
    }


    public void updateFileData() {
        cardDao.updateFileData();
    }

    private String inputCardNumber(){
        String cardNumber = scanner.nextLine();
        while(!checkNumberFormat(cardNumber)){
            if (cardNumber.equals("0")) {
                return "0";
            }

            System.out.println("Wrong card number. Please, enter card number in «ХХХХ-ХХХХ-ХХХХ-ХХХХ» format or enter 0 to back to root menu.");
            cardNumber = scanner.nextLine();
        }
        return cardNumber;
    }

    private int checkCardPin(Card card) {
        int attempts = 0;
        int enteredPin = 0;
        while (attempts < 3) {
            enteredPin = inputCardPin();
            if (enteredPin == card.getPin()) {
                break;
            }
            if (enteredPin == 0) {
                break;
            }
            attempts++;
            System.out.println("Wrong pin, attempts left:" + (3 - attempts));
            if (attempts == 3) {
                System.out.println("Your card is blocked for 24 hours.\n");
                blockCard(card);
                enteredPin = 0;
                break;
            }
        }

        return enteredPin;
    }

    private int inputCardPin() {
        int enteredPin = 0;
        boolean isRepeat = true;
        System.out.println("Please, enter the password in 4 digits format like «ХХХХ» format or enter 0 to back to root menu.");
        while (isRepeat) {
            try {
                isRepeat = false;
                scanner = new Scanner(System.in);
                enteredPin = scanner.nextInt();

                if (enteredPin == 0) {
                    return 0;
                }
                if (!checkPinFormat(enteredPin)) {
                    System.out.println("Wrong pin format. Please, enter the password in 4 digits format like «ХХХХ» or enter 0 to back to root menu.");
                    isRepeat = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong pin format. Please, enter the password in 4 digits format like «ХХХХ» or enter 0 to back to root menu.");
                isRepeat = true;
            }
        }
        return enteredPin;
    }

    private void blockCard(Card card){
        card.setBlocked(true);
        cardDao.update(card);
    }

    private Double inputWithdraw(Card card){
        double withdrawMoney = 0;
        boolean isRepeat = true;
        while(isRepeat){
            try {
                isRepeat = false;
                scanner = new Scanner(System.in);
                withdrawMoney = scanner.nextDouble();
            } catch (InputMismatchException e){
                System.out.println("Wrong format. Please, enter the numbers.");
                isRepeat = true;
            }
            if(withdrawMoney > card.getBalance()){
                System.out.println("Insufficient funds on the card, try again.");
                isRepeat = true;
            }
        }
        return withdrawMoney;
    }

    private Double inputDeposit(){
        double deposit = 0;
        double limit = 1000000;
        boolean isRepeat = true;
        while(isRepeat){
            try {
                isRepeat = false;
                scanner = new Scanner(System.in);
                deposit = scanner.nextDouble();
            } catch (InputMismatchException e){
                System.out.println("Wrong format. Please, enter the numbers.");
                isRepeat = true;
            }
            if(deposit > limit){
                System.out.println("Deposit cannot exceed 1000000, try again.");
                isRepeat = true;
            }
        }
        return deposit;
    }
}
