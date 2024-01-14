package Model;

import java.util.ArrayList;

public class Game {
    private ArrayList<Round> roundList;
    private Board board;
    private int currentRound;
    private int attemptLeft;
    public Game(Board board){
        this.roundList = new ArrayList<Round>();
        this.board = board;
        this.setRoundList();
        this.currentRound = 0;
        attemptLeft = getAttemptAmount();
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
        System.out.println("#### CURRENT ROUND #### :"+currentRound);
        System.out.println("#### ROUNDLIST SIZE #### :"+roundList.size());
        if(currentRound<roundList.size()){ //en fonction du nombre de round
            Round round = roundList.get(currentRound);
            if(round.playRound(this.popPlayerCombination())){ // si round gagné
                this.board.addScore(round.getScore(),currentRound+1); // ajoute le score de la manche
                attemptLeft = 0;
            }
            else{
                attemptLeft--;
                this.board.notifyObsHint(round.getHintList());
            }
            if(attemptLeft <=0){ // si plus aucune tentative => round suivant
                currentRound++;
                attemptLeft = getAttemptAmount();
                this.board.addScore(0,currentRound); // ajoute le score de la manche
            }
        }
        if(currentRound==roundList.size()){ // tt les rounds fini
            System.out.println("END");
            this.board.notifyObs(true,currentRound);
            // envoyer l'info à l'observer
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
    public void addScore(int score){ // plus utilisé
        this.board.addScore(score,1);
    }
}
