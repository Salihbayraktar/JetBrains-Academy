package Battleship;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        SaveAndLoadEditor saveAndLoadEditor = new SaveAndLoadEditor();
        Scanner scanner = new Scanner(System.in);

        GameTable player1Table = new GameTable();
        GameTable player2Table = new GameTable();
        boolean load = false;

        if (saveAndLoadEditor.isSaved()) {
            System.out.println("Saved game found do you wanna load ? ( Y - N ) ");
            load = scanner.next().equals("Y");
        }

        if (load) {
            player1Table.setGameTable(saveAndLoadEditor.loadGameTable(1));
            player2Table.setGameTable(saveAndLoadEditor.loadGameTable(2));
        } else {
            System.out.println("Player 1, place your ships on the game field");
            player1Table.setShipsCoordinates();

            System.out.println("Press Enter and pass the move to another player");
            scanner.nextLine();

            System.out.println("Player 2, place your ships to the game field");
            player2Table.setShipsCoordinates();
        }

        while (true) {

            System.out.println("Press Enter and pass the move to another player");
            scanner.nextLine();

            player1Table.printGameTableWithFog();
            System.out.println("Player 1, it's your turn:");
            player2Table.shotTable();
            if (player2Table.isGameEnd(player2Table.getGameTable())) break;

            System.out.println("Press Enter and pass the move to another player");
            scanner.nextLine();

            player2Table.printGameTableWithFog();
            System.out.println("Player 2, it's your turn:");
            player1Table.shotTable();
            if (player1Table.isGameEnd(player1Table.getGameTable())) break;


            saveAndLoadEditor.saveGameTable(player1Table.getGameTable(), 1);
            saveAndLoadEditor.saveGameTable(player2Table.getGameTable(), 2);
        }

        System.out.println("You sank the last ship. You won. Congratulations!");

        saveAndLoadEditor.deleteSaves();

    }

}

