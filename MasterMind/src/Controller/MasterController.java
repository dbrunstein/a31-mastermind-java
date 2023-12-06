package Controller;
import Model.*;
import View.*;


public class MasterController {

    Board board;
    GameWindow gameWindow;
    public MasterController(Board board) {
        this.board = board;
    }

    public void startNewGame(){
        board.startNewGame();
    }
    //les différents gets
    public int getScore() {
        return board.getScore();
    }
    public int getRoundAmount(){
        return board.getRoundAmount();
    }

    //les différents setters
    public void addScore(int scoreAdded){
        board.addScore(scoreAdded);
    }
    public void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }
    public void addObserver(GameWindow gameWindow) {
        board.addObserver(gameWindow);
    }
}