package code.choi.ch03.decorator;

public abstract class CondimentDecorator extends Beverage {

    Beverage beverage;
    public abstract String getDescription();
}