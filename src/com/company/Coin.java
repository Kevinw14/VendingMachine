package com.company;

/**
 * Enumeration of a Coin and its value it holds.
 *
 * @author Kevin Wood
 * @version 1.0
 */
public enum Coin {
    QUARTER(25),
    DIME(10),
    NICKEL(5),
    PENNY(1);

    private int denominition;

    Coin(int denomination) {
        this.denominition = denomination;
    }

    public int getDenominition() {
        return denominition;
    }
}
