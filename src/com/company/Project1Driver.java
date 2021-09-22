package com.company;


/**
 * Application that simulates a vending machine. Users can select from a number of items.
 * Enter coins separately and receive the item and their change back.
 *
 * Implements an operator mode to allow the owner of the vending machine to
 * restock sold out items, change prices of items, and retrieve profits made
 * by the machine.
 *
 * @author Kevin Wood
 * @version 1.0
 */
public class Project1Driver {
    public static void main(String[] args) {
        TextView textView = new TextView();
        new VendingMachineController(textView);
    }
}