public abstract class Piece {

    protected int color;
    protected int square;

    protected Piece(int color, int square) {
        this.color = color;
        this.square = square;
    }

    public int getSquare(){
        return this.square;
    }

    public int getColor(){
        return this.color;
    }

    public void moveTo(int square){
        this.square = square;
    }

    protected boolean isValidMove(int rowOffset, int colOffset) {
        int row = (this.square / 8) + rowOffset;
        int col = (this.square % 8) + colOffset;
        return row < 8 && row >= 0 && col < 8 && col >= 0 && ((! Board.getInstance().isOccupy(row * 8 + col)) || Board.getInstance().getColor(row * 8 + col) != this.color);
    }

    public abstract int[] getMoves();
}
