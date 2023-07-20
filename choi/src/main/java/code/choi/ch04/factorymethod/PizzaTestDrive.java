package code.choi.ch04.factorymethod;

public class PizzaTestDrive {

    public static void main(String[] args) {
        PizzaStore myStore = new NYPizzaStore();
        ChicagoPizzaStore chicagoStore = new ChicagoPizzaStore();

        Pizza pizza = myStore.orderPizza("cheese");
        System.out.println(pizza.getClass());

        pizza = chicagoStore.orderPizza("cheese");
        System.out.println(pizza.getClass());

        pizza = chicagoStore.orderPizza("clam");
        System.out.println(pizza.getClass());
    }

}
