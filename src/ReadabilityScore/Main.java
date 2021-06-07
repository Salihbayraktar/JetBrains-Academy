package ReadabilityScore;

import ReadabilityScore.utill.AlgorithmContext;
import ReadabilityScore.utill.Calculator;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static String getTextFromFile(){
        File file = new File("src/ReadabilityScore/in.txt");
        StringBuilder text = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine());
                if(scanner.hasNextLine()) text.append(" ");
            }
            scanner.close();
            return text.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {

        String text = getTextFromFile();

        Calculator calculator = new Calculator(text);

        calculator.printValues();

        AlgorithmContext context = new AlgorithmContext(calculator);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");

        String type = scanner.nextLine();

        context.executeAlgorithm(type);

    }
}