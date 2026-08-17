package minimaxBot;

import chess.Board;
import chess.Game;
import chess.Move;
import chess.Piece;

public class ChessBot{

    private Game game;
    private int maxDepth;

    public ChessBot(Game game, int depth){
        this.game = game;
        this.maxDepth = depth;
    }

    public void turn(){
        Move bestMove = null;
        int bestScore = Integer.MAX_VALUE;
        int phase = 0;
        System.out.println(this.game.getAllMoves().length + " Moves:  ");
        for (int i = 0; i < 64; i++) {
            if (this.game.getBoard().isOccupy(i)) {
                phase += getPiecePhaseValue(this.game.getBoard().getSquare(i));
            }
        }
        for (Move move : this.game.getAllMoves()){

            this.game.turn(move);
            int score = miniMax(this.maxDepth + ((24 - phase)/3) * 2, Integer.MIN_VALUE, Integer.MAX_VALUE);
            this.game.undoTurn();

            System.out.println("Move:  " + move + "  Score:  " + score);

            if (score < bestScore){
                bestScore = score;
                bestMove = move;
            }
        }
        System.out.println("Best Move: " + bestMove + " Best Score: " + bestScore);
        this.game.turn(bestMove);
    }
    
    public int miniMax(int depth, int alpha, int beta) {
        
        if (this.game.isWin() != 0) {
            // System.out.println("Game Over! Winner: " + (this.game.isWin() == 1 ? "White" : "Black"));
            return 1000000 * this.game.isWin() * (this.game.isWhiteTurn() ? -1 : 1); // Return a large positive or negative score based on who wins
        }
        else if (depth == 0) {
            return evaluateBoard();
        }

        int bestScore = Integer.MIN_VALUE;
        Move[] moves = this.game.getAllMoves();

        for (Move move : moves) {
            this.game.turn(move);

            int score = -miniMax(depth - 1, -beta, -alpha); // Recurse with reduced depth and inverted alpha-beta values
            this.game.undoTurn();

            bestScore = Math.max(bestScore, score);
            if (score >= 1000000 || score <= -1000000) {
                bestScore += Math.signum(score) * 50;
            }
            alpha = Math.max(alpha, score);

            if (alpha >= beta) {
                break; // Beta cut-off
            }
        }
        if (Math.abs(bestScore) > 9000){
            bestScore -= bestScore * Math.signum(bestScore);
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

        int score = mgScore(phase) + egScore(phase);

        return score * (this.game.isWhiteTurn() ? 1 : -1);
    }

    private int getAllPieceValue(){
        int score = 0;
        
        for (int i = 0; i < 64; i++) {
            score += getPieceValue(i);
        }

        return score;
    }

    private int getAllPSTValue(int phase){
        int score = 0;

        for (int i = 0; i < 64; i++){
            score += PST.getPSTValue(game.getBoard().getSquare(i), phase) * (this.game.isWhiteTurn() ? -1 : 1);
        }

        return score;
    }

    private int getNumMovesValue(){
        return this.game.getAllMoves().length * (this.game.isWhiteTurn() ? -1 : 1);
    }

    private int getPieceValue(int square) {
        if (! (this.game.getBoard().getSquare(square) instanceof chess.Piece)) return 0;
        Piece piece = (Piece) this.game.getBoard().getSquare(square);
        int color = game.isWhiteTurn() ? piece.getColor() : -piece.getColor();
        if (piece instanceof chess.Pawn) return 110 * color;
        if (piece instanceof chess.Knight) return 352 * color;
        if (piece instanceof chess.Bishop) return 363 * color;
        if (piece instanceof chess.Rook) return 550 * color;
        if (piece instanceof chess.Queen) return 990 * color;
        if (piece instanceof chess.King) return 100000 * color; // Arbitrary high value for the king
        return 0;
    }

    private int getPiecePhaseValue(Object piece) {
        if (piece instanceof chess.Knight) return 1;
        if (piece instanceof chess.Bishop) return 1;
        if (piece instanceof chess.Rook) return 2;
        if (piece instanceof chess.Queen) return 4;
        return 0;
    }

    private int mgScore(int phase) {
        int score = 0;
        score += getAllPieceValue();
        score += getAllPSTValue(phase);
        score += getNumMovesValue() * 20;

        return (score * phase) / 24;
    }

    private int egScore(int phase) {
        int score = 0;
        score += getAllPieceValue();
        score += getAllPSTValue(phase);
        score += getNumMovesValue() * 20;

        return (score * (24 - phase)) / 24;
    }
}