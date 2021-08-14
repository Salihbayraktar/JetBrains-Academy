package BudgetManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    private static final HashMap<String, Double> foods = new HashMap<>();
    private static final HashMap<String, Double> clothes = new HashMap<>();
    private static final HashMap<String, Double> entertainment = new HashMap<>();
    private static final HashMap<String, Double> other = new HashMap<>();
    private static double balance = 0.00;


    public static void addProduct(HashMap<String, Double> product) {

        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter purchase name:");
        String productName = scanner.nextLine();
        product.put(productName, 0.0);
        System.out.println("Enter its price:");
        double productPrice = Double.parseDouble(scanner.nextLine());
        product.replace(productName, productPrice);
        balance -= productPrice;
        System.out.println("Purchase was added!");
        System.out.println();
    }

    public static void showPurchases(HashMap<String, Double> products) {
        double productsPriceSum = 0.00;

        if (products.isEmpty()) {
            System.out.println("The purchase list is empty!");
        } else {
            for (Map.Entry<String, Double> entry : products.entrySet()) {
                productsPriceSum += entry.getValue();
                System.out.println(entry.getKey() + String.format(" $%.2f", entry.getValue()));
            }
            System.out.printf("Total sum: $%.2f\n", productsPriceSum);
        }
        System.out.println();

    }

    public static void showAllPurchases() {
        double productsPriceSum = 0.00;

        for (Map.Entry<String, Double> entry : foods.entrySet()) {
            productsPriceSum += entry.getValue();
            System.out.println(entry.getKey() + String.format(" $%.2f", entry.getValue()));
        }
        for (Map.Entry<String, Double> entry : clothes.entrySet()) {
            productsPriceSum += entry.getValue();
            System.out.println(entry.getKey() + String.format(" $%.2f", entry.getValue()));
        }
        for (Map.Entry<String, Double> entry : entertainment.entrySet()) {
            productsPriceSum += entry.getValue();
            System.out.println(entry.getKey() + String.format(" $%.2f", entry.getValue()));
        }
        for (Map.Entry<String, Double> entry : other.entrySet()) {
            productsPriceSum += entry.getValue();
            System.out.println(entry.getKey() + String.format(" $%.2f", entry.getValue()));
        }

        System.out.printf("Total sum: $%.2f", productsPriceSum);
        System.out.println();
        System.out.println();

    }

    public static void saveAllPurchases() {
        File purchasesFile = new File("src/BudgetManager/purchases.txt");
        File balanceFile = new File("src/BudgetManager/balance.txt");
        try (FileWriter purchaseWriter = new FileWriter(purchasesFile);
             FileWriter balanceWriter = new FileWriter(balanceFile)) {

            balanceWriter.write(String.valueOf(balance));

            for (Map.Entry<String, Double> product : foods.entrySet()) {
                purchaseWriter.write("1 " + product.getKey() + " $" + product.getValue() + "\n");
            }

            for (Map.Entry<String, Double> product : clothes.entrySet()) {
                purchaseWriter.write("2 " + product.getKey() + " $" + product.getValue() + "\n");
            }

            for (Map.Entry<String, Double> product : entertainment.entrySet()) {
                purchaseWriter.write("3 " + product.getKey() + " $" + product.getValue() + "\n");
            }

            for (Map.Entry<String, Double> product : other.entrySet()) {
                purchaseWriter.write("4 " + product.getKey() + " $" + product.getValue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadAllPurchases() {
        File purchasesFile = new File("src/BudgetManager/purchases.txt");
        File balanceFile = new File("src/BudgetManager/balance.txt");
        try (Scanner purchasesScanner = new Scanner(purchasesFile);
             Scanner balanceScanner = new Scanner(balanceFile)) {
            balance = Double.parseDouble(balanceScanner.nextLine());
            while (purchasesScanner.hasNextLine()) {
                String nextLine = purchasesScanner.nextLine();
                String product = nextLine.substring(0, nextLine.lastIndexOf("$") - 1);
                Double price = Double.valueOf(nextLine.substring(nextLine.lastIndexOf("$") + 1));
                if (product.startsWith("1")) {
                    foods.put(product.substring(2), price);
                } else if (product.startsWith("2")) {
                    clothes.put(product.substring(2), price);
                } else if (product.startsWith("3")) {
                    entertainment.put(product.substring(2), price);
                } else {
                    other.put(product.substring(2), price);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void sortAllPurchases() {

        if (foods.isEmpty() && clothes.isEmpty() && entertainment.isEmpty() && other.isEmpty()) {
            System.out.println("The purchase list is empty!");
            return;
        }

        TreeMap<String, Double> sortedPurchases = new TreeMap<>();
        sortedPurchases.putAll(foods);
        sortedPurchases.putAll(clothes);
        sortedPurchases.putAll(entertainment);
        sortedPurchases.putAll(other);
        System.out.println("All:");
        sortedPurchases.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(i -> System.out.printf("%s $%.2f\n", i.getKey(), i.getValue()));

        double totalPrice = sortedPurchases.values().stream().reduce(0.0, (c, e) -> c += e);
        System.out.printf("Total: $%.2f\n", totalPrice);
    }

    public static void sortByType() {
        double totalFoodPrice = foods.values().stream().reduce(0.0, (c, e) -> c += e);
        double totalEntertainmentPrice = entertainment.values().stream().reduce(0.0, (c, e) -> c += e);
        double totalClothesPrice = clothes.values().stream().reduce(0.0, (c, e) -> c += e);
        double totalOtherPrice = other.values().stream().reduce(0.0, (c, e) -> c += e);
        double totalPrice = totalFoodPrice + totalEntertainmentPrice + totalClothesPrice + totalOtherPrice;

        System.out.printf("""
                Food - $%.2f
                Entertainment - $%.2f
                Clothes - $%.2f
                Other - $%.2f
                Total sum: $%.2f
                """, totalFoodPrice, totalEntertainmentPrice, totalClothesPrice, totalOtherPrice, totalPrice);
    }

    public static void sortCertainType(HashMap<String, Double> products) {
        TreeMap<String, Double> sortedProducts = new TreeMap<>(products);
        sortedProducts.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(i -> System.out.printf("%s $%.2f\n", i.getKey(), i.getValue()));
        double totalPrice = sortedProducts.values().stream().reduce(0.0, (c, e) -> c += e);
        System.out.printf("Total sum: $%.2f\n", totalPrice);
    }

    public static void main(String[] args) {

        String condition;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Choose your action:
                    1) Add income
                    2) Add purchase
                    3) Show list of purchases
                    4) Balance
                    5) Save
                    6) Load
                    7) Analyze (Sort)
                    0) Exit""");
            condition = scanner.nextLine();
            System.out.println();
            switch (condition) {
                case "0":
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
                case "1":
                    System.out.println("Enter income:");
                    balance += Integer.parseInt(scanner.nextLine());
                    System.out.println("Income was added!");
                    System.out.println();
                    break;
                case "2":
                    boolean isPurchaseBack = false;
                    while (!isPurchaseBack) {
                        System.out.println("""
                                Choose the type of purchase
                                1) Food
                                2) Clothes
                                3) Entertainment
                                4) Other
                                5) Back""");
                        String purchaseCondition = scanner.nextLine();
                        switch (purchaseCondition) {
                            case "1" -> addProduct(foods);
                            case "2" -> addProduct(clothes);
                            case "3" -> addProduct(entertainment);
                            case "4" -> addProduct(other);
                            case "5" -> {
                                System.out.println();
                                isPurchaseBack = true;
                            }
                        }
                    }

                    break;
                case "3":
                    if (foods.isEmpty() && clothes.isEmpty() && entertainment.isEmpty() && other.isEmpty()) {
                        System.out.println("The purchase list is empty!");
                        System.out.println();
                    } else {
                        boolean isListBack = false;
                        while (!isListBack) {
                            System.out.println("""
                                    Choose the type of purchases
                                    1) Food
                                    2) Clothes
                                    3) Entertainment
                                    4) Other
                                    5) All
                                    6) Back""");
                            String showListCondition = scanner.nextLine();
                            System.out.println();
                            switch (showListCondition) {
                                case "1" -> {
                                    System.out.println("Food:");
                                    showPurchases(foods);
                                }
                                case "2" -> {
                                    System.out.println("Clothes:");
                                    showPurchases(clothes);
                                }
                                case "3" -> {
                                    System.out.println("Entertainment:");
                                    showPurchases(entertainment);
                                }
                                case "4" -> {
                                    System.out.println("Other:");
                                    showPurchases(other);
                                }
                                case "5" -> {
                                    System.out.println("All:");
                                    showAllPurchases();
                                }
                                case "6" -> isListBack = true;
                            }
                        }
                    }
                    break;
                case "4":
                    System.out.printf("Balance: $%.2f", balance);
                    System.out.println();
                    System.out.println();
                    break;
                case "5":
                    saveAllPurchases();
                    System.out.println("Purchases were saved!");
                    System.out.println();
                    break;
                case "6":
                    loadAllPurchases();
                    System.out.println("Purchases were loaded!");
                    System.out.println();
                    break;
                case "7":
                    boolean isAnalyzeBack = false;
                    while (!isAnalyzeBack) {
                        System.out.println("""
                                How do you want to sort?
                                1) Sort all purchases
                                2) Sort by type
                                3) Sort certain type
                                4) Back""");
                        String analyzeCondition = scanner.nextLine();
                        System.out.println();
                        switch (analyzeCondition) {
                            case "1" -> {
                                sortAllPurchases();
                                System.out.println();
                            }
                            case "2" -> {
                                System.out.println("Types:");
                                sortByType();
                                System.out.println();
                            }
                            case "3" -> {
                                System.out.print("""
                                        Choose the type of purchase
                                        1) Food
                                        2) Clothes
                                        3) Entertainment
                                        4) Other
                                        """);
                                String typeCondition = scanner.nextLine();
                                System.out.println();
                                switch (typeCondition) {
                                    case "1" -> {
                                        if (foods.isEmpty()) {
                                            System.out.println("The purchase list is empty!");
                                        } else {
                                            System.out.println("Food:");
                                            sortCertainType(foods);
                                        }
                                        System.out.println();
                                    }
                                    case "2" -> {
                                        if (clothes.isEmpty()) {
                                            System.out.println("The purchase list is empty!");
                                        } else {
                                            System.out.println("Clothes:");
                                            sortCertainType(clothes);
                                        }
                                        System.out.println();
                                    }
                                    case "3" -> {
                                        if (entertainment.isEmpty()) {
                                            System.out.println("The purchase list is empty!");
                                        } else {
                                            System.out.println("Entertainment:");
                                            sortCertainType(entertainment);
                                        }
                                        System.out.println();
                                    }
                                    case "4" -> {
                                        if (other.isEmpty()) {
                                            System.out.println("The purchase list is empty!");
                                        } else {
                                            System.out.println("Other:");
                                            sortCertainType(other);
                                        }
                                        System.out.println();
                                    }
                                }
                            }
                            case "4" -> isAnalyzeBack = true;
                        }
                    }
                    break;
            }
        }
    }
}

