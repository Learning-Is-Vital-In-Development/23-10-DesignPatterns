package ch01.KimJinKyu.example;

import ch01.KimJinKyu.example.discount.PercentDiscountItem;
import ch01.KimJinKyu.example.discount.PriceDiscountItem;
import ch01.KimJinKyu.example.product.SaleProduct;

public class StrategyPattern {

    public static void main(String[] args) {
        PercentDiscountItem percentDiscountItem1 = new PercentDiscountItem("TEST1", 10);
        PriceDiscountItem priceDiscountItem2 = new PriceDiscountItem("TEST2", 200);
        SaleProduct product1 = new SaleProduct("A", 1, 1000, percentDiscountItem1);
        SaleProduct product2 = new SaleProduct("B", 1, 1000, priceDiscountItem2);

        System.out.println("product1.findTotalPrice() = " + product1.findTotalPrice());
        System.out.println("product2.findTotalPrice() = " + product2.findTotalPrice());

    }
}