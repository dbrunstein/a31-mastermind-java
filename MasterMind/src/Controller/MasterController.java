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

    //###################### GETTERS ######################
    public int getScore() { return board.getScore(); }
    public int getRoundAmount(){ return board.getRoundAmount(); }
    public int getPawnAmount(){ return board.getPawnAmount(); }
    public int getCombinationPawnAmount(){ return board.getCombinationPawnAmount(); }
    public int getAttemptAmount(){ return board.getAttemptAmount(); }
    public HintDisplayMode getDisplayMode(){ return board.getDisplayMode(); }
    public Combination getPlayerCombination(){ return board.getPlayerCombination(); }
    public Boolean wantToPlay(){ return board.wantToPlay(); }
    public Settings getSettings(){ return board.getSettings(); }

    //###################### SETTERS ######################
    public void addScore(int scoreAdded){ board.addScore(scoreAdded); }
    public void setGameWindow(GameWindow gameWindow) {this.gameWindow = gameWindow; }
    public void addObserver(GameWindow gameWindow) { board.addObserver(gameWindow); }
    public void removeObserver(GameWindow gameWindow) { board.removeObserver(gameWindow); }
    public void setSettings(Settings settings) { board.setSettings(settings); }

}