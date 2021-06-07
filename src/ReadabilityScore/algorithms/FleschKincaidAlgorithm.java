package ReadabilityScore.algorithms;

import ReadabilityScore.utill.Calculator;

public class FleschKincaidAlgorithm extends BaseAlgorithm {

    public FleschKincaidAlgorithm(Calculator calculator) {
        super(calculator);
    }

    @Override
    public void calculateScore() {
        score = (0.39 *  ((double)totalWordsInText / totalSentencesInText)) + (11.8 *  ((double)totalSyllablesInText / totalWordsInText)) - 15.59;

    }

    @Override
    public String toString() {
        return String.format("Flesch–Kincaid readability tests: %.2f (about %d-year-olds).", score, lowestReadingAge);

    }
}
