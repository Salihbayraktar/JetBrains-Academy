package CoffeeMachine.Coffees;

public class Coffee {

    private int waterPerCupCoffee;
    private int milkPerCupCoffee;
    private int coffeeBeansPerCupCoffee;
    private int price;

    public Coffee(int waterPerCupCoffee, int milkPerCupCoffee, int coffeeBeansPerCupCoffee, int price) {
        this.waterPerCupCoffee = waterPerCupCoffee;
        this.milkPerCupCoffee = milkPerCupCoffee;
        this.coffeeBeansPerCupCoffee = coffeeBeansPerCupCoffee;
        this.price = price;
    }

    public int getWaterPerCupCoffee() {
        return waterPerCupCoffee;
    }

    public int getMilkPerCupCoffee() {
        return milkPerCupCoffee;
    }

    public int getCoffeeBeansPerCupCoffee() {
        return coffeeBeansPerCupCoffee;
    }

    public int getPrice() {
        return price;
    }
}







