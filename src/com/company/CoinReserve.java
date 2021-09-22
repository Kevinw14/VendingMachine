package com.company;

import java.util.ArrayList;

/**
 *
 */
public class CoinReserve {
    private final ArrayList<Coin> coins;
    private int moneyCollected;

    public CoinReserve() {
        this.moneyCollected = 0;
        this.coins = new ArrayList<>();
        initializeCoinsReserves();
    }

    /**
     * Initializes the coin reserve with 10 of each coin
     */
    private void initializeCoinsReserves() {
        for (Coin coin: Coin.values()) {
            for (int i = 0; i < 10; i++) {
                coins.add(coin);
            }
        }
    }

    /**
     * Called to make sure the vending machine can give change back
     * before proceeding further in the transaction
     *
     * @param amount The amount of change that needs to be given back
     * @return boolean if machine can process the change with the given levels
     */
    public boolean canMakeChange(int amount) {
        int amountRemaining = amount;
        int quarters = amountRemaining / 25;
        amountRemaining -= quarters * 25;
        int dimes = amountRemaining / 10;
        amountRemaining -= dimes * 10;
        int nickels = amountRemaining / 5;
        amountRemaining -= nickels * 5;
        int pennies = amountRemaining;
        return quarters < numberOfCoinsInReserves(Coin.QUARTER) &&
                dimes < numberOfCoinsInReserves(Coin.DIME) &&
                nickels < numberOfCoinsInReserves(Coin.NICKEL) &&
                pennies < numberOfCoinsInReserves(Coin.PENNY);
    }

    /**
     * Counts a coin that appears in the reserves
     *
     * @param coin The coin to count in the reserve
     * @return The count of coins in the reserve
     */
    private int numberOfCoinsInReserves(Coin coin) {
        int count = 0;

        for (Coin currentCoin: coins) {
            if (currentCoin.getDenominition() == coin.getDenominition())
                count++;
        }

        return count;
    }

    /**
     * Adds the coin array to the over reserve array
     *
     * @param coins Adds the coins to the reserve array
     */
    public void addCoinsToReserves(ArrayList<Coin> coins) {
        this.coins.addAll(coins);
    }

    /**
     * Called to update the overall money the vending machine has collected
     * over the lifetime of the machine
     *
     * @param amount The amount that vending machine has collected
     */
    public void updateMoneyCollected(int amount) {
        moneyCollected += amount;
    }

    /**
     * Given an amount goes through and removes the coins that make up that amount
     *
     * @param amount The amount that needs to be removed from the reserves
     */
    public void removeCoinsFromReserves(int amount) {
        int amountRemaining = Math.abs(amount);
        int quarters = amountRemaining / 25;
        amountRemaining -= quarters * 25;
        removeCoinFromReserve(Coin.QUARTER, quarters);
        int dimes = amountRemaining / 10;
        amountRemaining -= dimes * 10;
        removeCoinFromReserve(Coin.DIME, dimes);
        int nickels = amountRemaining / 5;
        amountRemaining -= nickels * 5;
        removeCoinFromReserve(Coin.NICKEL, nickels);
        int pennies = amountRemaining;
        removeCoinFromReserve(Coin.PENNY, pennies);
    }

    /**
     * Removes a certain coin from the reserve n number of times
     *
     * @param coin The coin to remove from the reserve
     * @param amount The amount of a certain coin to remove
     */
    private void removeCoinFromReserve(Coin coin, int amount) {
        int count = 0;
        int i = 0;
        while (count < amount) {
            Coin currentCoin = coins.get(i);
            if (currentCoin.getDenominition() == coin.getDenominition()) {
                coins.remove(i);
                count++;
            }
            i++;
        }
    }

    public int getMoneyCollected() {
        return moneyCollected;
    }

    @Override
    public String toString() {
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0;

        for (Coin coin: coins) {
            if (coin == Coin.QUARTER)
                quarters++;
            if (coin == Coin.DIME)
                dimes++;
            if (coin == Coin.NICKEL)
                nickels++;
            if (coin == Coin.PENNY)
                pennies++;
        }

        return String.format("The reserves have\n%d quarters\n%d dimes\n%d nickels\n%d pennies\nand collected %.2f ", numberOfCoinsInReserves(Coin.QUARTER), numberOfCoinsInReserves(Coin.DIME), numberOfCoinsInReserves(Coin.NICKEL), numberOfCoinsInReserves(Coin.PENNY), (double)(moneyCollected) / 100);
    }
}
