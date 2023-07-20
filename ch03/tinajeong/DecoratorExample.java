package ch02.tinajeong.decorator;

public class DecoratorExample {

    public static void main(String[] args) {
        Food myFood = new Pizza();
        myFood = new Cheese(myFood);
        myFood = new Olives(myFood);

        System.out.println(myFood.getDescription() + " costs " + myFood.cost());
    }

    public abstract static class Food {
        String description = "Unknown Food";

        public String getDescription() {
            return description;
        }

        public abstract double cost();
    }

    public static class Pizza extends Food {
        public Pizza() {
            description = "Pizza";
        }

        public double cost() {
            return 1.99;
        }
    }

    public static class Burger extends Food {
        public Burger() {
            description = "Burger";
        }

        public double cost() {
            return 2.99;
        }
    }

    public abstract static class ToppingDecorator extends Food {
        public abstract String getDescription();
    }

    public static class Cheese extends ToppingDecorator {
        Food food;

        public Cheese(Food food) {
            this.food = food;
        }

        public String getDescription() {
            return food.getDescription() + ", Cheese";
        }

        public double cost() {
            return 0.25 + food.cost();
        }
    }

    public static class Olives extends ToppingDecorator {
        Food food;

        public Olives(Food food) {
            this.food = food;
        }

        public String getDescription() {
            return food.getDescription() + ", Olives";
        }

        public double cost() {
            return 0.30 + food.cost();
        }
    }
}
