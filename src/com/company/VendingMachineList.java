package com.company;
import java.util.ArrayList;

public class VendingMachineList extends ArrayList<Interactive> {

    public boolean contains(String machineCode) {
        for (Interactive element : this) {
            if (element.getMachineCode().equalsIgnoreCase(machineCode)) {
                return true;
            }
        }
        return false;
    }

    public Interactive find(String machineCode) {
        for (Interactive element : this) {
            if (element.getMachineCode().equalsIgnoreCase(machineCode)) {
                return element;
            }
        }
        return null;
    }
}
