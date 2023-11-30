package Model;

import java.util.ArrayList;

public class Game {
    private ArrayList<Round> roundList;

    private Board board;

    public Game(Board board){
        this.roundList = new ArrayList<Round>();
        this.board = board;
        this.setRoundList();
    }
    public void setBoard(Board board){
        this.board = board;
    }
    public void setRoundList(){
        for(int i = 0;i<this.getRoundAmount();i++){
            this.roundList.add(new Round(this));
        }
    }
    public void play(){ // joue une partie
        for(Round round : this.roundList){ // joue chaque manche
            round.playRound();
            this.board.addScore(round.getScore()); // ajoute le score de la manche
        }
    }
    //// GET-VALEUR OPTIONS
    public int getRoundAmount(){
        return this.board.getRoundAmount();
    }
    public int getPawnAmount(){ // probablement inutile, juste pour le joueur
        return this.board.getPawnAmount();
    }
    public int getCombinationPawnAmount(){
        return this.board.getCombinationPawnAmount();
    }
    public int getAttemptAmount(){
        return this.board.getAttemptAmount();
    }
    public HintDisplayMode getDisplayMode(){
        return this.board.getDisplayMode();
    }

    //// PLAYER-RELATED
    public Boolean wantToPlay(){
        return this.board.wantToPlay();
    }
    public void askCombination(){
        this.board.askCombination();
    }
    public Combination getPlayerCombination(){
        return this.board.getPlayerCombination();
    }
    public void addScore(int score){
        this.board.addScore(score);
    }
}
