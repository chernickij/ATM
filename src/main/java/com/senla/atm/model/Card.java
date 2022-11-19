package com.senla.atm.model;

public class Card {
    private String owner;
    private String number;
    private int pin;
    private int cvv;
    private double balance;
    private String validityPeriod;
    private boolean status;

    public Card() {

    }

    public Card(String owner, String number, int pin, int cvv, double balance, String validityPeriod, boolean status) {
        this.owner = owner;
        this.number = number;
        this.pin = pin;
        this.cvv = cvv;
        this.balance = balance;
        this.validityPeriod = validityPeriod;
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(String validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Card {" +
            "owner='" + owner + '\'' +
            ", number='" + number + '\'' +
            ", pin=" + pin +
            ", cvv=" + cvv +
            ", balance=" + balance +
            ", validityPeriod='" + validityPeriod + '\'' +
            ", status=" + status +
            '}';
    }
}
