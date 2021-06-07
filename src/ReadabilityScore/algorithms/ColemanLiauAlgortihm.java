package ReadabilityScore.algorithms;

import ReadabilityScore.utill.Calculator;

public class ColemanLiauAlgortihm extends BaseAlgorithm {

    public ColemanLiauAlgortihm(Calculator calculator) {
        super(calculator);
    }

    @Override
    public void calculateScore() {
        score = (0.0588 * ((double) totalCharactersInText / totalWordsInText) * 100) - (0.296 * ((double) totalSentencesInText / totalWordsInText) * 100) - 15.8;

    }

    @Override
    public String toString() {
        return String.format("Coleman–Liau index: %.2f (about %d-year-olds).", score, lowestReadingAge);
    }

}
