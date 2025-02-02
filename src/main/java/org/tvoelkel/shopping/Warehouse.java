package org.tvoelkel.shopping;

import java.util.HashMap;

/**
 * Singleton class, storing prices for all items.
 */
public class Warehouse {
    private final HashMap<String, Item> stock = new HashMap<>();

    private Warehouse() {
        // singleton
    }

    private static final class InstanceHolder {
        private static final Warehouse instance = new Warehouse();
    }

    public static Warehouse getInstance() {
        return InstanceHolder.instance;
    }

    /**
     * Return information of an item from the stock.
     * @param id ID of the item to retrieve.
     * @return Data about an item of the given ID or 'null' if item not in warehouse.
     */
    public Item getItem(String id) {
        return this.stock.get(id);
    }

    /**
     * Add an item to the stock
     * @param item Item to add.
     */
    public void addItem(Item item) {
        stock.put(item.id(), item);
    }
}
