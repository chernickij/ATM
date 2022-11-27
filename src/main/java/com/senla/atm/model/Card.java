package com.senla.atm.model;

import java.time.LocalDateTime;

public class Card {
    private String owner;
    private String number;
    private int pin;
    private double balance;
    private CardStatus status;
    private LocalDateTime blockTime;

    public Card() {

    }

    public Card(String owner, String number, int pin, double balance, CardStatus status, LocalDateTime blockTime) {
        this.owner = owner;
        this.number = number;
        this.pin = pin;
        this.balance = balance;
        this.status = status;
        this.blockTime = blockTime;
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

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public LocalDateTime getBlockTime() {
        return blockTime;
    }

    public void setBlockTime(LocalDateTime blockTime) {
        this.blockTime = blockTime;
    }

    @Override
    public String toString() {
        return "Card {" +
            "owner='" + owner + '\'' +
            ", number='" + number + '\'' +
            ", pin=" + pin +
            ", balance=" + balance +
            ", status=" + status +
            '}';
    }
}
