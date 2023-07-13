package ch02.KimJinKyu.example;

public class EmailAlarm implements Observer{

    private Product product;

    public EmailAlarm(Product product) {
        this.product = product;
    }

    @Override
    public void update() {
        System.out.printf("이메일 전송: 상품 %s 이(가) %d원으로 변경되었습니다.", product.getName(), product.getPrice());
        System.out.println();
    }
}
