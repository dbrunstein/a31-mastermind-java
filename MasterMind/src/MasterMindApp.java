import Model.Board;
import Model.Player;
import View.StartWindow;

public class MasterMindApp {
    public static void main(String[] args) {
        Player player = new Player();
        Board board = new Board(player);
        //StartWindow startWindow = new StartWindow();
        board.startNewGame();


    }
}
