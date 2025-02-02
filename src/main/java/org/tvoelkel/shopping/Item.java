package org.tvoelkel.shopping;

/**
 * Class describing a shop item.
 * @param id Name of the item.
 * @param price Price of the item.
 * @param percentOff Percentage amount by which price is reduced. Expected value between 0.0 and 100.0.
 * @param isGetOneFree Flag, whether every second item of this type is free.
 */
public record Item(String id, double price, double percentOff, boolean isGetOneFree) {
    public Item(String id, double price) {
        this(id, price, 0.0, false);
    }

    public Item(String id, double price, double percentOff) {
        this(id, price, percentOff, false);
    }

    public Item(String id, double price, boolean isGetOneFree) {
        this(id, price, 0.0, isGetOneFree);
    }
}
