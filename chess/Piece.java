package chess;

public abstract class Piece {

    protected int color;
    protected int square;
    protected Board board;
    protected char name;

    protected Piece(int color, int square, Board board, char name) {
        this.color = color;
        this.square = square;
        this.board = board;
        this.name = name;
    }

    public int getSquare(){
        return this.square;
    }

    public int getColor(){
        return this.color;
    }

    public char getName(){
        return this.name;
    }

    public void moveTo(int square){
        this.square = square;
    }

    protected boolean isValidMove(int rowOffset, int colOffset) {
        int row = (this.square / 8) + rowOffset;
        int col = (this.square % 8) + colOffset;
        return row < 8 && row >= 0 && col < 8 && col >= 0 && ((! this.board.isOccupy(row * 8 + col)) || this.board.getColor(row * 8 + col) != this.color);
    }

    public abstract Move[] getMoves();

    public abstract Piece copy(Board newBoard);
}
