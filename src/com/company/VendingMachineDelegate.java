package com.company;

public interface VendingMachineDelegate {
    String chooseItem();
    String askForItem();
    void showItems(VendingMachineList items);
    void notValidItem(String machineCode);
    void errorMachineOutOfStock(Item item);
    void vendingMachineInformation(VendingMachine machine);
    void vendingMachineDidVendItem(Item item);
    void vendingMachineDidMakeChange(int change);
    int askForQuarters();
    int askForDimes();
    int askForNickels();
    int askForPennies();
//    int askForRestockQuantity(Item item);
//    double askForPriceChange(Item item);
    void notValidOption(String option);
    String operatorAskForOption(VendingMachineList options);
    void showOperatorOptions(VendingMachineList options);
}
