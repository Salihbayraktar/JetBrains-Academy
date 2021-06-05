package EncryptionDecryption.algorithms;


public class UnicodeAlgorithm extends MainEncryption {

    public UnicodeAlgorithm(String[] args) {
        super(args);
    }

    @Override
    public void encryption() {
        StringBuilder processData = new StringBuilder();
        char[] letters = data.toCharArray();

        for (char letter : letters) {
            if (mode.equals("enc")) {
                processData.append((char) (letter + key));
            } else {
                processData.append((char) (letter - key));
            }
        }
        result = processData.toString();
        printOrWriteResult(result, outputFileName);
    }
}
