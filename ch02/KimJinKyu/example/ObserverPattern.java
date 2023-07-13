package ch02.KimJinKyu.example;


public class ObserverPattern {

    public static void main(String[] args) {
        Product product1 = new Product("상품1", 1000);
        Product product2 = new Product("상품2", 2000);
        Product product3 = new Product("상품3", 3000);

        EmailAlarm emailAlarm1 = new EmailAlarm(product1);
        EmailAlarm emailAlarm2 = new EmailAlarm(product2);
        PushAlarm pushAlarm1 = new PushAlarm(product1);
        PushAlarm pushAlarm2 = new PushAlarm(product2);

        product1.addObserver(emailAlarm1);
        product2.addObserver(emailAlarm2);

        product1.addObserver(pushAlarm1);
        product2.addObserver(pushAlarm2);

        // 제대로 된 객체를 넣었는 지 검사가 필요할 듯?
        product3.addObserver(emailAlarm1);
        product3.addObserver(pushAlarm2);

        product1.notifyObserver();
        product2.notifyObserver();
        product3.notifyObserver();
    }
}
