package TicTacToeWithAI.Game;

import TicTacToeWithAI.Game.Enums.States;

public class GameTable {

    private static final char[][] gameTable = new char[3][3];

    static {
        for (int r = 0; r < gameTable.length; r++) {
            for (int c = 0; c < gameTable[0].length; c++) {
                gameTable[r][c] = '_';
            }
        }
    }

    public static char[][] getGameTable() {
        return gameTable;
    }

    public static void addElementToGameTable(int row, int column, char value) {
        gameTable[row][column] = value;
    }

    public static char getValue(int row, int column) {
        return gameTable[row][column];
    }

    @Deprecated
    public static void fillTable() {
        for (int r = 0; r < gameTable.length; r++) {
            for (int c = 0; c < gameTable[0].length; c++) {
                gameTable[r][c] = '_';
            }
        }
    }

    public static void printTable() {
        System.out.println("---------");
        for (int r = 0; r < gameTable.length; r++) {
            System.out.print("| ");
            for (int c = 0; c < gameTable[0].length; c++) {
                if (gameTable[r][c] != '_') {
                    System.out.print(gameTable[r][c] + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }


    public static States getState() {

        int countXRow = 0, countXColumn = 0, countORow = 0, countOColumn = 0;

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {

                if (gameTable[r][c] == 'X') countXRow++;
                if (gameTable[c][r] == 'X') countXColumn++;
                if (gameTable[r][c] == 'O') countORow++;
                if (gameTable[c][r] == 'O') countOColumn++;

                if (countXRow == 3 || countXColumn == 3) return States.X_WINS;
                if (countORow == 3 || countOColumn == 3) return States.O_WINS;

            }
            countXRow = 0;
            countXColumn = 0;
            countORow = 0;
            countOColumn = 0;
        }

        if ((gameTable[0][0] == 'X' && gameTable[1][1] == 'X' && gameTable[2][2] == 'X')
                || (gameTable[0][2] == 'X' && gameTable[1][1] == 'X' && gameTable[2][0] == 'X')) return States.X_WINS;

        if ((gameTable[0][0] == 'O' && gameTable[1][1] == 'O' && gameTable[2][2] == 'O')
                || (gameTable[0][2] == 'O' && gameTable[1][1] == 'O' && gameTable[2][0] == 'O')) return States.O_WINS;

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (gameTable[r][c] == '_') return States.CONTINUE;
            }
        }

        return States.DRAW;
    }


}
