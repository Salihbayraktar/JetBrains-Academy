package EncryptionDecryption.algorithms;

public class AlgorithmContext {

    MainStrategy selectedStrategy;

    public AlgorithmContext(String[] args) {

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("-alg")) {

                if (args[i + 1].equalsIgnoreCase("unicode")) {

                    this.selectedStrategy = new UnicodeAlgorithm(args);

                } else {

                    this.selectedStrategy = new ShiftAlgorithm(args);

                }
                return;
            }
        }
        this.selectedStrategy = new ShiftAlgorithm(args);
    }

    public void encryption() {
        selectedStrategy.encryption();
    }

}

