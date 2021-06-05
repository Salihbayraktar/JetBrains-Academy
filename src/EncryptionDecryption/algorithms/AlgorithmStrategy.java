package EncryptionDecryption.algorithms;

public class AlgorithmStrategy {

    public static MainEncryption selectAlgorithm(String[] args) {

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("-alg")) {

                if (args[i + 1].equalsIgnoreCase("unicode")) {

                    return new UnicodeAlgorithm(args);

                } else {

                    return new ShiftAlgorithm(args);

                }
            }
        }
        return new ShiftAlgorithm(args);
    }
}

