package Model;

import java.util.ArrayList;

public class Game {
    private ArrayList<Round> roundList;
    private Board board;
    private int currentRound;
    private int AttemptLeft;
    public Game(Board board){
        this.roundList = new ArrayList<Round>();
        this.board = board;
        this.setRoundList();
        this.currentRound = 0;
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

        if(AttemptLeft<=0){ // au cas ou ça foire
            currentRound++;
            AttemptLeft = getAttemptAmount();
        }
        System.out.println("!!! PLAYER ENTERED COMBINATION !!!");
        Round round = roundList.get(currentRound);
        if(round.playRound(this.popPlayerCombination())){ // si round gagné
            this.board.addScore(round.getScore()); // ajoute le score de la manche
            AttemptLeft = 0;
        }
    }
    /* mode ligne de commande
    *    public void play(){ // joue une partie
        for(Round round : this.roundList){ // joue chaque manche
            for(int i=0;i<this.getAttemptAmount();i++){
                while(this.getPlayerCombination()==null){} // attend le submit du joueur (éclaté pour test)
                System.out.println("!!! PLAYER ENTERED COMBINATION !!!");
                if(round.playRound(this.popPlayerCombination())){ // si round gagné
                    this.board.addScore(round.getScore()); // ajoute le score de la manche
                    i= this.getAttemptAmount()+1;
                }
            }
        }
    }
    * */

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
        //this.board.askCombination();
    }
    public Combination getPlayerCombination(){
        return this.board.getPlayerCombination();
    }
    public Combination popPlayerCombination(){
        return this.board.popPlayerCombination();
    }
    public void addScore(int score){
        this.board.addScore(score);
    }
}
