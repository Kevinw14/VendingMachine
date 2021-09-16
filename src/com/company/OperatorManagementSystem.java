package com.company;

public class OperatorManagementSystem {

    private VendingMachineList options;
    private String password;
    private boolean inOperatorMode;

    public OperatorManagementSystem(String password) {
        this.password = password;
        Option restockItem = new Option("Restock Item", "1");
        Option changePriceOfItem = new Option("Change Price Of Item", "2");
        Option machineInformation = new Option("Machine Information", "3");
        Option goBack = new Option("Go Back\n", "4");

        this.options = new VendingMachineList();
        options.add(restockItem);
        options.add(changePriceOfItem);
        options.add(machineInformation);
        options.add(goBack);
    }

        public void changePrice() { }

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
