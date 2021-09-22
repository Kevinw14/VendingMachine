package com.company;

import java.util.InputMismatchException;

/**
 * The VendingMachineController class handles relaying messages from the view to
 * the VendingMachine object. The VendingMachineController will relay the messages from the VendingMachine
 * object to the view. The VendingMachineController will ask the user what item they would like to purchase.
 * how many quarters, dimes, nickels, and pennies they would like to enter.
 *
 * @author Kevin Wood
 * @version 1.0
 */
public class VendingMachineController implements VendingMachineDelegate, OperatorManagementSystemDelegate {

    private final TextView textView;
    private final VendingMachine vendingMachine;

    public VendingMachineController(TextView textView) {
        this.textView = textView;
        InventoryManagementSystem ims = new InventoryManagementSystem();
        CoinReserve reserve = new CoinReserve();
        OperatorManagementSystem oms = new OperatorManagementSystem("Password", reserve);
        oms.setDelegate(this);
        this.vendingMachine = new VendingMachine(ims, oms, reserve);
        this.vendingMachine.setDelegate(this);
    }

    /**
     * Delegate Method
     *
     * Is called when in customer mode and on start up. Will display to customer until the
     * customer exits the vending machine.
     *
     * @param items Items that are in the vending machine.
     */
    @Override
    public void showItems(VendingMachineList items) {
        for (Interactive item : items) {
            textView.display("%s. %s", item.getMachineCode(), item);
        }
    }

    /**
     * Delegate Method
     *
     * Prompts the user to choose an item they would like to purchase
     *
     * @return the machine code of the item you want.
     */
    @Override
    public String chooseItem() {
        return textView.prompt("\nWhat would you like to choose? ");
    }

    /**
     * Delegate Method
     *
     * Is called when the user enters a machine code that it couldn't find in it inventory.
     *
     * @param machineCode the machine code the user entered that the vending machine
     *                    couldn't find
     */
    @Override
    public void notValidItem(String machineCode) {
        textView.display("%s is not a valid option\n", machineCode);
    }

    /**
     * Delegate Method
     *
     * Is called when a successful transaction occurs. Gives the item you
     * selected and paid for.
     *
     * @param item the item the user receives after a successful transaction.
     */
    @Override
    public void vendingMachineDidVendItem(Item item) {
        textView.display("\nYou received %s\n", item.getName());
    }

    /**
     * Delegate Method
     *
     * Is called when the vending machine wants to know the amount of quarters
     * the user wants to enter
     *
     * @return the number of quarters you want to enter in the machine
     */
    @Override
    public int askForQuarters() {
        int results = -1;

        while (results < 0)
            try {
                results = textView.promptForInt("How many quarters would you like to insert? ");
            } catch (InputMismatchException e) {
                textView.display("Mismatch Error");
                return 0;
            }
        return results;
    }

    /**
     * Delegate Method
     *
     * Is called when the vending machine wants to know the amount of dimes
     * the user wants to enter
     *
     * @return the number of dimes you want to enter in the machine
     */
    @Override
    public int askForDimes() {
        int results = -1;

        while (results < 0) {
            try {
                results = textView.promptForInt("How many dimes would you like to insert? ");
            } catch (InputMismatchException e) {
                textView.display("Mismatch Error");
            }
        }
        return results;
    }

    /**
     * Delegate Method
     *
     * Is called when the vending machine wants to know the amount of nickels
     * the user wants to enter
     *
     * @return the number of nickels you want to enter in the machine
     */
    @Override
    public int askForNickels() {
        int results = -1;

        while (results < 0) {
            try {
                results = textView.promptForInt("How many nickels would you like to insert? ");
            } catch (InputMismatchException e) {
                textView.display("Mismatch Error");
            }
        }
        return results;
    }

    /**
     * Delegate Method
     *
     * Is called when the vending machine wants to know the amount of pennies
     * the user wants to enter
     *
     * @return the number of pennies you want to enter in the machine
     */
    @Override
    public int askForPennies() {
        int results = -1;

        while (results < 0) {
            try {
                results = textView.promptForInt("How many pennies would you like to insert? ");
            } catch (InputMismatchException e) {
                textView.display("Mismatch Error");
            }
        }
        return results;
    }

