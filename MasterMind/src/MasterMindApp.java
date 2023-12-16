import Controller.MasterController;
import Model.Board;
import Model.Combination;
import Model.Player;
import View.StartWindow;

public class MasterMindApp {
    public static void main(String[] args) {
        Player player = new Player();
        Combination coom = new Combination();
        coom.createCombination(4);
        player.setCombination(coom);
        Board board = new Board(player);
        MasterController masterController = new MasterController(board);
        StartWindow startWindow = new StartWindow(masterController);
        board.startNewGame();
        System.out.println("MasterMindApp started");
    }
}
