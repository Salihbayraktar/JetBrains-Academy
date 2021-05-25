package CoffeeMachine;

import java.util.Locale;
import java.util.Scanner;

import static CoffeeMachine.CoffeeMachineUtill.*;

public class CoffeeMachineMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        States stateEnum = States.REMAINING;
        while (!stateEnum.equals(States.EXIT)) {
            System.out.println("Write action (buy, fill, take, remaining, exit)");
            stateEnum = States.valueOf(scanner.next().toUpperCase(Locale.ROOT));
            switch (stateEnum) {
                case BUY -> {
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    String input = scanner.next();
                    if (input.equals("back")) break;
                    int coffeeCode = Integer.parseInt(input);
                    buy(coffeeCode);
                }
                case FILL -> {
                    System.out.println("Write how many ml of water you want to add:");
                    int addedWater = scanner.nextInt();
                    System.out.println("Write how many ml of milk you want to add:");
                    int addedMilk = scanner.nextInt();
                    System.out.println("Write how many grams of coffee beans you want to add:");
                    int addedCoffeeBeans = scanner.nextInt();
                    System.out.println("Write how many disposable cups of coffee you want to add: ");
                    int addedDisposableCups = scanner.nextInt();
                    fill(addedWater, addedMilk, addedCoffeeBeans, addedDisposableCups);
                }
                case TAKE -> take();
                case REMAINING -> printStatus();
                case EXIT -> System.out.println("Have a good day");
                default -> System.out.println("ERROR invalid action you can only write buy, fill, take, remaining or exit.");
            }

        }

    }

    enum States {
        BUY,
        FILL,
        TAKE,
        REMAINING,
        EXIT
    }
}

