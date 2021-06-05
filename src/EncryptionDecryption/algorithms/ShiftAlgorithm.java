package EncryptionDecryption.algorithms;

public class ShiftAlgorithm extends MainEncryption {

    public ShiftAlgorithm(String[] args) {
        super(args);
    }

    @Override
    public void encryption() {

        //26 is the number of letters in the alphabet

        key = key % 26;

        if (mode.equals("dec")) {
            key = 26 - key;
        }

        StringBuilder processData = new StringBuilder();

        for (int i = 0; i < data.length(); i++) {
            char letter = data.charAt(i);
            char encDecLetter;
            if (Character.isUpperCase(letter)) {

                if (letter + key > 'Z') {
                    encDecLetter = (char) (letter + key - 26);
                } else {
                    encDecLetter = (char) (letter + key);
                }

            } else if (Character.isLowerCase(letter)) {

                if (letter + key > 'z') {
                    encDecLetter = (char) (letter + key - 26);
                } else {
                    encDecLetter = (char) (letter + key);
                }
            } else {
                encDecLetter = letter;
            }
            processData.append(encDecLetter);
        }
        result = processData.toString();
        printOrWriteResult(result, outputFileName);
    }
}
