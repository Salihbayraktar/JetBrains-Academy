package ReadabilityScore.algorithms;

import ReadabilityScore.utill.Calculator;

public class SMOGAlgorithm extends BaseAlgorithm {  //SMOG : Simple Measure of Gobbledygook

    public SMOGAlgorithm(Calculator calculator) {
        super(calculator);
    }

    @Override
    public void calculateScore() {
        score = (1.043 * Math.sqrt(totalPolysyllablesInText *  ((double)30 / totalSentencesInText))) + 3.1291;

    }

    @Override
    public String toString() {
        return String.format("Simple Measure of Gobbledygook: %.2f (about %d-year-olds).", score, lowestReadingAge);
    }
}
