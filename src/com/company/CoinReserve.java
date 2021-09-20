package com.company;

import java.util.ArrayList;

public class CoinReserve {
    private final ArrayList<Coin> coins;
    private int moneyCollected;

    public CoinReserve() {
        this.moneyCollected = 0;
        this.coins = new ArrayList<>();
        initializeCoinsReserves();
    }

    private void initializeCoinsReserves() {
        for (Coin coin: Coin.values()) {
            for (int i = 0; i < 10; i++) {
                coins.add(coin);
            }
        }
    }

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

    private int numberOfCoinsInReserves(Coin coin) {
        int count = 0;

        for (Coin currentCoin: coins) {
            if (currentCoin.getDenominition() == coin.getDenominition())
                count++;
        }

        return count;
    }
    public void addCoinsToReserves(ArrayList<Coin> coins) {
        this.coins.addAll(coins);
    }

    public void updateMoneyCollected(int amount) {
        moneyCollected += amount;
    }

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
