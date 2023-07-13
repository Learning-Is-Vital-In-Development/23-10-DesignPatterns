package ch03.KimJinKyu.example;

public class IntegerTester implements Tester{

    private Tester tester;

    public IntegerTester(Tester tester) {
        this.tester = tester;
    }

    @Override
    public void test() {
        tester.test();
        System.out.println("Integer Tester");
    }
}
