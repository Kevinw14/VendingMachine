package com.company;

/**
 * Exception that is thrown when not enough money is
 * inserted into the machine by the user
 *
 * @author Kevin Wood
 * @version 1.0
 */
public class NotEnoughMoneyException extends Exception {

    public NotEnoughMoneyException() {
        super("Not enough money was inserted");
    }
}
