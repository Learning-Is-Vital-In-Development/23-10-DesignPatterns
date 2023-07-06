package code.choi.ch01.duck;

public class Main {

    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        System.out.println(mallard.performQuack());
        System.out.println(mallard.performFly());

        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }

}
