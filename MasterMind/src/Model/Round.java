package Model;

import java.util.ArrayList;

public class Round {
    private Combination secretCombination;
    private ArrayList<Hint> hintsline;
    private Game game;
    private int localScore;

    public Round(Game game){
        this.secretCombination = new Combination();
        this.game = game;
        this.secretCombination.createCombination(this.game.getPawnAmount()); //crée la combinaison
        this.localScore = 0;
    }
    public int getScore(){
        return this.localScore;
    }
}
