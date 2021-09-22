package com.company;

/**
 * Item class encapsulates data that makes up an item.
 * Item is given a name, price and a machine code
 *
 * @author Kevin Wood
 * @version 1.0
 */
public class Item implements Interactive {
    private final String name;
    private int price;
    private int quantity;
    private String machineCode;

    public Item(String name, int price, String machineCode) {
        this.name = name;
        this.price = price;
        this.quantity = 10;
        this.machineCode = machineCode;
    }

    public Item(String name, int price, int quantity, String machineCode) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.machineCode = machineCode;
    }

    /**
     * Increases the quantity of an item by a certain amount
     *
     * @param quantity The amount you want to increase by
     */
    public void increaseQuantity(int quantity) { this.quantity += quantity; }

    /**
     * Decreases the quantity of an item by a certain amount
     *
     * @param quantity The amount you want to decrease by
     */
    public void decreaseQuantity(int quantity) { this.quantity -= quantity; }

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    void setPrice(int price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getMachineCode() {
        return machineCode;
    }

    @Override
    public String toString() {
        return String.format("%s: $%.2f", this.name, ((double)this.price / 100));
    }
}