package ReadabilityScore.algorithms;

import ReadabilityScore.utill.Calculator;

public class ARIAlgorithm extends BaseAlgorithm { // ARI : Automated Readability Index

    public ARIAlgorithm(Calculator calculator) {
        super(calculator);
    }

    @Override
    public void calculateScore() {
        score = (4.71 * ((double) totalCharactersInText / totalWordsInText)) + (0.5 * ((double) totalWordsInText / totalSentencesInText)) - 21.43;

    }

    @Override
    public String toString() {
        return String.format("Automated Readability Index: %.2f (about %d-year-olds).", score, lowestReadingAge);
    }
}
