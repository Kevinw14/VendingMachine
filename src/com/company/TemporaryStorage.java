package com.company;

import java.util.ArrayList;

/**
 *
 */
public class TemporaryStorage {

    private final ArrayList<Coin> coins;
    private final Item item;
    private final ChangeCalculator calculator;
    private final CoinReserve reserves;

    public TemporaryStorage(int quarters, int dimes, int nickels, int pennies, Item item, CoinReserve reserves) {
        this.item = item;
        this.calculator = new ChangeCalculator();
        this.reserves = reserves;
        this.coins = new ArrayList<>();
        addToTempStorage(Coin.QUARTER, quarters);
        addToTempStorage(Coin.DIME, dimes);
        addToTempStorage(Coin.NICKEL, nickels);
        addToTempStorage(Coin.PENNY, pennies);
    }

    /**
     *
     * @param coin
     * @param amount
     */
    private void addToTempStorage(Coin coin, int amount) {
        for (int i = 0; i < amount; i++) {
            coins.add(coin);
        }
    }

    /**
     *
     * @return
     * @throws NotEnoughMoneyException
     * @throws NotEnoughChangeException
     */
    public int processTransaction() throws NotEnoughMoneyException, NotEnoughChangeException {
        if (!(calculator.totalAmount(coins) >= item.getPrice())) {
            throw new NotEnoughMoneyException();
        }

        if (!reserves.canMakeChange(calculator.totalAmount(coins))) {
           throw new NotEnoughChangeException();
        }

        ArrayList<Coin> usedCoins = calculator.findUsedCoins(coins, item);
        reserves.addCoinsToReserves(usedCoins);
        reserves.updateMoneyCollected(item.getPrice());
        int differenceFromUsedCoins = differenceFromUsedCoins(usedCoins);
        reserves.removeCoinsFromReserves(differenceFromUsedCoins);
        int totalLeftOver = calculator.totalAmount(coins);

        return differenceFromUsedCoins + totalLeftOver;
    }

    /**
     *
     * @param coins
     * @return
     */
    private int differenceFromUsedCoins(ArrayList<Coin> coins) {
        int total = 0;
        for (Coin coin : coins) {
            total += coin.getDenominition();
        }
        return total - item.getPrice();
    }

    @Override
    public String toString() {
        return calculator.toString();
    }
}
