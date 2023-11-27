import Controller.MasterController;
import Model.MasterMindGame;
import View.StartWindow;

public class MasterMindApp {
    public static void main(String[] args) {
        MasterMindGame game = new MasterMindGame();
        MasterController controller = new MasterController(game);
        StartWindow mainWindow = new StartWindow(controller);
    }
}
