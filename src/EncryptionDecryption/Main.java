package EncryptionDecryption;

import EncryptionDecryption.algorithms.AlgorithmStrategy;
import EncryptionDecryption.algorithms.MainEncryption;

public class Main {

    public static void main(String[] args) {

        MainEncryption algorithm = AlgorithmStrategy.selectAlgorithm(args);

        algorithm.encryption();
    }
}
