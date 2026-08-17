import chess.Game;
import chess.Move;
import java.util.Scanner;
import minimaxBot.ChessBot;

public class Main{
    public static void main(String[] args) {
        Game game = new Game();
        ChessBot chessBot = new ChessBot(game, 4);
        Scanner scanner = new Scanner(System.in);

        while (game.isWin() == 0){
            game.printBoard();
            Move move;

            for (Move tempMove : game.getAllMoves()) {
                System.out.println(tempMove);
            }
            
            do {
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

            } while(true);

            game.printBoard();

            chessBot.turn();

        }
        scanner.close();
    }
}