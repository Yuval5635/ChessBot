package chess;

public class Game {

    private boolean isWhiteTurn;
    private Board board;

    public Game(){
        this.isWhiteTurn = true;
        this.board = new Board();
    }

    public Game(Game other){
        this.isWhiteTurn = other.isWhiteTurn;
        this.board = new Board(other.board);
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

    private boolean move(Move move){
        if (this.board.movePiece(move)){
            this.isWhiteTurn = !this.isWhiteTurn;
            return true;
        }
        return false;
    }

    public boolean turn(Move move){
        if (this.board.isOccupy(move.fromSquare()) && this.board.getColor(move.fromSquare()) == (this.isWhiteTurn ? 1 : -1)){
            if (move(move)) {
                return isWin() != 0;
            }
        }
        return false;
    }

    public boolean isWhiteTurn() {
        return this.isWhiteTurn;
    }

    public int getNumOfMoves(){
        return this.board.getAllMoves(this.isWhiteTurn ? 1 : -1).length;
    }

    public Move[] getAllMoves(){
        return this.board.getAllMoves(this.isWhiteTurn ? 1 : -1);
    }

    public Board getBoard() {
        return this.board;
    }
}
