package ReadabilityScore.utill;

public class Calculator {

    private final int totalWordsInText;
    private final int totalSentencesInText;
    private final int totalCharactersInText;
    private final int totalSyllablesInText;
    private final int totalPolysyllablesInText;

    public Calculator(String text) {

        totalWordsInText = text.split(" ").length;
        totalSentencesInText = text.split("[!.?]").length;
        totalCharactersInText = text.replaceAll("[\\s\\n\\t]", "").length();
        int[] syllablesAndPolysyllables = calculateSyllablesAndPolySyllables(text);
        totalSyllablesInText = syllablesAndPolysyllables[0];
        totalPolysyllablesInText = syllablesAndPolysyllables[1];

    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y';
    }

    private int[] calculateSyllablesAndPolySyllables(String text) {

        String[] words = text.toLowerCase().replaceAll("[,.!?]", "").split(" ");
        int totalSyllablesInText = 0;
        int totalPolysyllablesInText = 0;
        for (String word : words) {

            int syllablesInWord = 0;

            for (int i = 0; i < word.length(); i++) {

                char letter = word.charAt(i);

                if (i < word.length() - 1) {
                    char nextLetter = word.charAt(i + 1);
                    if (isVowel(letter) && !isVowel(nextLetter)) {
                        syllablesInWord++;
                        totalSyllablesInText++;
                    }
                } else {
                    if (letter != 'e' && isVowel(letter)) {
                        syllablesInWord++;
                        totalSyllablesInText++;
                    }
                }
            }
            if (syllablesInWord == 0) totalSyllablesInText++;
            else if (syllablesInWord > 2) totalPolysyllablesInText++;
        }

        int[] result = new int[2];
        result[0] = totalSyllablesInText;
        result[1] = totalPolysyllablesInText;
        return result;
    }

    public int getTotalWordsInText() {
        return totalWordsInText;
    }

    public int getTotalSentencesInText() {
        return totalSentencesInText;
    }

    public int getTotalCharactersInText() {
        return totalCharactersInText;
    }

    public int getTotalSyllablesInText() {
        return totalSyllablesInText;
    }

    public int getTotalPolysyllablesInText() {
        return totalPolysyllablesInText;
    }

    public void printValues() {
        System.out.println(
                "Words: " + totalWordsInText + "\n" +
                        "Sentences: " + totalSentencesInText + "\n" +
                        "Characters: " + totalCharactersInText + "\n" +
                        "Syllables: " + totalSyllablesInText + "\n" +
                        "Polysyllables: " + totalPolysyllablesInText);
    }

}


