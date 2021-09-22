package com.company;

/**
 * Class designed to keep track of operations and the actions
 * those operations perform
 *
 * @author Kevin Wood
 * @version 1.0
 */
public class OperatorManagementSystem {

    private final VendingMachineList options;
    private final String password;
    private boolean inOperatorMode;
    private OperatorManagementSystemDelegate delegate;
    private CoinReserve reserve;

    public OperatorManagementSystem(String password, CoinReserve reserve) {
        this.password = password;
        this.reserve = reserve;
        Option restockItem = new Option("Restock Item", "1");
        Option changePriceOfItem = new Option("Change Price Of Item", "2");
        Option machineInformation = new Option("Remove Change", "3");
        Option goBack = new Option("Go Back\n", "4");

        this.options = new VendingMachineList();
        options.add(restockItem);
        options.add(changePriceOfItem);
        options.add(machineInformation);
        options.add(goBack);
    }

    /**
     * Asks the operator how much of an item to restock
     *
     * @param item Item chosen to be restocked
     */
    public void restockItem(Item item) {
        int quantity = delegate.askForRestockQuantity();
        if (item.getQuantity() + quantity <= 10) {
            item.increaseQuantity(quantity);
        } else {
            delegate.restockQuantityOverThreshold();
        }
    }

    /**
     * Asks the operator what the new items price should be
     *
     * @param item Item chosen to change the price
     */
    public void changePrice(Item item) {
        int price = (int)(delegate.askForPriceChange() * 100);
        if (price >= 0) {
            item.setPrice(price);
        } else {
            delegate.priceChangePriceNegative();
        }
    }

    /**
     * Removes the money collected since the last removal.
     * Resets the reserves back to ten coins each
     */
    public void removeChange() {
        int moneyCollected = reserve.getMoneyCollected();
        reserve = new CoinReserve();
        delegate.moneyCollected(moneyCollected, reserve);
    }

    public void setDelegate(OperatorManagementSystemDelegate delegate) {
        this.delegate = delegate;
    }
    public String getPassword() {
        return password;
    }
    public boolean isInOperatorMode() {
        return inOperatorMode;
    }
    public void setInOperatorMode(boolean inOperatorMode) {
        this.inOperatorMode = inOperatorMode;
    }
    public VendingMachineList getOptions() {
        return options;
    }
}
