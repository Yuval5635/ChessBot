public class Piece {

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

}
