package com.company;

/**
 * Delegate that helps the OperatorManagementSystem communicate with the
 * VendingMachineController, to update the view and ask operator for input.
 *
 * @author Kevin Wood
 * @version 1.0
 */
public interface OperatorManagementSystemDelegate {
    /**
     * Delegate Method
     *
     * Asks the operator how many they would like to add to the already
     * existing quantity
     *
     * @return The amount to add to the current stock
     */
    int askForRestockQuantity();

    /**
     * Delegate Method
     *
     * Asks the operator the new price to set the item to be
     *
     * @return The price to set the new price
     */
    double askForPriceChange();

    /**
     * Delegate Method
     *
     * Called when operator requests to stock an item over ten
     */
    void restockQuantityOverThreshold();

    /**
     * Delegate Method
     *
     * Called when the operator tries to set the price below 0
     */
    void priceChangePriceNegative();

    /**
     * Called when collecting the money out of the machine. Gives
     * the amount that has been collected over the lifetime of the machine
     *
     * @param moneyCollected The money collected by the machine
     * @param reserve The new reserve that is created with default values
     */
    void moneyCollected(int moneyCollected, CoinReserve reserve);
}
