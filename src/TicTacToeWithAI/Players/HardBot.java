package TicTacToeWithAI.Players;

import TicTacToeWithAI.Game.Enums.States;
import TicTacToeWithAI.Game.GameTable;

public class HardBot extends Player {

    char enemySymbol = playerSymbol == 'X' ? 'O' : 'X';


    public HardBot(int playerId) {
        super(playerId);
    }

    @Override
    public void makeMove() {
        System.out.println("Making move level \"hard\"");

        bestMove();

    }

    public void bestMove() {
        int[] bestMove = new int[2];
        int bestScore = -10;
        for (int r = 0; r < 3; r++) {

            for (int c = 0; c < 3; c++) {

                if (GameTable.getValue(r, c) == '_') {
                    GameTable.addElementToGameTable(r, c, playerSymbol);
                    int score = minimax(false);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = r;
                        bestMove[1] = c;
                    }
                    GameTable.addElementToGameTable(r, c, '_');
                }

            }

        }
        GameTable.addElementToGameTable(bestMove[0], bestMove[1], playerSymbol);
    }

    public int minimax(boolean isMaximizing) {
        States playerState = playerSymbol == 'X' ? States.X_WINS : States.O_WINS;
        States gameState = GameTable.getState();
        int bestScore;

        if (gameState != States.CONTINUE) {
            if (gameState == States.DRAW) return 0;
            else if (gameState == playerState) return 1;
            else return -1;
        }

        if (isMaximizing) {
            bestScore = -10;
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (GameTable.getValue(r, c) == '_') {
                        GameTable.addElementToGameTable(r, c, playerSymbol);
                        int score = minimax(false);
                        GameTable.addElementToGameTable(r, c, '_');
                        if (score > bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
        } else {
            bestScore = 10;
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (GameTable.getValue(r, c) == '_') {
                        GameTable.addElementToGameTable(r, c, enemySymbol);
                        int score = minimax(true);
                        GameTable.addElementToGameTable(r, c, '_');
                        if (score < bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
        }
        return bestScore;
    }
}
