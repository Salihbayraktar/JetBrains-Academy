package ReadabilityScore.utill;

import ReadabilityScore.algorithms.*;

public class AlgorithmContext {

    Calculator calculator;

    public AlgorithmContext(Calculator calculator) {
        this.calculator = calculator;
    }

    public void executeAlgorithm(String type) {
        type = type.toUpperCase();
        BaseAlgorithm algorithm;
        switch (type) {
            case "ARI" -> {
                algorithm = new ARIAlgorithm(calculator);
                algorithm.calculateScore();
                algorithm.calculateLowestReadingAge();
                System.out.println(algorithm);
            }
            case "FK" -> {
                algorithm = new FleschKincaidAlgorithm(calculator);
                algorithm.calculateScore();
                algorithm.calculateLowestReadingAge();
                System.out.println(algorithm);
            }
            case "SMOG" -> {
                algorithm = new SMOGAlgorithm(calculator);
                algorithm.calculateScore();
                algorithm.calculateLowestReadingAge();
                System.out.println(algorithm);
            }
            case "CL" -> {
                algorithm = new ColemanLiauAlgortihm(calculator);
                algorithm.calculateScore();
                algorithm.calculateLowestReadingAge();
                System.out.println(algorithm);
            }
            case "ALL" -> {
                double averageAge = 0;
                algorithm = new ARIAlgorithm(calculator);
                algorithm.calculateScore();
                algorithm.calculateLowestReadingAge();
                System.out.println(algorithm);

                averageAge += algorithm.getLowestReadingAge();
                algorithm = new FleschKincaidAlgorithm(calculator);
                algorithm.calculateScore();
                algorithm.calculateLowestReadingAge();
                System.out.println(algorithm);

                averageAge += algorithm.getLowestReadingAge();
                algorithm = new SMOGAlgorithm(calculator);
                algorithm.calculateScore();
                algorithm.calculateLowestReadingAge();
                System.out.println(algorithm);

                averageAge += algorithm.getLowestReadingAge();
                algorithm = new ColemanLiauAlgortihm(calculator);
                algorithm.calculateScore();
                algorithm.calculateLowestReadingAge();
                System.out.println(algorithm);

                averageAge += algorithm.getLowestReadingAge();
                averageAge = averageAge / 4;
                System.out.println();
                System.out.printf("This text should be understood in average by %.2f-year-olds.", averageAge);
            }
        }
    }
}
