package TicTacToeWithAI.Players;

public abstract class Player {
    int playerId;
    char playerSymbol;

    public Player(int playerId) {
        this.playerId = playerId;
        playerSymbol = playerId == 1 ? 'X' : 'O';
    }

    public abstract void makeMove();
}
