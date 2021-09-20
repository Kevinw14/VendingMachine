package com.company;

public class NotEnoughMoneyException extends Exception {

    public NotEnoughMoneyException() {
        super("Not enough money was inserted");
    }
}
