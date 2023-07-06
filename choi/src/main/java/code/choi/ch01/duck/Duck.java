package code.choi.ch01.duck;

public abstract class Duck {

    DuckType duckType;
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck() {
    }

    public abstract void display();

    public String performFly(){
        return flyBehavior.fly();
    }

    public String performQuack(){
        return quackBehavior.quack();
    }

    public void setFlyBehavior(FlyBehavior fb){
        flyBehavior = fb;
    }

    public void setQuackBehavior(QuackBehavior qb){
        quackBehavior = qb;
    }

    public DuckType getDuckType() {
        return duckType;
    }
}
