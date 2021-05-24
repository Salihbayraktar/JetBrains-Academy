package BullsCows;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BullsCows {

    public static void checkErrors(String secretCodeLengthStr, String possibleSymbolsStr) {
        if (Pattern.compile("\\D").matcher(secretCodeLengthStr).find()) {
            System.out.println("error");
            System.out.println("Invalid character inside the secretCodeLengthStr");
            System.exit(-1);
        }

        if (Pattern.compile("\\D").matcher(possibleSymbolsStr).find()) {
            System.out.println("error");
            System.out.println("Invalid character inside the possibleSymbolsStr");
            System.exit(-1);
        }

        if (Integer.parseInt(secretCodeLengthStr) == 0) {
            System.out.println("error");
            System.out.println("secretCodeLengthStr can't be zero");
            System.exit(-1);
        }

        if (Integer.parseInt(possibleSymbolsStr) > 36) {
            System.out.println("error");
            System.out.println("possibleSymbolsStr over flow max number is 36 Your number is : " + possibleSymbolsStr);
            System.exit(-1);
        }

        if (Integer.parseInt(secretCodeLengthStr) > Integer.parseInt(possibleSymbolsStr)) {
            System.out.println("error");
            System.out.println("secretCodeLengthStr can't be bigger than possibleSymbolsStr ");
            System.exit(-1);
        }
    }

    public static String generateSecretCode() {

        String syntax = "0123456789abcdefghijklmnopqrstuvwxyz";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input the length of the secret code:");
        String secretCodeLengthStr = scanner.next();

        System.out.println("Input the number of possible symbols in the code:");
        String possibleSymbolsStr = scanner.next();

        checkErrors(secretCodeLengthStr, possibleSymbolsStr);

        int secretCodeLength = Integer.parseInt(secretCodeLengthStr);
        int possibleSymbols = Integer.parseInt(possibleSymbolsStr);

        StringBuilder secretCode = new StringBuilder();
        while (secretCode.length() < secretCodeLength) {
            int random = new Random().nextInt(possibleSymbols);
            if (!secretCode.toString().contains(String.valueOf(syntax.charAt(random))))
                secretCode.append(syntax.charAt(random));
        }
        String stars = secretCode.toString().replaceAll("[(a-z)(0-9)]", "*");
        String digitStr = possibleSymbols > 9 ? "0-9" : "0-" + possibleSymbols;
        String characterStr = possibleSymbols > 10 ? "a-" + syntax.charAt(possibleSymbols - 1) : "";

        System.out.printf("The secret is prepared: %s (%s, %s).", stars, digitStr, characterStr);

        return secretCode.toString();
    }

    public static void checkAnswer(String secretCode) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int cow = 0;
            int bull = 0;
            System.out.print("> ");
            String guess = scanner.next();

            char[] secretCodeArr = secretCode.toCharArray();

            char[] guessArr = guess.toCharArray();

            for (int i = 0; i < guessArr.length; i++) {
                if (guessArr[i] == secretCodeArr[i]) {
                    bull++;
                    continue;
                }

                for (int j = 0; j < guessArr.length; j++) {
                    if (guessArr[i] == secretCodeArr[j]) {
                        cow++;
                        break;
                    }
                }
            }
            if (cow != 0 && bull != 0)
                System.out.printf("Grade: %d bull(s) and %d cow(s). The secret code is %s.", bull, cow, secretCode);
            else if (cow != 0) System.out.printf("Grade : %d cow(s). The secret code is %s.", cow, secretCode);
            else if (bull != 0) System.out.printf("Grade : %d bull(s). The secret code is %s.", bull, secretCode);
            else System.out.printf("None. The secret code is %s", secretCode);
            if (bull == secretCodeArr.length) {
                System.out.print("Congratulations! You guessed the secret code.");
                break;
            }
        }
    }

    public static void main(String[] args) {

        checkAnswer(generateSecretCode());

    }
}
