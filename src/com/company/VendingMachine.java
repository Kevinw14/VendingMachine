package com.company;

/**
 * VendingMachine encapsulates the data and methods needed to accurately
 * process input from user and give correct item to user.
 * Vending machine uses a loop to perform multiple transactions without exiting
 * the interface.
 *
 * @author Kevin Wood
 * @version 1.0
 */
public class VendingMachine {

    private final InventoryManagementSystem ims;
    private final OperatorManagementSystem oms;
    private CoinReserve reserve;
    private VendingMachineDelegate delegate;
    private boolean isFinished;

    public VendingMachine(InventoryManagementSystem ims, OperatorManagementSystem oms, CoinReserve reserve) {
        this.ims = ims;
        this.oms = oms;
        this.reserve = reserve;
        this.isFinished = false;
    }

    /**
     * Starts when application starts. Loops through showing items and asking for
     * which item the user wants to purchase
     */
    private void customerMode() {
        while (!isFinished) {
            delegate.showItems(ims.getInventory());
            String machineCode = delegate.chooseItem();

            if (machineCode.equalsIgnoreCase("exit")) {
                isFinished = true;
            } else if (machineCode.equals(oms.getPassword())) {
                oms.setInOperatorMode(true);
                operatorMode();
            } else if (machineCode.equalsIgnoreCase("info")) {
                delegate.vendingMachineInformation(this);
            } else if (ims.getInventory().contains(machineCode)) {
                vend(machineCode);
            } else {
                delegate.notValidItem(machineCode);
            }
        }
    }

    /**
     * Gets item the user wants to purchase. If item is in stock
     * will ask user for quarters, dimes, nickels, and pennies.
     * Stores money temporary to check if there is enough money, calculate
     * change, decrease quantity, vend the item and give back change
     *
     * Notifies user if item is out of stock, not enough money inserted, or
     * not enough money to provide change
     *
     * @param machineCode The machine code of the item you want to purchase
     */
    private void vend(String machineCode) {
        Item item = (Item) ims.getInventory().find(machineCode);
        if (ims.isItemInStock(item)) {
            int quarters = delegate.askForQuarters();
            int dimes = delegate.askForDimes();
            int nickels = delegate.askForNickels();
            int pennies = delegate.askForPennies();

            TemporaryStorage storage = new TemporaryStorage(quarters, dimes, nickels, pennies, item, reserve);

            try {
                int change = storage.processTransaction();
                ims.decreaseQuantity(item);
                delegate.vendingMachineDidVendItem(item);
                delegate.vendingMachineDidMakeChange(change);
            } catch (NotEnoughChangeException e) {
                delegate.notEnoughChange();

            } catch (NotEnoughMoneyException e) {
                delegate.notEnoughMoney();
            }
        } else {
            delegate.errorMachineOutOfStock(item);
        }
    }

    /**
     * Gets an item and restock quantity from operator
     * to update an items stock
     */
    private void restockItem() {
        delegate.showItems(ims.getInventory());
        String machineCode = delegate.chooseItem();
        if (ims.getInventory().contains(machineCode)) {
            Item item = (Item) ims.getInventory().find(machineCode);
            oms.restockItem(item);
        } else
            delegate.notValidItem(machineCode);
    }

    /**
     * Gets an item and price from operator
     * to update an items price
     */
    private void changePrice() {
        delegate.showItems(ims.getInventory());
        String machineCode = delegate.chooseItem();
        if (ims.getInventory().contains(machineCode)) {
            Item item = (Item) ims.getInventory().find(machineCode);
            oms.changePrice(item);
        } else
            delegate.notValidItem(machineCode);
    }

    /**
     * Called when operator successfully enters the vending machines password
     * Loops through showing list of options to choose from
     * When finished you can go back to customer mode
     */
    private void operatorMode() {
        while (oms.isInOperatorMode()) {
            delegate.showOperatorOptions(oms.getOptions());
            String option = delegate.operatorAskForOption();
            switch (option) {
                case "1":
                    restockItem();
                    break;
                case "2":
                    changePrice();
                    break;
                case "3":
                    oms.removeChange();
                    break;
                case "4":
                    oms.setInOperatorMode(false);
                    break;
                default:
                    delegate.notValidOption(option);
            }
        }
    }

    /**
     * Sets the delegate and runs customer mode
     * @param delegate object that conforms to the interface
     */
    public void setDelegate(VendingMachineDelegate delegate) {
        this.delegate = delegate;
        customerMode();
    }

    public void setReserves(CoinReserve reserve) {
        this.reserve = reserve;
    }

    @Override
    public String toString() {
        return reserve.toString() + "\n" + ims.toString();
    }
}