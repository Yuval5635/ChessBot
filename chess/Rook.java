package chess;

public class Rook extends Piece{

    public Rook(int color, int square, Board board) {
        super(color, square, board);
    }

    @Override
    public Move[] getMoves(){
        boolean[] isMoves = new boolean[64];

        for (int i = -1; i < 2; i += 2){
            for (int j = i; true; j += i){
                if (isValidMove(j, 0)){
                    isMoves[this.square + (j * 8)] = true;
                } else{
                    break;
                }
            }
            for (int j = i; true; j += i){
                if (isValidMove(0, j)){
                    isMoves[this.square + j] = true;
                } else{
                    break;
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
        return new Rook(this.color, this.square, newBoard);
    }
}