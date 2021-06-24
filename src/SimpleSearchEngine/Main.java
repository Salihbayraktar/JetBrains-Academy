package SimpleSearchEngine;

import SimpleSearchEngine.Strategies.SearchAll;
import SimpleSearchEngine.Strategies.SearchAny;
import SimpleSearchEngine.Strategies.SearchNone;
import SimpleSearchEngine.Strategies.StrategyContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    @Deprecated
    public static Map<Integer, String> createDataBaseWithConsole(int dataLength) {
        Map<Integer, String> dataBase = new HashMap<>();
        int counter = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter all people:");
        for (int i = 0; i < dataLength; i++) {
            dataBase.put(counter++, scanner.nextLine());
        }
        return dataBase;
    }

    @Deprecated
    public static void findAndPrintQuery(Map<Integer, String> dataBase, String query) {
        List<String> searchResult = dataBase.values().stream()
                .filter(e -> e.toLowerCase()
                        .contains(query.toLowerCase()))
                .collect(Collectors.toList());
        if (searchResult.size() != 0) searchResult.forEach(System.out::println);
        else System.out.println("No matching people found.");
    }

    public static Map<Integer, String> getDataBaseFromFile(String fileLocation) {

        File file = new File(fileLocation);
        Map<Integer, String> dataBase = new HashMap<>();
        int counter = 0;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                dataBase.put(counter++, scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return dataBase;
    }

    public static Map<String, List<Integer>> createInvertedIndex(Map<Integer, String> dataBase) {

        Map<String, List<Integer>> invertedIndex = new HashMap<>();

        for (Map.Entry<Integer, String> stringMap : dataBase.entrySet()) {

            String[] words = stringMap.getValue().split(" ");

            for (String word : words) {

                List<Integer> indexes = new LinkedList<>();

                for (Map.Entry<Integer, String> data : dataBase.entrySet()) {

                    if (data.getValue().toLowerCase().contains(word.toLowerCase())) {
                        indexes.add(data.getKey());
                    }
                }
                invertedIndex.put(word.toLowerCase(), indexes);
            }
        }

        return invertedIndex;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<Integer, String> dataBase = getDataBaseFromFile("./src/SimpleSearchEngine/text.txt");

        Map<String, List<Integer>> indexMap = createInvertedIndex(dataBase);

        while (true) {
            System.out.println("""
                    === Menu ===
                    1. Find a person
                    2. Print all people
                    0. Exit
                    """);
            int value = scanner.nextInt();
            scanner.nextLine();
            switch (value) {
                case 1:
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    String strategy = scanner.nextLine().toLowerCase();
                    System.out.println("Enter a name or email to search all suitable people.");
                    String query = scanner.nextLine().toLowerCase();
                    Set<Integer> result = new HashSet<>();
                    StrategyContext context;
                    switch (strategy.toUpperCase()) {
                        case "ALL" -> {
                            context = new StrategyContext(new SearchAll());
                            result = context.getResult(dataBase, indexMap, query);
                        }
                        case "ANY" -> {
                            context = new StrategyContext(new SearchAny());
                            result = context.getResult(dataBase, indexMap, query);
                        }
                        case "NONE" -> {
                            context = new StrategyContext(new SearchNone());
                            result = context.getResult(dataBase, indexMap, query);
                        }
                        default -> System.out.println("INVAlID STRATEGY");
                    }
                    if (result.size() != 0) {
                        result.forEach(e -> System.out.println(dataBase.get(e)));
                    } else {
                        System.out.println("No matching people found.");
                    }
                    break;
                case 2:
                    for (String s : dataBase.values()) System.out.println(s);
                    break;
                case 0:
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
            }
        }
    }
}
