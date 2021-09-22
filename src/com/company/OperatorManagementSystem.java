package com.company;

/**
 *
 */
public class OperatorManagementSystem {

    private final VendingMachineList options;
    private final String password;
    private boolean inOperatorMode;
    private OperatorManagementSystemDelegate delegate;
    private CoinReserve reserves;

    public OperatorManagementSystem(String password, CoinReserve reserves) {
        this.password = password;
        this.reserves = reserves;
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
     *
     * @param item
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
     *
     * @param item
     */
    public void changePrice(Item item) {
        int price = (int)(delegate.askForPriceChange() * 100);
        if (price >= 0) {
            item.setPrice(price);
        } else {
            delegate.priceChangePriceNegative();
        }
    }

    public void removeChange() {
        int moneyCollected = reserves.getMoneyCollected();
        reserves = new CoinReserve();
        delegate.moneyCollected(moneyCollected);
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
