package com.company;

/**
 * Delegate that helps the model communicate with the VendingMachineController, to update the
 * view.
 *
 * @author Kevin Wood
 * @version 1.0
 */
public interface VendingMachineDelegate {
    /**
     * Delegate Method
     *
     * Will be called when machine wants to know
     * what the user wants to purchase
     *
     * @return The machine code of the item
     */
    String chooseItem();

    /**
     * Delegate Method
     *
     * Will be called on startup and every cycle until the user exits the machine
     *
     * @param items The list of items that the machine has to offer
     */
    void showItems(VendingMachineList items);

    /**
     * Delegate Method
     *
     * Called when user enters a machine code that the machine
     * can't find in it's items
     *
     * @param machineCode The machine code the user entered
     */
    void notValidItem(String machineCode);

    /**
     * Delegate Method
     *
     * Called when user tries to purchase an item that is out of stock
     *
     * @param item The item that is out of stock
     */
    void errorMachineOutOfStock(Item item);

    /**
     * Delegate Method
     *
     * Called when operator wants to know the current state of the
     * vending machine
     *
     * @param machine The current vending machine object
     */
    void vendingMachineInformation(VendingMachine machine);

    /**
     * Delegate Method
     *
     * Called on a successful transaction and the user receives
     * their item
     *
     * @param item The item that was successfully vended
     */
    void vendingMachineDidVendItem(Item item);

    /**
     * Delegate Method
     *
     * Called after a successful transaction and the user receives their
     * change back
     *
     * @param change The change the user receives after a successful transaction
     */
    void vendingMachineDidMakeChange(int change);

    /**
     * Delegate Method
     *
     * Called when the vending machine wants to know how many
     * quarters the user wants to insert
     *
     * @return The amount of quarters the user wants to insert
     */
    int askForQuarters();

    /**
     * Delegate Method
     *
     * Called when the vending machine wants to know how many
     * dimes the user wants to insert
     *
     * @return The amount of dimes the user wants to insert
     */
    int askForDimes();

    /**
     * Delegate Method
     *
     * Called when the vending machine wants to know how many
     * nickels the user wants to insert
     *
     * @return The amount of nickels the user wants to insert
     */
    int askForNickels();

    /**
     * Delegate Method
     *
     * Called when the vending machine wants to know how many
     * pennies the user wants to insert
     *
     * @return The amount of pennies the user wants to insert
     */
    int askForPennies();

    /**
     * Delegate Method
     *
     * Called when vending machine can't find the machine code for
     * the operation
     *
     * @param option The machine code for the operation
     */
    void notValidOption(String option);

    /**
     * Delegate Method
     *
     * Called when the vending machine wants to know the
     * operation the operator wants to perform
     *
     * @return The machine code for the operation
     */
    String operatorAskForOption();

    /**
     * Delegate Method
     *
     * Called when password was successful and called every cycle
     * until operator exits back to customer mode
     *
     * @param options List of options available to the operator
     */
    void showOperatorOptions(VendingMachineList options);

    /**
     * Delegate Method
     *
     * Called when user doesn't insert enough money for the transaction
     */
    void notEnoughMoney();

    /**
     * Called when vending machine doesn't have enough change for the transaction
     */
    void notEnoughChange();

}
