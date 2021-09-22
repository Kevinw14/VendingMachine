package com.company;

/**
 * Option class encapsulates data that makes up an option.
 * Option is given a name and a machine code
 *
 * @author Kevin Wood
 * @version 1.0
 */
public class Option implements Interactive {
    private String name;
    private String machineCode;

    public Option(String name, String machineCode) {
        this.name = name;
        this.machineCode = machineCode;
    }

    @Override
    public String getMachineCode() {
        return machineCode;
    }

    @Override
    public String toString() {
        return name;
    }
}
