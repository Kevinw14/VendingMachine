package com.company;

/**
 * Exception that is thrown when not enough change is
 * available to complete the transaction
 *
 * @author Kevin Wood
 * @version 1.0
 */

public class NotEnoughChangeException extends Exception {
    public NotEnoughChangeException() {
        super("Not enough change to complete transaction");
    }
}
