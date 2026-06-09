package chess;

public class Board {

private Piece[] board;

    public Board() {
        this.board = new Piece[]{new Rook(1, 0, this),   new Knight(1, 1, this),   new Bishop(1, 2, this),   new Queen(1, 3, this),   new King(1, 4, this),   new Bishop(1, 5, this),   new Knight(1, 6, this),   new Rook(1, 7, this),
                                 new Pawn(1, 8, this),   new Pawn(1, 9, this),     new Pawn(1, 10, this),    new Pawn(1, 11, this),   new Pawn(1, 12, this),  new Pawn(1, 13, this),    new Pawn(1, 14, this),    new Pawn(1, 15, this),
                                 null, null, null, null, null, null, null, null,
                                 null, null, null, null, null, null, null, null,
                                 null, null, null, null, null, null, null, null,
                                 null, null, null, null, null, null, null, null,
                                 new Pawn(-1, 48, this), new Pawn(-1, 49, this),   new Pawn(-1, 50, this),   new Pawn(-1, 51, this),  new Pawn(-1, 52, this), new Pawn(-1, 53, this),   new Pawn(-1, 54, this),   new Pawn(-1, 55, this),
                                 new Rook(-1, 56, this), new Knight(-1, 57, this), new Bishop(-1, 58, this), new Queen(-1, 59, this), new King(-1, 60, this), new Bishop(-1, 61, this), new Knight(-1, 62, this), new Rook(-1, 63, this)};
    }

    public Board(Board other) {
        this.board = new Piece[64];
        for (int i = 0; i < 64; i++) {
            if (other.board[i] != null) {
                this.board[i] = other.board[i].copy(this);
            }
        }
    }
    
    public void resetBoard(){
        this.board = new Piece[]{new Rook(1, 0, this),   new Knight(1, 1, this),   new Bishop(1, 2, this),   new Queen(1, 3, this),   new King(1, 4, this),   new Bishop(1, 5, this),   new Knight(1, 6, this),   new Rook(1, 7, this),
                                 new Pawn(1, 8, this),   new Pawn(1, 9, this),     new Pawn(1, 10, this),    new Pawn(1, 11, this),   new Pawn(1, 12, this),  new Pawn(1, 13, this),    new Pawn(1, 14, this),    new Pawn(1, 15, this),
                                 null, null, null, null, null, null, null, null,
                                 null, null, null, null, null, null, null, null,
                                 null, null, null, null, null, null, null, null,
                                 null, null, null, null, null, null, null, null,
                                 new Pawn(-1, 48, this), new Pawn(-1, 49, this),   new Pawn(-1, 50, this),   new Pawn(-1, 51, this),  new Pawn(-1, 52, this), new Pawn(-1, 53, this),   new Pawn(-1, 54, this),   new Pawn(-1, 55, this),
                                 new Rook(-1, 56, this), new Knight(-1, 57, this), new Bishop(-1, 58, this), new Queen(-1, 59, this), new King(-1, 60, this), new Bishop(-1, 61, this), new Knight(-1, 62, this), new Rook(-1, 63, this)};
    }

    public boolean isOccupy(int square){
        return this.board[square] != null;
    }

    public int getColor(int square){
        return this.board[square].getColor();
    }

    public Piece getSquare(int square){
        return this.board[square];
    }

    public boolean movePiece(Move move){
        Piece piece = this.board[move.fromSquare()];
        if (piece == null) return false;
        for (Move m : piece.getMoves()){
            if (m.toSquare() == move.toSquare()){
                movePiece(piece, move.toSquare());
                return true;
            }
        }
        return false;
    }

    private void movePiece(Piece piece, int toSquare){
        this.board[toSquare] = piece;
        this.board[piece.getSquare()] = null;
        piece.moveTo(toSquare);
        if(piece instanceof Pawn){
            if ((toSquare / 8 == 0 && piece.getColor() == 1) || (toSquare / 8 == 7 && piece.getColor() == -1)){
                this.board[toSquare] = new Queen(piece.getColor(), toSquare, this);
            }
        }
    }

    public Move[] getAllMoves(int color){
        int numOfMoves = 0;
        for (Piece piece : this.board){
            if (piece != null && piece.getColor() == color) numOfMoves += piece.getMoves().length;
        }

        Move[] allMoves = new Move[numOfMoves];
        int indexer = 0;
        for (Piece piece : this.board){
            if (piece != null && piece.getColor() == color){
                for (Move move : piece.getMoves()){
                    allMoves[indexer] = move;
                    indexer++;
                }
            }
        }

        return allMoves;
    }
}