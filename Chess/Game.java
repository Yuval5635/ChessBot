public class Game {

    private static Game instance;

    private Board board;
    private boolean isWhiteTurn;

    public static Game getInstance() {
        if (instance == null) instance = new Game();
        return instance;
    }

    private Game() {
        this.board = Board.getInstance();
        this.isWhiteTurn = true;
    }

    public void resetGame(){
        instance = new Game();
    }

    public int isWin() {
        boolean whiteKingAlive = false;
        boolean blackKingAlive = false;

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
