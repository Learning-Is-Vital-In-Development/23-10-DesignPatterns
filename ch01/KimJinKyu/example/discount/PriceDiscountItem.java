package ch01.KimJinKyu.example.discount;

public class PriceDiscountItem implements DiscountItem{

    private String code;
    private int discountPrice;

    public PriceDiscountItem(String code, int discountPrice) {
        this.code = code;
        this.discountPrice = discountPrice;
    }

    @Override
    public int discount(int price) {
        return Math.max(price - discountPrice, 0);
    }
}
