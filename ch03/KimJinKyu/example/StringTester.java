package ch03.KimJinKyu.example;

public class StringTester implements Tester{

    private Tester tester;

    public StringTester(Tester tester) {
        this.tester = tester;
    }

    @Override
    public void test() {
        tester.test();
        System.out.println("String Tester");
    }
}
