package CoffeeMachine;


import CoffeeMachine.Coffees.*;

public class CoffeeMachineUtill {

    static int waterTank = 400;
    static int milkTank = 540;
    static int coffeeCan = 120;
    static int disposableCups = 9;
    static int totalCash = 550;

    public static void printStatus() {
        System.out.printf("""
                        The coffee machine has:
                        %d ml of water
                        %d ml of milk
                        %d g of coffee beans
                        %d disposable cups
                        $%d of money"""
                , waterTank, milkTank, coffeeCan, disposableCups, totalCash);
    }

    public static void buy(int coffeeCode) {

        switch (coffeeCode) {
            case 1 -> buyCoffee(new Espresso());
            case 2 -> buyCoffee(new Latte());
            case 3 -> buyCoffee(new Cappuccino());
            default -> System.out.println("ERROR Invalid coffeeCode you can only put '1 2 3'");
            }
        }

    public static void buyCoffee(Coffee coffee) {

        if (isMaterialEnough(coffee)) {
            waterTank -= coffee.getWaterPerCupCoffee();
            milkTank -= coffee.getMilkPerCupCoffee();
            coffeeCan -= coffee.getCoffeeBeansPerCupCoffee();
            disposableCups--;
            totalCash += coffee.getPrice();
        }
    }

    public static boolean isMaterialEnough(Coffee coffee) {
        if (waterTank - coffee.getWaterPerCupCoffee() < 0) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else if (milkTank - coffee.getMilkPerCupCoffee() < 0) {
            System.out.println("Sorry, not enough milk!");
            return false;
        } else if (coffeeCan - coffee.getCoffeeBeansPerCupCoffee() < 0) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        } else if (disposableCups <= 0) {
            System.out.println("Sorry, not enough coffee disposable cups!");
            return false;
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            return true;
        }

    }


    public static void fill(int addedWater, int addedMilk, int addedCoffeeBeans, int addedDisposableCups) {

        waterTank += addedWater;

        milkTank += addedMilk;

        coffeeCan += addedCoffeeBeans;

        disposableCups += addedDisposableCups;

    }

    public static void take() {
        if (totalCash > 0) {
            System.out.printf("I gave you $%d", totalCash);
            System.out.println();
            totalCash = 0;
        } else System.out.println("ERROR there is no money in the Safe");
    }

}

