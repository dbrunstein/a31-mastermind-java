package Model;

import java.util.ArrayList;

public class Game {
    private ArrayList<Round> roundList;

    private Board board;

    public Game(Board board){
        this.roundList = new ArrayList<Round>();
        this.board = board;
    }
    public void setBoard(Board board){
        this.board = board;
    }
    public void setRoundList(){
        for(int i = 0;i<this.getRoundAmount();i++){
            this.roundList.add(new Round(this));
        }
    }
    public void addScore(int score){
        this.board.addScore(score);
    }
    public int getRoundAmount(){
        return this.board.getRoundAmount();
    }
    public int getPawnAmount(){
        return this.board.getPawnAmount();
    }
    public int getCombinationPawnAmount(){
        return this.board.getCombinationPawnAmount();
    }
    public int getAttemptAmount(){
        return this.board.getAttemptAmount();
    }

    public Combination getPlayerCombination(){
        return this.board.getPlayerCombination();
    }

}
