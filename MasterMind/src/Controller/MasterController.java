package Controller;
import Model.*;
import View.*;

import javax.swing.*;


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
    public String[] getAllColorsString(){
        Color[]colors=Color.values();
        String[] allColors = new String[colors.length];
        for(int i=0;i<allColors.length;i++){
            allColors[i] = colors[i].name();
        }
        return allColors;
    }
    public Color[] getAllColors(){
        return Color.values();
    }

    //###################### SETTERS ######################
    public void addScore(int scoreAdded){ board.addScore(scoreAdded,1); } // plus utilisé
    public void setGameWindow(GameWindow gameWindow) {this.gameWindow = gameWindow; }
    public void addObserver(GameWindow gameWindow) { board.addObserver(gameWindow); }
    public void removeObserver(GameWindow gameWindow) { board.removeObserver(gameWindow); }
    public void setPlayerCombination(String[] combination){this.board.setPlayerCombination(combination);}
    public void setSettings(Settings settings) {
        board.setSettings(settings);
        System.out.println("test 2");}

    //###################### OTHERS ######################
    public void play(){
        this.board.play();
    }
}