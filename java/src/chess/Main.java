package chess;

import java.util.Scanner;
import minimaxBot.ChessBot;

public class Main{
    private Game game;
    ChessBot chessBot;

    public Main(){
        this.game = new Game();
        this.chessBot = new ChessBot(game, 4);
    }
    // public static void main(String[] args) {
    //     ChessBot chessBot = new ChessBot(game, 4);
    //     Scanner scanner = new Scanner(System.in);

    //     while (game.isWin() == 0){
    //         game.printBoard();
    //         Move move;

    //         for (Move tempMove : game.getAllMoves()) {
    //             System.out.println(tempMove);
    //         }
            
    //         while(true) {
    //             try{
    //                 int fromSquare = scanner.nextInt();
    //                 int toSquare = scanner.nextInt();
    //                 move = new Move(fromSquare, toSquare);
    //                 if(!game.turn(move)){
    //                     break;
    //                 }

    //             } catch(Exception e){
    //                 System.out.println("Undoing last move");
    //                 game.undoTurn();
    //                 game.undoTurn();
    //                 game.printBoard();
    //                 scanner.nextLine(); // Clear the invalid input
    //                 continue;
    //             }

    //         } 

    //         game.printBoard();

    //         chessBot.turn();

    //     }
    //     scanner.close();
    // }

    public Board getBoard(){
        return game.getBoard();
    }

    public boolean isMoveValid(int fromSquare, int toSquare){
        return game.isMoveValid(fromSquare, toSquare);
    }

    public boolean turn(int fromSquare, int toSquare){
        return game.turn(new Move(fromSquare, toSquare));
    }

    public void botTurn(){
        chessBot.turn();
    }

    public void undoTurn(){
        game.undoTurn();
    }

    public boolean isWhiteTurn(){
        return game.isWhiteTurn();
    }

    public boolean isWin(){
        return game.isWin() != 0;
    }
}