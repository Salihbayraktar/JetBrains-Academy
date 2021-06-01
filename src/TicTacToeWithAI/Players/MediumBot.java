package TicTacToeWithAI.Players;

import TicTacToeWithAI.Game.Enums.States;
import TicTacToeWithAI.Game.GameTable;

public class MediumBot extends EasyBot {

    char enemySymbol = playerSymbol == 'X' ? 'O' : 'X';

    public MediumBot(int playerId) {
        super(playerId);
    }

    @Override
    public void makeMove() {

        System.out.println("Making move level \"medium\"");


        int[] rowAndColumn = getWinningMove(playerSymbol);
        if(rowAndColumn[0] != -1 && rowAndColumn[1] != -1){
            GameTable.addElementToGameTable(rowAndColumn[0],rowAndColumn[1],playerSymbol);
            return;
        }
        rowAndColumn = getWinningMove(enemySymbol);
        if(rowAndColumn[0] != -1 && rowAndColumn[1] != -1){
            GameTable.addElementToGameTable(rowAndColumn[0],rowAndColumn[1],playerSymbol);
            return;
        }

        randomMove();

    }


    public int[] getWinningMove(char symbol){

        for(int r = 0 ; r < 3 ; r++){
            for(int c = 0 ; c < 3 ; c++) {
                if( GameTable.getValue(r,c) == '_' ) {
                    GameTable.addElementToGameTable(r,c,symbol);
                    if(GameTable.getState() == States.O_WINS || GameTable.getState() == States.X_WINS ) {
                        GameTable.addElementToGameTable(r,c,'_');
                        return new int[]{r, c};
                    }
                    GameTable.addElementToGameTable(r,c,'_');
                }
            }
        }
       return new int[]{-1,-1};
    }

}
