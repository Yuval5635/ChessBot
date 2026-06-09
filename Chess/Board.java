
public class Board {

    private static Board instance;

    private Piece[] board;

    public static Board getInstance() {
        if (instance == null) instance = new Board();
        return instance;
    }

    private Board() {
        this.board = new Piece[]{new Rook(1, 0),   new Knight(1, 1),   new Bishop(1, 2),   new Queen(1, 3),   new King(1, 4),   new Bishop(1, 5),   new Knight(1, 6),   new Rook(1, 7),
                                 new Pawn(1, 8),   new Pawn(1, 9),     new Pawn(1, 10),    new Pawn(1, 11),   new Pawn(1, 12),  new Pawn(1, 13),    new Pawn(1, 14),    new Pawn(1, 15),
                                 null,             null,               null,               null,              null,             null,               null,               null,
                                 null,             null,               null,               null,              null,             null,               null,               null,
                                 null,             null,               null,               null,              null,             null,               null,               null,
                                 null,             null,               null,               null,              null,             null,               null,               null,
                                 new Pawn(-1, 48), new Pawn(-1, 49),   new Pawn(-1, 50),   new Pawn(-1, 51),  new Pawn(-1, 52), new Pawn(-1, 53),   new Pawn(-1, 54),   new Pawn(-1, 55),
                                 new Rook(-1, 56), new Knight(-1, 57), new Bishop(-1, 58), new Queen(-1, 59), new King(-1, 60), new Bishop(-1, 61), new Knight(-1, 62), new Rook(-1, 63)};
    }

    public void resetBoard(){
        instance = new Board();
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

    public boolean movePiece(int fromSquare, int toSquare){
        Piece piece = this.board[fromSquare];
        if (piece == null) return false;
        for (int move : piece.getMoves()){
            if (move == toSquare){
                movePiece(piece, toSquare);
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
                this.board[toSquare] = new Queen(piece.getColor(), toSquare);
            }
        }
    }

    public int[] getAllMoves(int color){
        int numOfMoves = 0;
        for (Piece piece : this.board){
            if (piece != null && piece.getColor() == color) numOfMoves += piece.getMoves().length;
        }

        int[] allMoves = new int[numOfMoves];
        int indexer = 0;
        for (Piece piece : this.board){
            if (piece != null && piece.getColor() == color){
                for (int move : piece.getMoves()){
                    allMoves[indexer] = move;
                    indexer++;
                }
            }
        }

        return allMoves;
    }
}