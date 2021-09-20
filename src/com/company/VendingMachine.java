package com.company;

public class VendingMachine {

    private final InventoryManagementSystem ims;
    private final OperatorManagementSystem oms;
    private final CoinReserve reserves;
    private VendingMachineDelegate delegate;
    private boolean isFinished;

    public VendingMachine(InventoryManagementSystem ims, OperatorManagementSystem oms, CoinReserve reserves) {
        this.ims = ims;
        this.oms = oms;
        this.reserves = reserves;
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
            } else if (machineCode.equalsIgnoreCase("info")) {
                delegate.vendingMachineInformation(this);
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

            TemporaryStorage storage = new TemporaryStorage(quarters, dimes, nickels, pennies, item, reserves);

            try {
                int change = storage.processTransaction();
                ims.decreaseQuantity(item);
                delegate.vendingMachineDidVendItem(item);
                delegate.vendingMachineDidMakeChange(change);
            } catch (NotEnoughChangeException e) {
                System.out.println("Not enough change");

            } catch (NotEnoughMoneyException e) {
                System.out.println("Not enough money");
            }
        } else {
            delegate.errorMachineOutOfStock(item);
        }
    }

//    private void restockItem() {
//        delegate.showItems(ims.getInventory());
//        String machineCode = delegate.askForItem();
//
//        if (ims.getInventory().contains(machineCode)) {
//            Item item = (Item) ims.getInventory().find(machineCode);
//            int quantity = delegate.askForRestockQuantity(item);
//            ims.restockItem(item, quantity);
//        } else {
//            delegate.notValidItem(machineCode);
//        }
//    }

    private void operatorMode() {
        while (oms.isInOperatorMode()) {
            delegate.showOperatorOptions(oms.getOptions());
            String option = delegate.operatorAskForOption(oms.getOptions());
            switch (option) {
                case "1":
//                    restockItem();
                    break;
                case "2":
//                    priceChange();
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

//    private void priceChange() {
//        delegate.showItems(ims.getInventory());
//        String machineCode = delegate.askForItem();
//
//        if (ims.getInventory().contains(machineCode)) {
//            Item item = (Item) ims.getInventory().find(machineCode);
//            double price = delegate.askForPriceChange(item);
//            ims.changePrice(item, (int)(price * 100));
//        } else {
//            delegate.notValidItem(machineCode);
//        }
//    }

    public void setDelegate(VendingMachineDelegate delegate) {
        this.delegate = delegate;
        customerMode();
    }

    public OperatorManagementSystem getOms() {
        return oms;
    }

    public InventoryManagementSystem getIms() {
        return ims;
    }

    @Override
    public String toString() {
        return reserves.toString() + "\n" + ims.toString();
    }
}