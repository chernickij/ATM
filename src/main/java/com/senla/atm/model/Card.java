package com.senla.atm.model;

public class Card {
    private String owner;
    private String number;
    private int pin;
    private double balance;
    private boolean blocked;

    public Card() {

    }

    public Card(String owner, String number, int pin, double balance, boolean blocked) {
        this.owner = owner;
        this.number = number;
        this.pin = pin;
        this.balance = balance;
        this.blocked = blocked;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public String toString() {
        return "Card {" +
            "owner='" + owner + '\'' +
            ", number='" + number + '\'' +
            ", pin=" + pin +
            ", balance=" + balance +
            ", blocked=" + blocked +
            '}';
    }
}
