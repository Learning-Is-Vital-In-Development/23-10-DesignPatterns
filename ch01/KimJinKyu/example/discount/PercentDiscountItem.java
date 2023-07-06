package ch01.KimJinKyu.example.discount;

public class PercentDiscountItem implements DiscountItem{

    private String code;
    private int discountPercent;

    public PercentDiscountItem(String code, int discountPercent) {
        this.code = code;
        this.discountPercent = discountPercent;
    }

    @Override
    public int discount(int price) {
        double percent = ((double) Math.max(100 - discountPercent, 0) / 100);
        return (int)(price * percent);
    }
}
