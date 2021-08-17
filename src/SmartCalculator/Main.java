package SmartCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

public class Main {

    private static final HashMap<String, BigDecimal> variables = new HashMap<>();

    public static String infixToPostfix(String infixEquation) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> symbolsStack = new Stack<>();

        String[] elements = infixEquation.split(" ");
        HashMap<Character, Integer> priorityMap = new HashMap<>();
        priorityMap.put('^', 3);
        priorityMap.put('*', 2);
        priorityMap.put('/', 2);
        priorityMap.put('+', 1);
        priorityMap.put('-', 1);
        priorityMap.put('(', 0);
        priorityMap.put(')', 0);

        int previousPriory = 0;
        int currentPriory;

        for (String element : elements) {
            if (element.matches("[\\^/*\\-+()]")) {

                Character symbol = element.charAt(0);
                currentPriory = priorityMap.get(symbol);

                if (symbol.equals('(')) {
                    symbolsStack.push(symbol);
                    previousPriory = priorityMap.get(symbol);
                    continue;
                } else if (symbol.equals(')')) {
                    while (!symbolsStack.peek().equals('(')) {
                        postfix.append(symbolsStack.pop()).append(" ");
                    }
                    symbolsStack.pop();
                    previousPriory = priorityMap.get(symbolsStack.peek());
                    continue;
                }

                if (!symbolsStack.isEmpty()) {

                    while (currentPriory <= previousPriory) {
                        postfix.append(symbolsStack.pop()).append(" ");
                        if (symbolsStack.isEmpty()) {
                            previousPriory = 0;
                        } else {
                            previousPriory = priorityMap.get(symbolsStack.peek());
                        }
                    }
                }
                symbolsStack.push(symbol);
                previousPriory = priorityMap.get(symbol);
            } else {
                postfix.append(element).append(" ");
            }
        }
        while (!symbolsStack.isEmpty()) {
            postfix.append(symbolsStack.pop()).append(" ");
        }