    /**
     * Delegate Method
     *
     * Called when user selects an item but the item
     * is out of stock
     *
     * @param item the item that is out of stock
     */
    @Override
    public void errorMachineOutOfStock(Item item) {
        textView.display("%s is out of stock \n", item.getName());
    }

    /**
     * Delegate Method
     *
     * Called when info is typed into the console
     *
     * @param machine the current state of the vending machine
     */
    @Override
    public void vendingMachineInformation(VendingMachine machine) {
        textView.display(machine);
    }

    /**
     * Delegate Method
     *
     * Shows the list of available operations the operator
     * is able to perform
     *
     * @param options Options that are available to the operator.
     */
    @Override
    public void showOperatorOptions(VendingMachineList options) {
        for (Interactive item : options) {
            textView.display("%s. %s", item.getMachineCode(), item);
        }
    }

    /**
     * Delegate Method
     *
     * Prompts the operator for the operation they would like to perform
     *
     * @return The machine code of the operation you want to perform
     */
    @Override
    public String operatorAskForOption() {
        return textView.prompt("\n\nWhat would you like to do? ");
    }

    /**
     * Delegate Method
     *
     * Called when transaction was successful and gives the user their change
     *
     * @param change The change that is returned after purchase
     */
    @Override
    public void vendingMachineDidMakeChange(int change) {
        textView.display("You received $%.2f in change\n", (double)change / 100);
    }

    /**
     * Delegate Method
     *
     * Called when user selected an operation that isn't available
     *
     * @param option The machine code of the operation
     */
    @Override
    public void notValidOption(String option) {
        textView.display("%s is not a valid option\n", option);
    }

    /**
     * Delegate Method
     *
     * Called when operator tries to overstock an item over ten
     */
    @Override
    public void restockQuantityOverThreshold() {
        textView.display("Machine can only hold 10 of each item");
    }

    /**
     * Delegate Method
     *
     * Called when vending machine needs to know how much stock to
     * add to the already existing stock
     *
     * @return The amount to restock an item to
     */
    @Override
    public int askForRestockQuantity() {
        int results = -1;

        while (results < 0) {
            try {
                results = textView.promptForInt("How many would you like to add? ");
            } catch(InputMismatchException e) {
                textView.display("Not a valid input");
                textView.clearNumberScanner();
            }
        }

        return results;
    }

    /**
     * Delegate Method
     *
     * Called when operator tries to set an item's price to a negative number
     */
    @Override
    public void priceChangePriceNegative() {
        textView.display("New price cant be negative");
    }

    /**
     * Delegate Method
     *
     * Called when vending machine wants to know the price it should set
     * an item's price to
     *
     * @return The price the operator wants to change an item's price to
     */
    @Override
    public double askForPriceChange() {
        double results = -1;
        while (results < 0) {
            try {
                results = textView.promptForDouble("What would you like the new price to be? ");
            } catch (InputMismatchException e) {
                textView.display("Not a valid input");
                textView.clearNumberScanner();
            }
        }
        return results;
    }

    /**
     *
     * Delegate Method
     *
     * Called when operator removes change from vending machine. Creates a new
     * CoinReserve and adds 10 of each coin
     *
     * @param moneyCollected The amount of money the machine has collected
     *                       from transactions
     * @param reserve The new initialized reserve after taking out profits
     */
    @Override
    public void moneyCollected(int moneyCollected, CoinReserve reserve) {
        vendingMachine.setReserves(reserve);
        textView.display("You collected $%.2f\n", (double) moneyCollected / 100);
    }

    /**
     * Delegate Method
     *
     * Called when the machine can't make change for the transaction
     */
    @Override
    public void notEnoughChange() {
        textView.display("Not enough change in machine to complete transaction");
    }

    /**
     * Delegate Method
     *
     * Called when the user didn't insert enough money for the transaction
     */
    @Override
    public void notEnoughMoney() {
        textView.display("Not enough money was inserted");
    }
}
