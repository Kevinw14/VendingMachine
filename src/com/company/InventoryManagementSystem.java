package com.company;

/**
 *
 */
public class InventoryManagementSystem {

    private final VendingMachineList inventory;

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

    /**
     * Checks to make sure the item is in stock before going
     * furthur in the transaction
     *
     * @param item The item the user chooses
     * @return boolean if the item is in stock
     */
    public boolean isItemInStock(Item item) {
        return item.getQuantity() > 0;
    }

    /**
     * Called when a transaction is successful and decreases the inventory by one
     * on the item bought
     * @param item The item to decrease the quantity
     */
    public void decreaseQuantity(Item item) {
        item.decreaseQuantity(1);
    }
    public VendingMachineList getInventory() {
        return inventory;
    }
    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        for (Interactive item: inventory) {
            Item convertedItem = (Item)item;
            String formattedString = String.format("%s, %d In Stock, $%.2f\n", convertedItem.getName(), convertedItem.getQuantity(), ((double)convertedItem.getPrice() / 100));
            description.append(formattedString);
        }
        return description.toString();
    }
}
