package TicTacToeWithAI.Players;

import TicTacToeWithAI.Game.GameTable;
import TicTacToeWithAI.Game.GameUtil;

import java.util.Random;

public class EasyBot extends Player {

    public EasyBot(int playerId) {
        super(playerId);
    }

    @Override
    public void makeMove() {
        System.out.println("Making move level \"easy\"");

        randomMove();
    }


    public void randomMove() {
        boolean wrongMove = true;
        while (wrongMove) {
            Random random = new Random();
            int row = random.nextInt(3) + 1;             // Added +1 for control like a user input
            int column = random.nextInt(3) + 1;          // Added +1 for control like a user input
            String coordinates = String.valueOf(row) + column;
            if (GameUtil.controlCoordinates(coordinates)) {
                row--;                                          // Subtract -1 for convert to true value
                column--;                                       // Subtract -1 for convert to true value
                GameTable.addElementToGameTable(row, column, playerSymbol);
                wrongMove = false;
            }
        }
    }

}

