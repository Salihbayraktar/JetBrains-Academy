package Battleship;

import java.io.*;

public class SaveAndLoadEditor {

    public boolean isSaved() {
        return new File("src/Battleship/saves/player1GameTable.txt").isFile();
    }

    public void saveGameTable(char[][] gameTable, int playerId) throws IOException {

        byte[] bytes = new byte[gameTable.length];
        String fileName = String.format("src/Battleship/saves/player%dGameTable.txt", playerId);
        FileOutputStream stream = new FileOutputStream(fileName);
        for (int i = 0; i < gameTable.length; i++) {
            for (int j = 0; j < gameTable[0].length; j++) {
                bytes[j] = (byte) gameTable[i][j];
            }
            stream.write(bytes);
        }
    }

    public char[][] loadGameTable(int playerId) throws IOException {

        char[][] loadedTable = new char[12][12];
        String fileName = String.format("src//Battleship/saves/player%dGameTable.txt", playerId);
        FileInputStream inputStream = new FileInputStream(fileName);
        int condition = inputStream.read();
        int r = 0;
        while (condition != -1) {
            for (int c = 0; c < loadedTable.length; c++) {
                loadedTable[r][c] = (char) condition;
                condition = inputStream.read();
            }
            r++;
        }
        return loadedTable;
    }

    public void deleteSaves() throws FileNotFoundException {

        File player1Save = new File("src//Battleship/saves/player1GameTable.txt");
        File player2Save = new File("src//Battleship/saves/player2GameTable.txt");
        if (player1Save.delete()) System.out.println("player1GameTable.txt deleted");
        if (player2Save.delete()) System.out.println("player2GameTable.txt deleted");

    }

}
