package code.choi.ch03.decorator;

public class Whip extends CondimentDecorator {

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ", 휘핑 크림";
    }

    public double cost() {
        return .10 + beverage.cost();
    }
}
