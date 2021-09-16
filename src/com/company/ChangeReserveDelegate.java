package com.company;

public interface ChangeReserveDelegate {
    void errorVendingMachineNotEnoughMoney();
    void errorVendingMachineCantMakeChange();
//    void vendingMachineDidMakeChange(ChangeReserve reserve, double change);
}
