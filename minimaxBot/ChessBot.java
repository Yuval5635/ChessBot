package minimaxBot;

import chess.Board;
import chess.Game;
import chess.Move;
import chess.Piece;
public class ChessBot{

    private Game game;

    public ChessBot(Game game){
        this.game = game;
    }

    public void turn(){
        Move bestMove = null;
        int bestScore = Integer.MIN_VALUE;

        for (Move move : this.game.getAllMoves()){
            if (this.game.turn(move)){
                int score = miniMax(2, Integer.MIN_VALUE, Integer.MAX_VALUE);
                this.game.undoTurn();
                if (score > bestScore){
                    bestScore = score;
                    bestMove = move;
                }
            }
        }

        this.game.turn(bestMove);
    }
    
    public int miniMax(int depth, int alpha, int beta) {
        if (this.game.isWin() != 0) {
            return Integer.MAX_VALUE * this.game.isWin(); // Return a large positive or negative score based on who wins
        }
        if (depth == 0) {
            return evaluateBoard();
        }

        int bestScore = Integer.MIN_VALUE;
        Move[] moves = this.game.getAllMoves();

        for (Move move : moves) {
            if (this.game.turn(move)) { // Make the move
                int score = -miniMax(depth - 1, -beta, -alpha); // Recurse with reduced depth and inverted alpha-beta values
                this.game.undoTurn();

                bestScore = Math.max(bestScore, score);
                alpha = Math.max(alpha, score);

                if (alpha >= beta) {
                    break; // Beta cut-off
                }
            }
        }

        return bestScore;
    }

    private int evaluateBoard() {
        int phase = 0;
        Board board = this.game.getBoard();
        for (int i = 0; i < 64; i++) {
            if (board.isOccupy(i)) {
                phase += getPiecePhaseValue(board.getSquare(i));
            }
        }

        return mgScore(phase) + egScore(phase);
    }

    private int getAllPieceValue(){
        int score = 0;

        for (int i = 0; i < 64; i++){
            score += getPieceValue(i);
        }

        return score;
    }

    private int getPieceValue(Object square) {
        if (! (square instanceof chess.Piece)) return 0;
        Piece piece = (Piece) square;
        int color = game.isWhiteTurn() ? piece.getColor() : -piece.getColor();
        if (piece instanceof chess.Pawn) return 100 * color;
        if (piece instanceof chess.Knight) return 320 * color;
        if (piece instanceof chess.Bishop) return 330 * color;
        if (piece instanceof chess.Rook) return 500 * color;
        if (piece instanceof chess.Queen) return 900 * color;
        return 10000 * color; // Arbitrary high value for the king
    }

    private int getPiecePhaseValue(Object piece) {
        if (piece instanceof chess.Pawn) return 1;
        if (piece instanceof chess.Knight) return 2;
        if (piece instanceof chess.Bishop) return 2;
        if (piece instanceof chess.Rook) return 4;
        if (piece instanceof chess.Queen) return 8;
        return 0;
    }

    private int mgScore(int phase) {
        int score = getAllPieceValue();
        return score * phase;
    }

    private int egScore(int phase) {
        int score = getAllPieceValue();
        return score * (24 - phase);
    }
}