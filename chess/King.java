package chess;

public class King extends Piece{
    
    public King(int color, int square, Board board) {
        super(color, square, board);
    }
    
    @Override
    public Move[] getMoves() {
        boolean[] isMoves = new boolean[64];

        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if (isValidMove(i, j)){
                    isMoves[this.square + (i * 8) + j] = true;
                }
            }
        }

        int numOfValidMoves = 0;
        for (boolean isMove : isMoves) {
            if (isMove) numOfValidMoves++;
        }

        Move[] validMoves = new Move[numOfValidMoves];

        int indexer = 0;
        for (int i = 0; i < 64; i++){
            if(isMoves[i]){
                validMoves[indexer] = new Move(this.square, i);
                indexer++;
            }
        }

        return validMoves;
    }

    @Override
    public Piece copy(Board newBoard) {
        return new King(this.color, this.square, newBoard);
    }
}