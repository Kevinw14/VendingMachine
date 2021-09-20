package com.company;

import java.util.ArrayList;
import java.util.Iterator;

public class ChangeCalculator {

    public int totalAmount(ArrayList<Coin> coins) {
        int total = 0;
        for (Coin coin: coins) {
            total += coin.getDenominition();
        }
        return total;
    }

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
