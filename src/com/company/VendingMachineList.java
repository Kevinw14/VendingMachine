package com.company;
import java.util.ArrayList;

/**
 * A list object that holds Interactive objects
 *
 * Inherits from ArrayList but adds the functionality to search
 * list by machine code
 *
 * @author Kevin Wood
 * @version 1.0
 */
public class VendingMachineList extends ArrayList<Interactive> {

    /**
     * Searches the ArrayList to see if machine code
     * exists in the list
     *
     * @param machineCode The machine code to search
     * @return boolean if it is or isn't in the list
     */
    public boolean contains(String machineCode) {
        for (Interactive element : this) {
            if (element.getMachineCode().equalsIgnoreCase(machineCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Searches the ArrayList and finds the Interactive
     * object with the given machine code
     *
     * @param machineCode The machine code to search
     * @return Interactive object that is found
     */
    public Interactive find(String machineCode) {
        for (Interactive element : this) {
            if (element.getMachineCode().equalsIgnoreCase(machineCode)) {
                return element;
            }
        }
        return null;
    }
}
