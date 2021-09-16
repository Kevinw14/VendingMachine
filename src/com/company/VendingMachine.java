package com.company;

public class VendingMachine {

    private final InventoryManagementSystem ims;
    private final OperatorManagementSystem oms;
    private ChangeReserve reserve;
    private VendingMachineDelegate delegate;
    private boolean isFinished;

    public VendingMachine(String password) {
        this.ims = new InventoryManagementSystem();
        this.oms = new OperatorManagementSystem(password);
        this.reserve = new ChangeReserve();
        this.isFinished = false;
    }

    private void customerMode() {
        while (!isFinished) {
            delegate.showItems(ims.getInventory());
            String machineCode = delegate.chooseItem();

            if (machineCode.equalsIgnoreCase("exit")) {
                isFinished = true;
            } else if (machineCode.equals(oms.getPassword())) {
                oms.setInOperatorMode(true);
                operatorMode();
            } else if (ims.getInventory().contains(machineCode)) {
                vend(machineCode);
            } else {
                delegate.notValidItem(machineCode);
            }
        }
    }


    private void vend(String machineCode) {
        Item item = (Item) ims.getInventory().find(machineCode);
        if (ims.isItemInStock(item)) {
            int quarters = delegate.askForQuarters();
            int dimes = delegate.askForDimes();
            int nickels = delegate.askForNickels();
            int pennies = delegate.askForPennies();

            if (reserve.canMakeChange(quarters, dimes, nickels, pennies) &&
                    reserve.canMakePurchase(quarters, dimes, nickels, pennies, item)) {
////                double change = calculateChange(totalAmountProvided, item);
                reserve.updateMoneyCollected(item.getPrice());
                ims.decreaseQuantity(item);
////                decreaseMoneyDeposits(change);
                delegate.vendingMachineDidVendItem(item);
////                delegate.vendingMachineDidMakeChange(this, change);
            }
        } else {
            delegate.errorMachineOutOfStock(item);
        }
    }

    private void restockItem() {
        delegate.showItems(ims.getInventory());
        String machineCode = delegate.askForItem();

        if (ims.getInventory().contains(machineCode)) {
            Item item = (Item) ims.getInventory().find(machineCode);
            int quantity = delegate.askForRestockQuantity(item);
            ims.restockItem(item, quantity);
        } else {
            delegate.notValidItem(machineCode);
        }
    }

    private void operatorMode() {
        while (oms.isInOperatorMode()) {
            delegate.showOperatorOptions(oms.getOptions());
            String option = delegate.operatorAskForOption(oms.getOptions());
            switch (option) {
                case "1":
                    restockItem();
                    break;
                case "2":
                    priceChange();
                    break;
                case "3":
                    delegate.vendingMachineInformation(this);
                    break;
                case "4":
                    oms.setInOperatorMode(false);
                    break;
                default:
                    delegate.notValidOption(option);
            }
        }
    }

    private void priceChange() {
        delegate.showItems(ims.getInventory());
        String machineCode = delegate.askForItem();

        if (ims.getInventory().contains(machineCode)) {
            Item item = (Item) ims.getInventory().find(machineCode);
            double price = delegate.askForPriceChange(item);
            ims.changePrice(item, price);
        } else {
            delegate.notValidItem(machineCode);
        }
    }

    public void setDelegate(VendingMachineDelegate delegate) {
        this.delegate = delegate;
        customerMode();
    }

    public ChangeReserve getChangeReserve() {
        return this.reserve;
    }

    public OperatorManagementSystem getOms() {
        return oms;
    }

    public InventoryManagementSystem getIms() {
        return ims;
    }

    @Override
    public String toString() {
        String description = String.format("Machine has\n" +
                "%d quarters\n" +
                "%d dimes\n" +
                "%d nickels\n" +
                "%d pennies\n" +
                "collected $%.2f\n", reserve.getQuarters(), reserve.getDimes(), reserve.getNickels(), reserve.getPennies(), reserve.getMoneyCollected());
        description += ims;
        return description;
    }
}