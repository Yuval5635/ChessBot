public class Game {

    private static Game instance;

    private boolean isWhiteTurn;

    public static Game getInstance() {
        if (instance == null) instance = new Game();
        return instance;
    }

    private Game() {
        this.isWhiteTurn = true;
    }

    public void resetGame(){
        instance = new Game();
        Board.getInstance().resetBoard();
    }

    public int isWin() {
        boolean whiteKingAlive = false;
        boolean blackKingAlive = false;

        Board board = Board.getInstance();
        
        for (int i = 0; i < 64; i++){
            if (board.isOccupy(i) && board.getSquare(i) instanceof King){
                if (board.getColor(i) == 1) whiteKingAlive = true;
                else blackKingAlive = true;
            }
        }

        if (!whiteKingAlive) return -1;
        else if (!blackKingAlive) return 1;
        else return 0;
    }

}
