import Controller.MasterController;
import Model.Board;
import Model.Combination;
import Model.Player;
import View.StartWindow;
import View.AskNameWindow;

public class MasterMindApp {
    public static void main(String[] args) {
        Player player = new Player();
        Board board = new Board(player);
        MasterController masterController = new MasterController(board);
        AskNameWindow askNameWindow = new AskNameWindow(masterController);

        //board.startNewGame();
        System.out.println("MasterMindApp started");
    }
}
