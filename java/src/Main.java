import chess.Board;
import chess.Game;
import chess.Move;

import java.util.Scanner;
import minimaxBot.ChessBot;

public class Main{
    private static Game game = new Game();
    public static void main(String[] args) {
        ChessBot chessBot = new ChessBot(game, 4);
        Scanner scanner = new Scanner(System.in);

        while (game.isWin() == 0){
            game.printBoard();
            Move move;

            for (Move tempMove : game.getAllMoves()) {
                System.out.println(tempMove);
            }
            
            while(true) {
                try{
                    int fromSquare = scanner.nextInt();
                    int toSquare = scanner.nextInt();
                    move = new Move(fromSquare, toSquare);
                    if(!game.turn(move)){
                        break;
                    }

                } catch(Exception e){
                    System.out.println("Undoing last move");
                    game.undoTurn();
                    game.undoTurn();
                    game.printBoard();
                    scanner.nextLine(); // Clear the invalid input
                    continue;
                }

            } 

            game.printBoard();

            chessBot.turn();

        }
        scanner.close();
    }

    public static Board getBoard(){
        return game.getBoard();
    }
}