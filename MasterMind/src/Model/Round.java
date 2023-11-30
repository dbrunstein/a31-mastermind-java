package Model;

import java.util.ArrayList;

public class Round {
    private Combination secretCombination;

    private Game game;
    private int localScore;
    private int attemptsLeft;

    public Round(Game game){
        this.secretCombination = new Combination();
        this.game = game;
        this.secretCombination.createCombination(this.game.getCombinationPawnAmount()); //crée la combinaison
        this.localScore = 0;

        this.attemptsLeft = this.game.getAttemptAmount();
    }
    public void playOneAttempt(){
        if(this.attemptsLeft >0){ // tant qu'il reste des tentatives
            this.attemptsLeft--;
            Combination playerCombination = this.game.getPlayerCombination();
            this.testCombination(playerCombination);
            //this.displayHints(); // marche pas jsp pourquoi
        }
    }
    public void playRound(){
        for(int i=0;i<this.game.getAttemptAmount();i++){ // Il faudra peut-être unifier attemptLeft et le setting
            System.out.println("Round : "+i);
            if(this.game.wantToPlay()==true){ // sera plus tard géré via controller et view
                this.game.askCombination();
                this.playOneAttempt();
                if(this.hasWon()){ // check si il a gagné
                    this.won();
                    i=this.game.getAttemptAmount()+1; // break
                }
                else{
                    System.out.println("WRONG");
                    this.displayHints();
                }
            }
            else{
                System.out.println("Skipping round");
                this.skipRound();
            }

        }
    }
    public void skipRound(){
        if(this.attemptsLeft >0){ // tant qu'il reste des tentatives
            this.attemptsLeft--;
        }
    }
    public int getScore(){
        return this.localScore;
    }
    public void testCombination(Combination combination){
        this.secretCombination.testCombination(combination); // remplace la liste afin d'éviter d'avoir
    }                                                                         // les hints de la dernière tentative
    public Boolean hasWon(){ // voir pour le refaire dans combination ?
        Boolean hasWon = false;
        int nbRight = 0;
        for(Hint hint : this.secretCombination.getHintsline()){
            if(hint.getHintColor() && hint.getHintPosition()){ // si la couleur et position sont bonnes
                nbRight++;
            }
        }
        if(nbRight==this.game.getCombinationPawnAmount()){ // si tt les indices indique que c'est bon
            hasWon = true; // il gagne
        }
        return hasWon;
    }
    public void displayHints(){
        ;
        if(this.secretCombination.getHintsline().size()==0){
            System.out.println("YOU RE SHIT!!!! NO HINTS FOR YOU!!!!");
        }
        for(Hint hint : this.secretCombination.getHintsline()){
            System.out.println("Is color right ?" + hint.getHintColor() +"| Is position right ?"+ hint.getHintPosition());
        }
    }
    public void won(){
        System.out.println("You have won this round!!!! :DDDDDDD");
        System.out.println("This round Score : "+this.localScore);
    }
}
