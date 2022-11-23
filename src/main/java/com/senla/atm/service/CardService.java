package com.senla.atm.service;

import com.senla.atm.dao.AtmDao;
import com.senla.atm.dao.CardDao;
import com.senla.atm.model.Card;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CardService {
    private Scanner scanner = new Scanner(System.in);
    private final CardDao cardDao;
    private final AtmDao atmDao;

    public CardService(CardDao cardDao, AtmDao atmDao) {
        this.cardDao = cardDao;
        this.atmDao = atmDao;
    }

    public Card cardAuthentication(){
        System.out.println("Please, enter card number in «ХХХХ-ХХХХ-ХХХХ-ХХХХ» format.\n" +
            "Enter 0 to return to the menu.");
        String cardNumber = inputCardNumber();
        Card userCard = cardDao.getByNumber(cardNumber).orElseThrow(
            //todo throw not found exception
        );
        checkCardPin(userCard);
        return userCard;
    }

    public String checkBalance(Card card){
        return "You have " + card.getBalance() + " currency.";
    }

    public String withdraw(Card card){
        System.out.println("Enter the amount of currency you want to withdraw.");
        double withdrawMoney = inputWithdraw(card);
        card.setBalance(card.getBalance() - withdrawMoney);
        cardDao.update(card);
        atmDao.decreaseBalance(withdrawMoney);
        return "You have successfully withdrawn " + withdrawMoney +" currency.";
    }

    public String putCurrency(Card card){
        System.out.println("Enter the amount of currency you want to put on the balance.");
        double deposit = inputDeposit(card);
        card.setBalance(card.getBalance() + deposit);
        cardDao.update(card);
        atmDao.increaseBalance(deposit);
        return "You have successfully put" + deposit +" currency on your balance.";
    }

    public void updateFileData() {
        cardDao.updateFileData();
        atmDao.updateAtmBalance();
    }

    private String inputCardNumber(){
        String cardNumber = scanner.nextLine();
        while(!checkNumberFormat(cardNumber)){
            if(cardNumber.equals("0")) {
                System.exit(0);
            }

            System.out.println("Wrong card number. Please, enter card number in «ХХХХ-ХХХХ-ХХХХ-ХХХХ» format.\n" +
                "Enter 0 to exit.");
            cardNumber = scanner.nextLine();
        }
        return cardNumber;
    }

    private boolean checkNumberFormat(String cardNumber){
        return Pattern.matches("^\\d{4}-\\d{4}-\\d{4}-\\d{4}$", cardNumber);
    }

    private void checkCardPin(Card card){
        int attempts = 0;
        int enteredPin = 0;
        while (attempts < 3){
            enteredPin = inputCardPin();
            if(enteredPin == card.getPin()){
                break;
            }
            attempts++;
            System.out.println("Wrong pin, attempts left:" + (3 - attempts));
            if (attempts == 3){
                System.out.println("Your card is blocked for 24 hours.\n");
                blockCard(card);
//todo                cardDao.block
            }
        }
    }

    private int inputCardPin(){
        int enteredPin = 0;
        boolean isRepeat = true;
        System.out.println("Please, enter the password in 4 digits format. «ХХХХ».");
        while (isRepeat){
            try {
                isRepeat = false;
                scanner = new Scanner(System.in);
                enteredPin = scanner.nextInt();
                if(!checkPinFormat(enteredPin) ){
                    System.out.println("Wrong pin format. Please, enter the password in 4 digits format. «ХХХХ».");
                    isRepeat = true;
                }
            } catch (InputMismatchException e){
                System.out.println("Wrong pin format. Please, enter the password in 4 digits format. «ХХХХ».");
                isRepeat = true;
            }
        }
        return enteredPin;
    }

    private boolean checkPinFormat(int pin){
        int numDigits = String.valueOf(pin).length();
        return numDigits == 4;
    }

    private void blockCard(Card card){
        card.setStatus(false);
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
            if(withdrawMoney > atmDao.getAtmBalance()){
                System.out.println("insufficient funds in the ATM, try again.");
                isRepeat = true;
            }
        }
        return withdrawMoney;
    }

    private Double inputDeposit(Card card){
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
