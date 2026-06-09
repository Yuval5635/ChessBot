package chess;

public class Game {

    private boolean isWhiteTurn;
    private Board board;

    public Game(){
        this.isWhiteTurn = true;
        this.board = new Board();
    }

    public void resetGame(){
        this.board.resetBoard();
        this.isWhiteTurn = true;
    }

    public int isWin() {
        boolean whiteKingAlive = false;
        boolean blackKingAlive = false;

        for (int i = 0; i < 64; i++){
            if (this.board.isOccupy(i) && this.board.getSquare(i) instanceof King){
                if (this.board.getColor(i) == 1) whiteKingAlive = true;
                else blackKingAlive = true;
            }
        }

        if (!whiteKingAlive) return -1;
        else if (!blackKingAlive) return 1;
        else return 0;
    }

    private boolean move(int fromSquare, int toSquare){
        if (this.board.movePiece(fromSquare, toSquare)){
            this.isWhiteTurn = !this.isWhiteTurn;
            return true;
        }
        return false;
    }

    public boolean turn(int fromSquare, int toSquare){
        if (this.board.isOccupy(fromSquare) && this.board.getColor(fromSquare) == (this.isWhiteTurn ? 1 : -1)){
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
        return this.board.getAllMoves(color).length;
    }

    public int[] getAllMoves(int color){
        return this.board.getAllMoves(color);
    }
}
