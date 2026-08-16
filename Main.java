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

                int fromSquare = scanner.nextInt();
                int toSquare = scanner.nextInt();
                move = new Move(fromSquare, toSquare);

            } while(game.turn(move));

            game.printBoard();

            chessBot.turn();

        }
        scanner.close();
    }
}