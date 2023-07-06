package code.choi.ch01.duck;

public class FlyNoWay implements FlyBehavior{

    @Override
    public String fly() {
        return "날지 못해요!!";
    }

}
