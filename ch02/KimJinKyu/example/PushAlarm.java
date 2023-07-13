package ch02.KimJinKyu.example;

public class PushAlarm implements Observer{

    private Product product;

    public PushAlarm(Product product) {
        this.product = product;
    }

    @Override
    public void update() {
        System.out.printf("푸시 발송: 상품 %s 이(가) %d원으로 변경되었습니다.", product.getName(), product.getPrice());
        System.out.println();
    }
}
