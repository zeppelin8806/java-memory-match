import application.GameBoard;

public class MemoryMatchApp {

    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        board.setup();
        board.run();
    }
}
