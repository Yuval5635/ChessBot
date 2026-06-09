
public class Board {

    private static Board instance;

    private Piece[] board;

    public static Board getInstance() {
        if (instance == null) instance = new Board();
        return instance;
    }

    private Board() {
        this.board = new Piece[]{new Rook(1, 0), new Knight(1, 1)};
    }

    public void resetBoard(){
        instance = new Board();
    }

    public boolean isOccupy(int square){
        return board[square] != null;
    }

    public int getColor(int square){
        return board[square].getColor();
    }

    public Piece getSquare(int square){
        return board[square];
    }

    public boolean movePiece(int fromSquare, int toSquare){
        Piece piece = board[fromSquare];
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
        board[toSquare] = piece;
        board[piece.getSquare()] = null;
        piece.moveTo(toSquare);
        if(piece instanceof Pawn){
            if ((toSquare / 8 == 0 && piece.getColor() == 1) || (toSquare / 8 == 7 && piece.getColor() == -1)){
                board[toSquare] = new Queen(piece.getColor(), toSquare);
            }
        }
    }

    public int[] getAllMoves(int color){
        int numOfMoves = 0;
        for (Piece piece : board){
            if (piece != null && piece.getColor() == color) numOfMoves += piece.getMoves().length;
        }

        int[] allMoves = new int[numOfMoves];
        int indexer = 0;
        for (Piece piece : board){
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