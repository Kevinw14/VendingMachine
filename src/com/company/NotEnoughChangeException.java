package com.company;

public class NotEnoughChangeException extends Exception {
    public NotEnoughChangeException() {
        super("Not enough change to complete transaction");
    }
}
