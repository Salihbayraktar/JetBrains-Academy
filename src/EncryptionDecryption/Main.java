package EncryptionDecryption;


import EncryptionDecryption.algorithms.AlgorithmContext;

public class Main {

    public static void main(String[] args) {

        AlgorithmContext algorithmContext = new AlgorithmContext(args);

        algorithmContext.encryption();

    }
}
