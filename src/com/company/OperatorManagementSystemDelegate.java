package com.company;

public interface OperatorManagementSystemDelegate {
    /**
     * Delegate Method
     *
     * @return
     */
    int askForRestockQuantity();

    /**
     * Delegate Method
     *
     * @return
     */
    double askForPriceChange();

    /**
     * Delegate Method
     *
     *
     */
    void restockQuantityOverThreshold();

    /**
     * Delegate Method
     *
     *
     */
    void priceChangePriceNegative();

    /**
     *
     * @param moneyCollected
     */
    void moneyCollected(int moneyCollected);
}
