package com.company;

public class ChangeCalculator {

    public double calculateChange(double totalAmountProvided, Item item) {
        return totalAmountProvided - item.getPrice();
    }
}
