package com.company;

import java.util.ArrayList;

public class InventoryManagementSystem {

    private VendingMachineList inventory;

    public InventoryManagementSystem() {
        this.inventory = new VendingMachineList();
        Item kitkat = new Item("Kit Kat", 100, 0, "1");
        Item reeses = new Item("Reese's", 150, "2");
        Item crunch = new Item("Crunch", 110, "3");
        Item pretzels = new Item("Pretzels", 55, "4");
        Item gum = new Item("Gum", 12, "5");

        inventory.add(kitkat);
        inventory.add(reeses);
        inventory.add(crunch);
        inventory.add(pretzels);
        inventory.add(gum);
    }

    public void restockItem(Item item, int quantity) {
        item.increaseQuantity(quantity);
    }

    public void changePrice(Item item, int price) {
        item.setPrice(price);
    }

    public boolean isItemInStock(Item item) {
        if (item.getQuantity() > 0) {
            return true;
        }
        return false;
    }

    public void decreaseQuantity(Item item) {
        item.decreaseQuantity(1);
    }

    public VendingMachineList getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
        String description = "";
        for (Interactive item: inventory) {
            Item convertedItem = (Item)item;
            String formattedString = String.format("%s, %d In Stock, $%.2f\n", convertedItem.getName(), convertedItem.getQuantity(), ((double)convertedItem.getPrice() / 100));
            description += formattedString;
        }
        return description;
    }
}
