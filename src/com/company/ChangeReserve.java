package com.company;

public class ChangeReserve {
    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;
    private double moneyCollected;
    private ChangeReserveDelegate delegate;

    public ChangeReserve() {
        this.quarters = 10;
        this.dimes = 10;
        this.nickels = 10;
        this.pennies = 10;
        this.moneyCollected = 0.00;
    }

    private double totalAmount(int quarters, int dimes, int nickels, int pennies) {
        double quarterAmount = quarters * 0.25;
        double dimeAmount = dimes * 0.10;
        double nickelAmount = nickels * 0.05;
        double penniesAmount = pennies * 0.01;
        return quarterAmount + dimeAmount + nickelAmount + penniesAmount;
    }

    public boolean canMakePurchase(int quarters, int dimes, int nickels, int pennies, Item item) {
        double totalAmount = totalAmount(quarters, dimes, nickels, pennies);

        if (totalAmount >= item.getPrice()) {
            return true;
        }
        delegate.errorVendingMachineNotEnoughMoney();
        return false;
    }

    public boolean canMakeChange(int quarters, int dimes, int nickels, int pennies) {
        boolean enoughQuarters = this.quarters >= quarters;
        boolean enoughDimes = this.dimes >= dimes;
        boolean enoughNickels = this.nickels >= nickels;
        boolean enoughPennies = this.pennies >= pennies;

        if (enoughQuarters && enoughDimes && enoughNickels && enoughPennies) {
            return true;
        }

        delegate.errorVendingMachineCantMakeChange();
        return false;
    }

    public void decreaseMoneyDeposits(double change) {
        int currentChange = (int) (change * 100);
        int quarters = currentChange / 25;
        this.quarters -= quarters;
        currentChange -= quarters * 25;
        int dimes = currentChange / 10;
        this.dimes -= dimes;
        currentChange -= dimes * 10;
        int nickels = currentChange / 5;
        this.nickels -= nickels;
        currentChange -= nickels * 5;
        this.pennies -= currentChange;
    }

    public void updateMoneyCollected(double amount) {
        moneyCollected += amount;
    }
    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public int getPennies() {
        return pennies;
    }

    public double getMoneyCollected() {
        return moneyCollected;
    }

    public void setDelegate(ChangeReserveDelegate delegate) {
        this.delegate = delegate;
    }
}
