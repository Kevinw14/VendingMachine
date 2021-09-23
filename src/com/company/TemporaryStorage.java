package com.company;

import java.util.ArrayList;

/**
 * Class used to help keep track of coins inserted into the vending machine
 * Processes coins and
 *
 * @author Kevin Wood
 * @version 1.0
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
     * Adds n number of coins of a certain type to the temporary storage array
     *
     * @param coin The coin to add to the temporary storage
     * @param amount The amount of one coin to add to the temporary storage
     */
    private void addToTempStorage(Coin coin, int amount) {
        for (int i = 0; i < amount; i++) {
            coins.add(coin);
        }
    }

    /**
     * Checks to make sure the vending machine can process the transaction and stores the money
     * calculates change, and removes it from the reserve
     *
     * @return The change that is returned after processing the transaction
     * @throws NotEnoughMoneyException Exception thrown when user doesn't insert enough money
     * @throws NotEnoughChangeException Exception thrown when not enough change to complete the transaction
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
        int differenceFromUsedCoins = calculator.differenceFromUsedCoins(usedCoins, item);
        reserves.removeCoinsFromReserves(differenceFromUsedCoins);
        int totalLeftOver = calculator.totalAmount(coins);

        return differenceFromUsedCoins + totalLeftOver;
    }

    @Override
    public String toString() {
        return calculator.toString();
    }
}
