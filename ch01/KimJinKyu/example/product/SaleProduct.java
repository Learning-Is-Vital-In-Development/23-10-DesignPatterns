package ch01.KimJinKyu.example.product;

import ch01.KimJinKyu.example.discount.DiscountItem;

public class SaleProduct implements Product{

    private String name;
    private int qty;
    private int price;
    private final DiscountItem discountItem;

    public SaleProduct(String name, int qty, int price, DiscountItem discountItem) {
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.discountItem = discountItem;
    }

    @Override
    public int findTotalPrice() {
        return qty * findDiscountPrice();
    }

    private int findDiscountPrice() {
        return discountItem.discount(price);
    }
}
