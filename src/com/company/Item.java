package com.company;

public class Item implements Interactive {
    private final String name;
    private double price;
    private int quantity;
    private String machineCode;

    public Item(String name, double price, String machineCode) {
        this.name = name;
        this.price = price;
        this.quantity = 10;
        this.machineCode = machineCode;
    }

    public Item(String name, double price, int quantity, String machineCode) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.machineCode = machineCode;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int quantity) { this.quantity += quantity; }
    public void decreaseQuantity(int quantity) { this.quantity -= quantity; }
    @Override
    public String getMachineCode() {
        return machineCode;
    }

    @Override
    public String toString() {
        return String.format("%s: $%.2f", this.name, this.price);
    }
}