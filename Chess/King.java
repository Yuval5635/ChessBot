public class King extends Piece{
    
    public King(int color, int square) {
        super(color, square);
    }
    
    @Override
    public int[] getMoves() {
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