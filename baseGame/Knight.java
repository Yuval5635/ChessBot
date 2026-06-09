package chess;

public class Knight extends Piece{

    public Knight(int color, int square, Board board) {
        super(color, square, board);
    }

    @Override
    public int[] getMoves() {
        boolean[] isMoves = new boolean[64];

        for (int i = -2; i <= 2; i++) {
            if (i == 0) continue;
            for (int j = -1; j < 2; j += 2) {
                if(isValidMove(i, (3 - Math.abs(i)) * j)){
                    isMoves[this.square + (i * 8) + ((3 - Math.abs(i)) * j)] = true;
                }
            }
        }

        int numOfValidMoves = 0;
        for (boolean isMove : isMoves) {
            if (isMove) numOfValidMoves++;
        }

        int[] validMoves = new int[numOfValidMoves];

        int indexer = 0;
        for (int i = 0; i < 64; i++){
            if(isMoves[i]){
                validMoves[indexer] = i;
                indexer++;
            }
        }

        return validMoves;
    }
}