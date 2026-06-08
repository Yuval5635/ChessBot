
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

    public boolean isOccupy(int square){
        return board[square] == null;
    }

}