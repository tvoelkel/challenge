package org.tvoelkel.shopping;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BasketTest {

    @Test
    void test_totalReturnsCorrectPriceForBuy1Get1Free() {
        Warehouse warehouse = Warehouse.getInstance();
        warehouse.addItem(new Item("A0001", 12.99));
        warehouse.addItem(new Item("A0002", 3.99, true));

        Basket basket = new Basket();
        basket.scan("A0002");
        basket.scan("A0001");
        basket.scan("A0002");

        Assertions.assertEquals(16.98, basket.total());
    }

    @Test
    void test_totalReturnsCorrectPriceForSale() {
        Warehouse warehouse = Warehouse.getInstance();
        warehouse.addItem(new Item("A0001", 12.99, 10.0));
        warehouse.addItem(new Item("A0002", 3.99));

        Basket basket = new Basket();
        basket.scan("A0002");
        basket.scan("A0001");
        basket.scan("A0002");

        Assertions.assertEquals(19.67, basket.total());
    }

    @Test
    void test_totalReturnsCorrectSaleAndBuy1Get1Free() {
        Warehouse warehouse = Warehouse.getInstance();
        warehouse.addItem(new Item("A0001", 10.00, 50.0, true));

        Basket basket = new Basket();
        basket.scan("A0001");
        basket.scan("A0001");
        basket.scan("A0001");
        basket.scan("A0001");
        basket.scan("A0001");

        // Expected total is 1.5x A0001 price, since 2 of A0001 are discounted via buy 1 get 1 free and their total is discounted by 50%
        Assertions.assertEquals(15.0, basket.total());
    }

    @Test
    void test_totalEmptyBasket() {
        Basket basket = new Basket();

        Assertions.assertEquals(0.0, basket.total());
    }

    @Test
    void test_itemNotInWarehouse() {
        Basket basket = new Basket();
        basket.scan("foo");

        Assertions.assertThrows(ItemNotInStockException.class, basket::total);
    }
}