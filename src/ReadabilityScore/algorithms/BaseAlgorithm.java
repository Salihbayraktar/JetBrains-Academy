package ReadabilityScore.algorithms;

import ReadabilityScore.utill.Calculator;

public abstract class BaseAlgorithm {
    protected double score;
    protected int lowestReadingAge;

    protected final int totalCharactersInText;
    protected final int totalWordsInText;
    protected final int totalSentencesInText;
    protected final int totalSyllablesInText;
    protected final int totalPolysyllablesInText;

    public BaseAlgorithm(Calculator calculator) {
        totalCharactersInText = calculator.getTotalCharactersInText();
        totalWordsInText = calculator.getTotalWordsInText();
        totalSentencesInText = calculator.getTotalSentencesInText();
        totalSyllablesInText = calculator.getTotalSyllablesInText();
        totalPolysyllablesInText = calculator.getTotalPolysyllablesInText();
    }

    public abstract void calculateScore();

    public void calculateLowestReadingAge() {

        double spareScore = score;
        double remainder = spareScore % 1;

        if (remainder > 0.5) {
            spareScore = spareScore + (1 - spareScore % 1);
        }
        else {
            spareScore = spareScore - (spareScore % 1);
        }

        if (spareScore == 1) lowestReadingAge = 6;
        else if (spareScore == 2) lowestReadingAge = 7;
        else if (spareScore >= 3 && spareScore <= 12) lowestReadingAge = (int)spareScore+6;
        else if (spareScore == 13) lowestReadingAge = 24;
        else lowestReadingAge = 25;
    }

    public int getLowestReadingAge() {
        return lowestReadingAge;
    }
}
