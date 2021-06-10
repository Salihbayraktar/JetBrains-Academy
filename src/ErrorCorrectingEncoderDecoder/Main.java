package ErrorCorrectingEncoderDecoder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static byte[] getBitsFromFile(String fileName) {

        byte[] allBytesFromFile = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            allBytesFromFile = fileInputStream.readAllBytes();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert allBytesFromFile != null;
        return convertBytesToBits(allBytesFromFile);
    }

    public static void writeBytesToFile(String fileName, byte[] bytes) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static byte[] convertBytesToBits(byte[] bytes) {

        byte[] allBits = new byte[bytes.length * 8];
        for (int i = 0; i < bytes.length; i++) {
            int currentByte = bytes[i];
            if (currentByte < 0) currentByte = 256 + currentByte;
            for (int j = 7; j >= 0; j--) {
                allBits[(i * 8) + j] = (byte) (currentByte % 2 == 0 ? 0 : 1);
                currentByte = (byte) (currentByte / 2);
            }
        }
        return allBits;
    }

    public static byte[] convertBitsToBytes(byte[] bits) {
        byte[] bytes = new byte[bits.length / 8];
        for (int j = 0; j < bytes.length; j++) {

            int value = 0;
            for (int i = 0; i < 8; i++) {

                value += bits[(j * 8) + i] == 1 ? Math.pow(2, 7 - (i)) : 0;

            }
            bytes[j] = (byte) value;

        }

        return bytes;
    }



    public static byte[] simulateSending(byte[] bytes) {
        Random random = new Random();

        for (int i = 0; i < bytes.length / 8; i++) {                //Simulating transfer loss
            int randomIndex = random.nextInt(6) + 1;
            bytes[i * 8 + randomIndex] = (byte) (bytes[i * 8 + randomIndex] == 1 ? 0 : 1);
        }

        return bytes;
    }

    public static byte[] encodeWithHamming(byte[] rawBits) {
        int bitsLength = (rawBits.length % 4 == 0) ? (rawBits.length) : ((rawBits.length) + (4 - (rawBits.length % 4)));
        boolean[] bits = new boolean[bitsLength];
        for (int i = 0; i < rawBits.length; i++) {
            bits[i] = rawBits[i] == 1;
        }

        boolean[] bitsWithParity = new boolean[bits.length * 2];      // P1 P2 D1 P3 D2 D3 D4 E  (P is parity, D is data and E is empty)
        for (int i = 0, bitsIndex = 0; i < bitsWithParity.length; i += 8) {

            bitsWithParity[i + 2] = bits[bitsIndex++];           //D1 location
            bitsWithParity[i + 4] = bits[bitsIndex++];           //D2 location
            bitsWithParity[i + 5] = bits[bitsIndex++];           //D3 location
            bitsWithParity[i + 6] = bits[bitsIndex++];           //D4 location
            bitsWithParity[i + 7] = false;                       //Empty bit

            // 0 1 and 3 are parity locations

            bitsWithParity[i] = bitsWithParity[i + 2] ^ bitsWithParity[i + 4] ^ bitsWithParity[i + 6];
            // P1 = D1 ^ D2 ^ D4

            bitsWithParity[i + 1] = bitsWithParity[i + 2] ^ bitsWithParity[i + 5] ^ bitsWithParity[i + 6];
            // P2 = D1 ^ D3 ^ D4

            bitsWithParity[i + 3] = bitsWithParity[i + 4] ^ bitsWithParity[i + 5] ^ bitsWithParity[i + 6];
            // P3 = D2 ^ D3 ^ D4
        }

        byte[] hammingBits = new byte[bitsWithParity.length];
        for (int i = 0; i < hammingBits.length; i++) {
            hammingBits[i] = (byte) (bitsWithParity[i] ? 1 : 0);
        }

        return hammingBits;
    }

    public static byte[] decodeHammingBits(byte[] encodedBits) {
        boolean[] bits = new boolean[encodedBits.length];
        byte[] decodedBits = new byte[bits.length / 2];
        for (int i = 0; i < encodedBits.length; i++) {
            bits[i] = encodedBits[i] == 1;
        }
        int p1Location = 1, p2Location = 2, p3Location = 4;

        int corruptIndex;

        boolean checkP1, checkP2, checkP3;

        for (int i = 0, encodedBitsIndex = 0; i < bits.length; i += 8) {

            checkP1 = bits[i] == (bits[i + 2] ^ bits[i + 4] ^ bits[i + 6]);

            checkP2 = bits[i + 1] == (bits[i + 2] ^ bits[i + 5] ^ bits[i + 6]);

            checkP3 = bits[i + 3] == (bits[i + 4] ^ bits[i + 5] ^ bits[i + 6]);

            corruptIndex = (checkP1 ? 0 : p1Location) + (checkP2 ? 0 : p2Location) + (checkP3 ? 0 : p3Location);

            corruptIndex--;        // For java's first array index rule (Starts with 0 instead of 1)

            bits[i + corruptIndex] = !bits[i + corruptIndex];

            decodedBits[encodedBitsIndex++] = (byte) (bits[i + 2] ? 1 : 0);
            decodedBits[encodedBitsIndex++] = (byte) (bits[i + 4] ? 1 : 0);
            decodedBits[encodedBitsIndex++] = (byte) (bits[i + 5] ? 1 : 0);
            decodedBits[encodedBitsIndex++] = (byte) (bits[i + 6] ? 1 : 0);

        }

        return decodedBits;
    }


    @Deprecated                                      // Stage 4 encode function
    public static byte[] encode(byte[] rawBits) {
        int filledByteLength;
        if (rawBits.length % 3 == 0) {
            filledByteLength = rawBits.length;
        } else {
            filledByteLength = rawBits.length + (3 - (rawBits.length % 3));
        }
        boolean[] filledBits = new boolean[filledByteLength];
        for (int i = 0; i < rawBits.length; i++) {
            if (rawBits[i] == 1) filledBits[i] = true;
        }

        byte[] encodedBytes = new byte[(filledByteLength / 3) * 8];

        for (int i = 0, j = 0; i < filledBits.length; i += 3) {

            byte value = (byte) (filledBits[i] ? 1 : 0);
            encodedBytes[j++] = value;
            encodedBytes[j++] = value;

            value = (byte) (filledBits[i + 1] ? 1 : 0);
            encodedBytes[j++] = value;
            encodedBytes[j++] = value;

            value = (byte) (filledBits[i + 2] ? 1 : 0);
            encodedBytes[j++] = value;
            encodedBytes[j++] = value;

            value = (byte) ((filledBits[i] ^ filledBits[i + 1] ^ filledBits[i + 2]) ? 1 : 0);
            encodedBytes[j++] = value;
            encodedBytes[j++] = value;

        }

        return encodedBytes;
    }

    @Deprecated                                       // Stage 4 decode function
    public static byte[] decode(byte[] encodedBits) {
        boolean[] bytes = new boolean[encodedBits.length];
        byte[] decodedBits = new byte[encodedBits.length / 8 * 3];

        for (int i = 0; i < encodedBits.length; i++) {
            bytes[i] = encodedBits[i] == 1;
        }

        for (int i = 0, decodeIndex = 0; i < bytes.length; i += 8) {

            if (bytes[i] ^ bytes[i + 2] ^ bytes[i + 4] == bytes[i + 6]) {
                decodedBits[decodeIndex++] = (byte) (bytes[i] ? 1 : 0);
                decodedBits[decodeIndex++] = (byte) (bytes[i + 2] ? 1 : 0);
                decodedBits[decodeIndex++] = (byte) (bytes[i + 4] ? 1 : 0);
            } else {
                decodedBits[decodeIndex++] = (byte) (bytes[i + 1] ? 1 : 0);
                decodedBits[decodeIndex++] = (byte) (bytes[i + 3] ? 1 : 0);
                decodedBits[decodeIndex++] = (byte) (bytes[i + 5] ? 1 : 0);
            }

        }

        byte[] trimDecodedBits = new byte[(decodedBits.length / 8) * 8];
        for (int i = 0; i < trimDecodedBits.length; i++) {
            trimDecodedBits[i] = decodedBits[i];
        }

        return trimDecodedBits;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write a mode:");
        String mode = scanner.nextLine();

        switch (mode) {
            case "encode" -> {
                byte[] rawBits = getBitsFromFile("src/ErrorCorrectingEncoderDecoder/send.txt");
                byte[] encodedBits = encodeWithHamming(rawBits);
                byte[] encodedBytes = convertBitsToBytes(encodedBits);
                writeBytesToFile("src/ErrorCorrectingEncoderDecoder/encoded.txt", encodedBytes);
            }
            case "send" -> {
                byte[] rawEncodedBits = getBitsFromFile("src/ErrorCorrectingEncoderDecoder/encoded.txt");
                byte[] corruptEncodedBits = simulateSending(rawEncodedBits);
                byte[] corruptEncodedBytes = convertBitsToBytes(corruptEncodedBits);
                writeBytesToFile("src/ErrorCorrectingEncoderDecoder/received.txt", corruptEncodedBytes);
            }
            case "decode" -> {
                byte[] corruptEncodeBits = getBitsFromFile("src/ErrorCorrectingEncoderDecoder/received.txt");
                byte[] decodedBits = decodeHammingBits(corruptEncodeBits);
                byte[] decodedBytes = convertBitsToBytes(decodedBits);
                writeBytesToFile("src/ErrorCorrectingEncoderDecoder/decoded.txt", decodedBytes);
            }
            default -> System.out.println("Invalid Mode");
        }

    }
}
