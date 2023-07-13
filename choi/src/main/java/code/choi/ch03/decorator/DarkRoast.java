package code.choi.ch03.decorator;

public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "다크 로스트";
    }

    public double cost() {
        return .99;
    }
}
