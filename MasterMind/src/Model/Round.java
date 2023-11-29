package Model;

import java.util.ArrayList;

public class Round {
    private Combination secretCombination;
    private ArrayList<Hint> hintsline;
    private Game game;
    private int localScore;
    private int attempsLeft;

    public Round(Game game){
        this.secretCombination = new Combination();
        this.game = game;
        this.secretCombination.createCombination(this.game.getCombinationPawnAmount()); //crée la combinaison
        this.localScore = 0;
        this.hintsline = new ArrayList<Hint>();
        this.attempsLeft = this.game.getAttemptAmount();
    }
    public void playOneAttempt(){
        if(this.attempsLeft>0){ // tant qu'il reste des tentatives
            this.attempsLeft--;
            Combination playerCombination = this.game.getPlayerCombination();
            this.testCombination(playerCombination);

        }
    }
    public void skipRound(){
        if(this.attempsLeft>0){ // tant qu'il reste des tentatives
            this.attempsLeft--;
        }
    }


    public int getScore(){
        return this.localScore;
    }
    public void testCombination(Combination combination){
        this.hintsline = this.secretCombination.testCombination(combination);
    }
}
