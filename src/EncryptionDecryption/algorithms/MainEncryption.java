package EncryptionDecryption.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class MainEncryption {

    protected String mode = "enc";

    protected int key = 0;

    protected String data = "";

    protected String outputFileName = "";

    protected String inputFileName = "";

    protected String result = "";

    public MainEncryption(String[] args) {

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode" -> mode = args[i + 1];
                case "-key" -> key = Integer.parseInt(args[i + 1]);
                case "-data" -> data = args[i + 1];
                case "-out" -> outputFileName = args[i + 1];
                case "-in" -> inputFileName = args[i + 1];
            }
        }

        if (data.equals("") && !inputFileName.equals("")) {
            data = getDataFromFile(inputFileName);
        }
    }

    public abstract void encryption();

    protected void printOrWriteResult(String result, String outputFileName) {
        if (outputFileName.equals("")) {
            System.out.println(result);
        } else {
            writeResultToOutputLocation(result, outputFileName);
        }
    }

    protected void writeResultToOutputLocation(String result, String outputFileName) {
        File file = new File(outputFileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(result);
        } catch (IOException e) {
            System.out.println("Error " + e);
            System.exit(-1);
        }
    }

    protected String getDataFromFile(String inputFileName) {

        try {
            File file = new File(inputFileName);
            Scanner scanner = new Scanner(file);
            String result = scanner.nextLine();
            scanner.close();
            return result;
        } catch (FileNotFoundException e) {
            System.out.println("Error " + e);
            System.exit(-1);
        }

        return "";
    }

}
