package TicTacToeWithAI.Players;

import TicTacToeWithAI.Game.GameTable;
import TicTacToeWithAI.Game.GameUtil;

import java.util.Scanner;

public class User extends Player{

    public User(int playerId) {
        super(playerId);
    }

    @Override
    public void makeMove() {

        boolean wrongMove = true;
        while (wrongMove) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the coordinates:");
            String coordinates = scanner.nextLine();
            if (GameUtil.controlCoordinates(coordinates)) {
                int row = Integer.parseInt(coordinates.substring(0, 1)) - 1;
                int column = Integer.parseInt(coordinates.substring(2, 3)) - 1;
                GameTable.addElementToGameTable(row,column,playerSymbol);
                wrongMove = false;
            }
        }
    }

}

