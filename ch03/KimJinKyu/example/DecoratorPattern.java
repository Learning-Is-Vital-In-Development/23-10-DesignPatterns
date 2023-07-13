package ch03.KimJinKyu.example;

public class DecoratorPattern {

    public static void main(String[] args) {
        StringTester stringTester = new StringTester(() -> System.out.println("Test 시작"));
        IntegerTester integerTester = new IntegerTester(stringTester);
        StringTester stringTester2 = new StringTester(integerTester);
        stringTester2.test();
    }
}
