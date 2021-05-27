package Battleship;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GameTable player1Table = new GameTable();
        System.out.println("Player 1, place your ships on the game field");
        player1Table.setShipsCoordinates();

        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();

        GameTable player2Table = new GameTable();
        System.out.println("Player 2, place your ships to the game field");
        player2Table.setShipsCoordinates();

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

        }
        System.out.println("You sank the last ship. You won. Congratulations!");

    }

}