        return postfix.toString().trim();
    }

    public static BigDecimal calculatePostfix(String postfixEquation) {
        String[] equationElements = postfixEquation.split("\\s");
        Stack<BigDecimal> calculateStack = new Stack<>();
        for (String element : equationElements) {
            if (element.matches("([0-9]*[.]?[0-9]+)")) {
                calculateStack.push(new BigDecimal(element));
            } else if (element.matches("([a-zA-Z]*)")) {
                if (variables.containsKey(element)) {
                    calculateStack.push(variables.get(element));
                } else {
                    System.out.println("No such element!");
                }
            } else if (element.matches("\\^")) {
                BigDecimal secondNum = calculateStack.pop();
                BigDecimal firstNum = calculateStack.pop();
                calculateStack.push(firstNum.pow(secondNum.intValue()));
            } else if (element.matches("\\*")) {
                BigDecimal secondNum = calculateStack.pop();
                BigDecimal firstNum = calculateStack.pop();
                calculateStack.push(firstNum.multiply(secondNum));
            } else if (element.matches("/")) {
                BigDecimal secondNum = calculateStack.pop();
                BigDecimal firstNum = calculateStack.pop();
                calculateStack.push(firstNum.divide(secondNum, 5, RoundingMode.DOWN));
            } else if (element.matches("\\+")) {
                BigDecimal secondNum = calculateStack.pop();
                BigDecimal firstNum = calculateStack.pop();
                calculateStack.push(firstNum.add(secondNum));
            } else if (element.matches("-")) {
                BigDecimal secondNum = calculateStack.pop();
                BigDecimal firstNum = calculateStack.pop();
                calculateStack.push(firstNum.subtract(secondNum));
            } else {
                System.out.println("Invalid input");
            }
        }
        BigDecimal result = calculateStack.pop();
        if (!calculateStack.isEmpty()) {
            System.out.println("Stack still has number wrong input!!!");
            return BigDecimal.ZERO;
        }
        return result;
    }


    public static boolean checkInput(String input) {

        //Invalid character control
        if (Pattern.compile("[^a-zA-Z0-9^/*\\-+()=\\s.]").matcher(input).find()) {
            return false;
        }
        //Invalid character control

        // Start and end control
        if (Pattern.compile("^[*/^]").matcher(input).find() || Pattern.compile("[*/^\\-+]$").matcher(input).find()) {
            return false;
        }
        // Start and end control

        //Brackets control
        String spacelessInput = input.replaceAll(" ", "");
        if (Pattern.compile("([^+\\-*/^()][(])|([)][^+\\-*/^()])").matcher(spacelessInput).find()) {
            return false;
        }

        int bracketsCounter = 0;
        for (int i = 0; i < input.length(); i++) {
            if (bracketsCounter < 0) {
                return false;
            }
            if (input.charAt(i) == '(') {
                bracketsCounter++;
            } else if (input.charAt(i) == ')') {
                bracketsCounter--;
            }
        }
        if (bracketsCounter != 0) {
            return false;
        }
        //Brackets control

        // Multiple operator control
        if (Pattern.compile("([*/^]{2,})").matcher(input).find()) {
            return false;
        }
        // Multiple operator control

        //Variable name control
        for (int i = 1; i < input.length(); i++) {
            char prevChar = input.charAt(i - 1);
            char currentChar = input.charAt(i);
            if ((Character.isDigit(prevChar) && Character.isAlphabetic(currentChar)) || (Character.isDigit(currentChar) && Character.isAlphabetic(prevChar))) {
                return false;
            }
        }
        //Variable name control

        return true;
    }

    public static String editInput(String input) {
        input = input.replaceAll("\\s", "");
        StringBuilder editedInput = new StringBuilder();

        while (input.length() != 0) {
            if (input.charAt(0) == '+') {
                while (input.length() > 0 && input.startsWith("+")) {
                    editedInput.append(input.charAt(0));
                    input = input.substring(1);
                }
                editedInput.append(" ");

            } else if (input.charAt(0) == '-') {
                while (input.length() > 0 && input.startsWith("-")) {
                    editedInput.append(input.charAt(0));
                    input = input.substring(1);
                }
                editedInput.append(" ");

            } else if (Character.isAlphabetic(input.charAt(0))) {
                while (input.length() > 0 && Character.isAlphabetic(input.charAt(0))) {
                    editedInput.append(input.charAt(0));
                    input = input.substring(1);
                }
                editedInput.append(" ");

            } else if (Character.isDigit(input.charAt(0)) || input.charAt(0) == '.') {
                while (input.length() > 0 && (Character.isDigit(input.charAt(0)) || input.charAt(0) == '.')) {
                    editedInput.append(input.charAt(0));
                    input = input.substring(1);

                }
                editedInput.append(" ");
            } else if (input.charAt(0) == ' ') {
                input = input.substring(1);
            } else {
                editedInput.append(input.charAt(0)).append(" ");
                input = input.substring(1);
            }
        }
        input = editedInput.toString().trim();

        String[] values = input.split("\\s+");
        editedInput = new StringBuilder();

        // Multiple ++ and -- edited
        for (String value : values) {
            if (value.contains("--")) {
                if (value.length() % 2 == 1) {
                    editedInput.append("-").append(" ");
                } else {
                    editedInput.append("+").append(" ");
                }
            } else if (value.contains("++")) {
                editedInput.append("+").append(" ");
            } else {
                editedInput.append(value).append(" ");
            }
        }
        // Multiple ++ and -- edited
        return editedInput.toString().trim();
    }

    public static void commandControl(String input) {
        if ("/help".equals(input)) {
            System.out.println("The smart calculator you can do arithmetic operations ( +, -, *, /) with very large numbers as well as parentheses to change the priority within an expression.");
        } else if (input.startsWith("/")) {
            System.out.println("Unknown command");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("")) {
                System.out.print("");
            } else if (input.startsWith("/")) {
                if ("/exit".equals(input)) {
                    System.out.println("Bye!");
                    System.exit(0);
                } else {
                    commandControl(input);
                }
            } else if (!checkInput(input)) {
                System.out.println("Invalid expression");
            } else if (input.matches("[a-zA-Z]*")) {
                if (variables.containsKey(input)) {
                    System.out.println(variables.get(input));
                } else {
                    System.out.println("Unknown variable");
                }
            } else if (input.contains("=")) {
                input = input.replaceAll("\\s", "");
                String[] equation = new String[2];
                int indexOfEqual = input.indexOf("=");
                equation[0] = input.substring(0, indexOfEqual);
                equation[1] = input.substring(indexOfEqual + 1);
                if (Pattern.compile("[^a-zA-Z]").matcher(equation[0]).find()) {
                    System.out.println("Invalid identifier");
                } else if (Pattern.compile("(.*[^a-zA-Z].*[a-zA-Z].*)|(.*[a-zA-Z].*[^a-zA-Z].*)").matcher(equation[1]).find()) {
                    System.out.println("Invalid assignment");
                } else {
                    boolean isRightSideNumber = equation[1].matches("^[+\\-]?[0-9]*");

                    if (isRightSideNumber) {
                        variables.put(equation[0], new BigDecimal(equation[1]));
                    } else {
                        if (variables.containsKey(equation[1])) {
                            variables.put(equation[0], variables.get(equation[1]));
                        } else {
                            System.out.println("Invalid assignment");
                        }
                    }
                }
            } else if (input.matches("^[\\-+][0-9]*")) {
                System.out.println(input);
            } else {
                input = editInput(input);
                String postfix = infixToPostfix(input);
                System.out.println(calculatePostfix(postfix));
            }
        }
    }
}


