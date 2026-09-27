package chess;

public class King extends Piece{
    
    boolean isMoved;

    public King(int color, int square, Board board, char name) {
        super(color, square, board, name);
        this.isMoved = false;
    }
    
    @Override
    public Move[] getValidMoves() {
        boolean[] isMoves = new boolean[64];

        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if (isValidMove(i, j)){
                    isMoves[this.square + (i * 8) + j] = true;
                }
            }
        }

        if (canCastle(2)){
            isMoves[2] = true;
        }
        if (canCastle(6)){
            isMoves[6] = true;
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
    public Move[] getMovesWithDeffence() {
        boolean[] isMoves = new boolean[64];

        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if (isValidSquare(i, j)){
                    isMoves[this.square + (i * 8) + j] = true;
                }
            }
        }

        if (canCastle(2)){
            isMoves[2] = true;
        }
        if (canCastle(6)){
            isMoves[6] = true;
        }

        int numOfMoves = 0;
        for (boolean isMove : isMoves) {
            if (isMove) numOfMoves++;
        }

        Move[] moves = new Move[numOfMoves];

        int indexer = 0;
        for (int i = 0; i < 64; i++){
            if(isMoves[i]){
                moves[indexer] = new Move(this.square, i);
                indexer++;
            }
        }

        return moves;
    }

    public boolean canCastle(int toSquare){
        if (this.isMoved) return false;
        if (toSquare == 2){
            if (this.board.getSquare(0) instanceof Rook && !((Rook)this.board.getSquare(0)).isMoved()){
                if (!this.board.isOccupy(1) && !this.board.isOccupy(2) && !this.board.isOccupy(3)){
                    return true;
                }
            }
        } else if (toSquare == 6){
            if (this.board.getSquare(7) instanceof Rook && !((Rook)this.board.getSquare(7)).isMoved()){
                if (!this.board.isOccupy(5) && !this.board.isOccupy(6)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void moveTo(int square) {
        super.moveTo(square);
        this.isMoved = true;
    }

    @Override
    public Piece copy(Board newBoard) {
        return new King(this.color, this.square, newBoard, this.name);
    }
}