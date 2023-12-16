import Controller.MasterController;
import Model.Board;
import Model.Combination;
import Model.Player;
import View.StartWindow;

public class MasterMindApp {
    public static void main(String[] args) {
        Player player = new Player();
        Board board = new Board(player);
        MasterController masterController = new MasterController(board);
        StartWindow startWindow = new StartWindow(masterController);
        board.startNewGame();
        System.out.println("MasterMindApp started");
    }
}
