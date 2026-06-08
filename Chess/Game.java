public class Game {

    private static Game instance;

    private Board board = Board.getInstance();

    public static Game getInstance() {
        if (instance == null) instance = new Game();
        return instance;
    }

    private Game() {}

}
