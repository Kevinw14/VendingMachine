package com.company;

import java.util.InputMismatchException;

/**
 *
 */
public class VendingMachineController implements VendingMachineDelegate, OperatorManagementSystemDelegate {

    private final TextView textView;

    public VendingMachineController(TextView textView) {
        this.textView = textView;
        InventoryManagementSystem ims = new InventoryManagementSystem();
        CoinReserve reserve = new CoinReserve();
        OperatorManagementSystem oms = new OperatorManagementSystem("Password", reserve);
        oms.setDelegate(this);
        VendingMachine vendingMachine = new VendingMachine(ims, oms, reserve);
        vendingMachine.setDelegate(this);
    }

    /**
     *
     * @param items
     */
    @Override
    public void showItems(VendingMachineList items) {
        for (Interactive item : items) {
            textView.display("%s. %s", item.getMachineCode(), item);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String chooseItem() {
        return textView.prompt("\nWhat would you like to choose? ");
    }

    /**
     *
     * @param machineCode
     */
    @Override
    public void notValidItem(String machineCode) {
        textView.display("%s is not a valid option\n", machineCode);
    }

    /**
     *
     * @param item
     */
    @Override
    public void vendingMachineDidVendItem(Item item) {
        textView.display("\nYou received %s\n", item.getName());
    }

    /**
     *
     * @return
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
     *
     * @return
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
     *
     * @return
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
     *
     * @return
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
     *
     * @param item
     */
    @Override
    public void errorMachineOutOfStock(Item item) {
        textView.display("%s is out of stock \n", item.getName());
    }

    /**
     *
     * @param machine
     */
    @Override
    public void vendingMachineInformation(VendingMachine machine) {
        textView.display(machine);
    }

    /**
     *
     * @param options
     */
    @Override
    public void showOperatorOptions(VendingMachineList options) {
        for (Interactive item : options) {
            textView.display("%s. %s", item.getMachineCode(), item);
        }
    }

    /**
     *
     * @param options
     * @return
     */
    @Override
    public String operatorAskForOption(VendingMachineList options) {
        return textView.prompt("\n\nWhat would you like to do? ");
    }

    /**
     *
     * @param change
     */
    @Override
    public void vendingMachineDidMakeChange(int change) {
        textView.display("You received $%.2f in change\n", (double)change / 100);
    }

    /**
     *
     * @param option
     */
    @Override
    public void notValidOption(String option) {
        textView.display("%s is not a valid option\n", option);
    }

    /**
     *
     */
    @Override
    public void restockQuantityOverThreshold() {
        textView.display("Machine can only hold 10 of each item");
    }

    /**
     *
     * @return
     */
    @Override
    public int askForRestockQuantity() {
        int results = -1;
        while (results < 0) {
            try {
                results = textView.promptForInt("How many would you like to add? ");
                return results;
            } catch(InputMismatchException e) {
                textView.display("Not a valid input");
                textView.clearNumberScanner();
            }
        }
        return results;
    }

    /**
     *
     */
    @Override
    public void priceChangePriceNegative() {
        textView.display("New price cant be negative");
    }

    /**
     *
     * @return
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

    @Override
    public void moneyCollected(int moneyCollected) {
        textView.display("You collected $%.2f\n", (double) moneyCollected / 100);
    }
}
