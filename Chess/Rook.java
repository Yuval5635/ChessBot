public class Rook extends Piece{

    public Rook(int color, int square) {
        super(color, square);
    }

    @Override
    public int[] getMoves(){
        boolean[] isMoves = new boolean[64];

        for (int i = -1; i < 2; i += 2){
            for (int j = i; true; j += i){
                if (isValidRow(j)){
                    isMoves[(j * 8) + this.square] = true;
                } else{
                    break;
                }
            }
            for (int j = i; true; j += i){
                if (isValidCol(j)){
                    isMoves[j + this.square] = true;
                } else{
                    break;
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

    private boolean isValidRow(int rowOffset){
        int row = rowOffset + (this.square / 8);
        return row < 8 && row >= 0 && (! Board.getInstance().isOccupy(row + this.square % 8));
    }

    private boolean isValidCol(int colOffset){
        int col = colOffset + (this.square % 8);
        return col < 8 && col >= 0 && (! Board.getInstance().isOccupy(col + this.square / 8));
    }
}