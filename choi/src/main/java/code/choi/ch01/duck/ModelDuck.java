package code.choi.ch01.duck;

public class ModelDuck extends Duck {

    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
        duckType = DuckType.MODEL;
    }

    @Override
    public void display() {
        System.out.println("저는 모형오리입니다.");
    }
}
