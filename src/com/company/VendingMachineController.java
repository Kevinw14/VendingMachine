package com.company;

import java.util.InputMismatchException;

public class VendingMachineController implements VendingMachineDelegate, ChangeReserveDelegate {

    private final TextView textView;
    private final VendingMachine vendingMachine;

    public VendingMachineController(TextView textView) {
        this.textView = textView;
        this.vendingMachine = new VendingMachine("Password");
        this.vendingMachine.getChangeReserve().setDelegate(this);
        this.vendingMachine.setDelegate(this);
    }

    @Override
    public void showItems(VendingMachineList items) {
        for (int i = 0; i < items.size(); i++) {
            Interactive item = items.get(i);
            textView.display("%s. %s", item.getMachineCode(), item);
        }
    }

    @Override
    public String chooseItem() {
        String item = textView.prompt("\nWhat would you like to purchase? ");
        return item;
    }

    @Override
    public void notValidItem(String machineCode) {
        textView.display("%s is not a valid option\n", machineCode);
    }

    @Override
    public void vendingMachineDidVendItem(Item item) {
        textView.display("\nYou received %s\n", item.getName());
    }

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

    @Override
    public void errorMachineOutOfStock(Item item) {
        textView.display("%s is out of stock \n", item.getName());
    }

    @Override
    public void vendingMachineInformation(VendingMachine machine) {
        textView.display(machine);
    }

    @Override
    public void notValidOption(String option) {
        textView.display("Not a valid option %s\n", option);
    }

    @Override
    public void errorVendingMachineCantMakeChange() {
        textView.display("\nVending Machine doesn't have enough change");
    }

    @Override
    public String askForItem() {
        String item = textView.prompt("\nWhat would you like to choose? ");
        return item;
    }

    @Override
    public int askForRestockQuantity(Item item) {
        int results = -1;

        while (results < 0) {
            try {
                textView.display("How many would you like to add to the stock?");
                results = textView.promptForInt();
            } catch (InputMismatchException e) {
                textView.display("Not a valid number");
                textView.prompt("");
            }
        }
        return results;
    }

//    @Override
//    public void vendingMachineDidMakeChange(VendingMachine machine, double change) {
//        textView.display("Your change is %.2f\n", change);
//    }

    @Override
    public void showOperatorOptions(VendingMachineList options) {
        for (int i = 0; i < options.size(); i++) {
            Interactive item = options.get(i);
            textView.display("%s. %s", item.getMachineCode(), item);
        }
    }

    @Override
    public String operatorAskForOption(VendingMachineList options) {
        String option = textView.prompt("\n\nWhat would you like to do? ");
        return option;
    }

    @Override
    public double askForPriceChange(Item item) {
        double results = -1;

        while (results < 0) {
            try {
                textView.display("What do you want to change %s to. %s is currently at $%.2f\n", item.getName(), item.getName(), item.getPrice());
                results = textView.promptForDouble();
            } catch (InputMismatchException e) {
                textView.prompt("Invalid Characters Please Try Again\n");
            }
        }

        return results;
    }

    @Override
    public void errorVendingMachineNotEnoughMoney() {
        textView.display("\nPlease enter enough money to make the purchase");
    }
}
