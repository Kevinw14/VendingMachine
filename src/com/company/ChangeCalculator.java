package com.company;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class designed to choose the best coins to use in a transaction
 * and calculates the change that needs to be given back to the user
 *
 * @author Kevin Wood
 * @version 1.0
 */
public class ChangeCalculator {

    /**
     * Calculates the total of all coins
     *
     * @param coins Coins used in the transaction
     * @return The total added up from the coins
     */
    public int totalAmount(ArrayList<Coin> coins) {
        int total = 0;
        for (Coin coin: coins) {
            total += coin.getDenominition();
        }
        return total;
    }

    /**
     * Calculates the change that needs to be given back to the user
     *
     * @param coins The array of coins that was used in the transaction
     * @return The total amount of the coins used in the transaction minus the item's price
     */
    public int differenceFromUsedCoins(ArrayList<Coin> coins, Item item) {
        int total = 0;
        for (Coin coin : coins) {
            total += coin.getDenominition();
        }
        return total - item.getPrice();
    }

    /**
     * Goes through the coins the user inserted and finds the ones
     * to complete the transaction
     *
     * @param coins ArrayList of the coins inserted by user
     * @param item Item the user is purchasing
     * @return ArrayList of the coins accepted.
     */
    public ArrayList<Coin> findUsedCoins(ArrayList<Coin> coins, Item item) {
        ArrayList<Coin> usedCoins = new ArrayList<>();
        Iterator<Coin> iter = coins.iterator();
        int results = item.getPrice();

        while(iter.hasNext()) {
            Coin coin = iter.next();
            if (results > 0) {
                results -= coin.getDenominition();
                iter.remove();
                usedCoins.add(coin);
            }
        }
        return usedCoins;
    }

}
