package com.company;

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
