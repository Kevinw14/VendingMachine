package com.company;

/**
 *
 */
public interface VendingMachineDelegate {
    /**
     * Delegate Method
     *
     * @return
     */
    String chooseItem();

    /**
     * Delegate Method
     *
     * @param items
     */
    void showItems(VendingMachineList items);

    /**
     * Delegate Method
     *
     * @param machineCode
     */
    void notValidItem(String machineCode);

    /**
     * Delegate Method
     *
     * @param item
     */
    void errorMachineOutOfStock(Item item);

    /**
     * Delegate Method
     *
     * @param machine
     */
    void vendingMachineInformation(VendingMachine machine);

    /**
     * Delegate Method
     *
     * @param item
     */
    void vendingMachineDidVendItem(Item item);

    /**
     * Delegate Method
     *
     * @param change
     */
    void vendingMachineDidMakeChange(int change);

    /**
     * Delegate Method
     *
     * @return
     */
    int askForQuarters();

    /**
     * Delegate Method
     *
     * @return
     */
    int askForDimes();

    /**
     * Delegate Method
     *
     * @return
     */
    int askForNickels();

    /**
     * Delegate Method
     *
     * @return
     */
    int askForPennies();

    /**
     * Delegate Method
     *
     * @param option
     */
    void notValidOption(String option);

    /**
     * Delegate Method
     *
     * @param options
     * @return
     */
    String operatorAskForOption(VendingMachineList options);

    /**
     * Delegate Method
     *
     * @param options
     */
    void showOperatorOptions(VendingMachineList options);
}
