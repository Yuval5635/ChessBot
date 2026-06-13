package chess;

import java.util.ArrayList;

public class Board {

    private Piece[] board;
    
    private ArrayList<Move> moves;

    public Board() {
        this.board = new Piece[]{new Rook(1, 0, this, 'R'),   new Knight(1, 1, this, 'N'),   new Bishop(1, 2, this, 'B'),   new Queen(1, 3, this, 'Q'),   new King(1, 4, this, 'K'),   new Bishop(1, 5, this, 'B'),   new Knight(1, 6, this, 'N'),   new Rook(1, 7, this, 'R'),
                                 new Pawn(1, 8, this, 'P'),   new Pawn(1, 9, this, 'P'),     new Pawn(1, 10, this, 'P'),    new Pawn(1, 11, this, 'P'),   new Pawn(1, 12, this, 'P'),  new Pawn(1, 13, this, 'P'),    new Pawn(1, 14, this, 'P'),    new Pawn(1, 15, this, 'P'),
                                 null, null, null, null, null, null, null, null,
                                 null, null, null, null, null, null, null, null,
                                 null, null, null, null, null, null, null, null,
                                 null, null, null, null, null, null, null, null,
                                 new Pawn(-1, 48, this, 'p'), new Pawn(-1, 49, this, 'p'),   new Pawn(-1, 50, this, 'p'),   new Pawn(-1, 51, this, 'p'),  new Pawn(-1, 52, this, 'p'), new Pawn(-1, 53, this, 'p'),   new Pawn(-1, 54, this, 'p'),   new Pawn(-1, 55, this, 'p'),
                                 new Rook(-1, 56, this, 'r'), new Knight(-1, 57, this, 'n'), new Bishop(-1, 58, this, 'b'), new Queen(-1, 59, this, 'q'), new King(-1, 60, this, 'k'), new Bishop(-1, 61, this, 'b'), new Knight(-1, 62, this, 'n'), new Rook(-1, 63, this, 'r')};
        moves = new ArrayList<Move>();
    }
    
    public void resetBoard(){
        this.board = new Piece[]{new Rook(1, 0, this, 'R'),   new Knight(1, 1, this, 'N'),   new Bishop(1, 2, this, 'B'),   new Queen(1, 3, this, 'Q'),   new King(1, 4, this, 'K'),   new Bishop(1, 5, this, 'B'),   new Knight(1, 6, this, 'N'),   new Rook(1, 7, this, 'R'),
                                 new Pawn(1, 8, this, 'P'),   new Pawn(1, 9, this, 'P'),     new Pawn(1, 10, this, 'P'),    new Pawn(1, 11, this, 'P'),   new Pawn(1, 12, this, 'P'),  new Pawn(1, 13, this, 'P'),    new Pawn(1, 14, this, 'P'),    new Pawn(1, 15, this, 'P'),
                                 null, null, null, null, null, null, null, null,
                                 null, null, null, null, null, null, null, null,
                                 null, null, null, null, null, null, null, null,
                                 null, null, null, null, null, null, null, null,
                                 new Pawn(-1, 48, this, 'p'), new Pawn(-1, 49, this, 'p'),   new Pawn(-1, 50, this, 'p'),   new Pawn(-1, 51, this, 'p'),  new Pawn(-1, 52, this, 'p'), new Pawn(-1, 53, this, 'p'),   new Pawn(-1, 54, this, 'p'),   new Pawn(-1, 55, this, 'p'),
                                 new Rook(-1, 56, this, 'r'), new Knight(-1, 57, this, 'n'), new Bishop(-1, 58, this, 'b'), new Queen(-1, 59, this, 'q'), new King(-1, 60, this, 'k'), new Bishop(-1, 61, this, 'b'), new Knight(-1, 62, this, 'n'), new Rook(-1, 63, this, 'r')};
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
        moves.add(move);
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

    public void undoMove(){
        resetBoard();
        moves.remove(moves.size() - 1);
        for (Move move : moves){
            movePiece(getSquare(move.fromSquare()), move.toSquare());
        }
    }

    private void movePiece(Piece piece, int toSquare){
        this.board[toSquare] = piece;
        this.board[piece.getSquare()] = null;
        piece.moveTo(toSquare);
        if(piece instanceof Pawn){
            if ((toSquare / 8 == 0 && piece.getColor() == 1) || (toSquare / 8 == 7 && piece.getColor() == -1)){
                this.board[toSquare] = new Queen(piece.getColor(), toSquare, this, piece.getColor() == 1 ? 'Q' : 'q');
            }
        }
    }

    public Move[] getAllMoves(int color){
        Move[] allMoves = new Move[4096];
        int index = 0;

        for (int i = 0; i < 64; i++){
            if (this.isOccupy(i) && this.getColor(i) == color){
                Move[] pieceMoves = this.getSquare(i).getMoves();
                for (Move pieceMove : pieceMoves){
                    allMoves[index] = pieceMove;
                    index++;
                }
            }
        }

        Move[] allValidMoves = new Move[index];

        for (int i = 0; i < allValidMoves.length; i++){
            allValidMoves[i] = allMoves[i];
        }
        
        return allValidMoves;
    }

    public void printBoard(){
        for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				System.out.print("" + ((board[8 * row + col] == null) ? " " : (board[8 * row + col].getName())));
				if (col < 7) System.out.print("|");
			}
			System.out.println();
			if (row < 7) System.out.println("-+-+-+-+-+-+-+-");
		}
    }
}