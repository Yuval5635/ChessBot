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

    private boolean move(int fromSquare, int toSquare){
        if (Board.getInstance().movePiece(fromSquare, toSquare)){
            this.isWhiteTurn = !this.isWhiteTurn;
            return true;
        }
        return false;
    }

    public boolean turn(int fromSquare, int toSquare){
        if (Board.getInstance().isOccupy(fromSquare) && Board.getInstance().getColor(fromSquare) == (this.isWhiteTurn ? 1 : -1)){
            if (move(fromSquare, toSquare)) {
                return isWin() != 0;
            }
        }
        return false;
    }

    public boolean isWhiteTurn() {
        return this.isWhiteTurn;
    }

    public int getNumOfMoves(int color){
        return Board.getInstance().getAllMoves(color).length;
    }

    public int[] getAllMoves(int color){
        return Board.getInstance().getAllMoves(color);
    }
}
