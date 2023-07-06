package code.choi.ch01.duck;

public class MallardDuck extends Duck {

    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
        duckType = DuckType.MALLARD;
    }

    @Override
    public void display() {
        System.out.println("저는 물오리입니다.");
    }

}
