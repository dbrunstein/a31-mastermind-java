package Model;

public class Settings {

    private int roundAmount;
    private final int  ROUND_MAX = 5;
    private int pawnAmount;
    private final int  PAWN_MAX = 8;
    private final int  PAWN_MIN = 4;
    private int combinationPawnAmount;
    private final int  COMBINATION_MAX = 6;
    private int attemptAmount;
    private final int  ATTEMPT_MAX = 12;
    public Settings(){
        this.roundAmount = 3;
        this.pawnAmount = PAWN_MAX;
        this.combinationPawnAmount = 4;
        this.attemptAmount = 10;
    }
    public void setRoundAmount(int roundAmount){
        if(roundAmount<=ROUND_MAX){
            this.roundAmount = roundAmount;
        }
        else{
            System.out.println("MAX ROUND REACHED");
        }
    }

    public void setPawnAmount(int pawnAmount){
        if(pawnAmount<=PAWN_MAX && pawnAmount >=PAWN_MIN){
            this.pawnAmount = pawnAmount;
        }
        else{
            System.out.println("PAWN AMOUNT LIMIT BREACHED");
        }
    }

    public void setCombinationPawnAmount(int combinationPawnAmount){
        if(combinationPawnAmount<=COMBINATION_MAX){
            this.combinationPawnAmount = combinationPawnAmount;
        }
        else{
            System.out.println("MAX COMBINATION PAWN REACHED");
        }
    }
    public void setAttemptAmount(int attemptAmount){
        if(attemptAmount<=ATTEMPT_MAX){
            this.attemptAmount = attemptAmount;
        }
        else{
            System.out.println("MAX ATTEMPTS REACHED");
        }
    }
    public int getRoundAmount(){
        return this.roundAmount;
    }
    public int getPawnAmount(){
        return this.pawnAmount;
    }
    public int getCombinationPawnAmount(){
        return this.combinationPawnAmount;
    }
    public int getAttemptAmount(){
        return this.attemptAmount;
    }
}
