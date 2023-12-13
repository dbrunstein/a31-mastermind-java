package Model;

import java.util.ArrayList;

public class Round {
    private Combination secretCombination;
    //private int attemptAmount;
    private int localScore;
    private HintDisplayMode displayMode;

    public Round(Game game){
        this.secretCombination = new Combination();
        this.secretCombination.createCombination(game.getCombinationPawnAmount()); //crée la combinaison
        this.localScore = 0;
        //this.attemptAmount = game.getAttemptAmount();
        this.displayMode = game.getDisplayMode();
    }
    public Boolean playRound(Combination playerCombination){
        Boolean hasWon = false;
            this.testCombination(playerCombination);
            if(this.hasWon(playerCombination)){ // check si il a gagné
                this.won();
                hasWon = true;
            }
            else{
                System.out.println("WRONG");
                this.calculateLocalScore();
                this.displayHints();
            }
        return hasWon;
    }
    public int getScore(){
        return this.localScore;
    }
    public void calculateLocalScore(){ // calcule le score à chaque tentative
        this.localScore = 0; // car c'est de la dernière manche qui compte
        for(Hint hint : this.secretCombination.getHintsline()){
            if(hint.getHintType()==HintType.KNOWN_POSITION){ // bien placé
                this.localScore += 3;
            }
            else{ // mal placé
                this.localScore += 1;
            }
        }
    }
    public void testCombination(Combination combination){
        this.secretCombination.testCombination(combination); // remplace la liste afin d'éviter d'avoir
    }                                                                         // les hints de la dernière tentative

    public Boolean hasWon(Combination playerCombination){ // Demande à la combinaison si la combinaison est trouvée
        Boolean hasWon = this.secretCombination.combinationIsEqual(playerCombination);
        return hasWon;
    }
    public void displayHints(){ // juste du debug
        if(this.secretCombination.getHintsline().size()==0){
            System.out.println("YOU RE SHIT!!!! NO HINTS FOR YOU!!!!");
        }
        for(Hint hint : this.secretCombination.getHintsline()){
            System.out.println("Is color right ?" + hint.getHintType() +"| Is position right ?"+ hint.getHintType());
        }
    }
    public void won(){
        if(this.displayMode==HintDisplayMode.CLASSIC){
            this.localScore+= 4;
        }
        System.out.println("You have won this round!!!! :DDDDDDD");
        System.out.println("This round Score : "+this.localScore);
    }
}
