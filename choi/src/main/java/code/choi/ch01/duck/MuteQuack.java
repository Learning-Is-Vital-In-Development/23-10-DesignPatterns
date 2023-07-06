package code.choi.ch01.duck;

public class MuteQuack implements QuackBehavior {

    @Override
    public String quack() {
        return "<< 조용~ >>";
    }

}
