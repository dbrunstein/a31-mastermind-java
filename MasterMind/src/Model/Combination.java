package Model;

import java.util.ArrayList;

public class Combination {
    private ArrayList<Pawn> pawnList;
    public Combination(){
        this.pawnList = new ArrayList<Pawn>();
    }
    public void createCombination(int nbPawn){
        for (int i=0;i<nbPawn;i++){
            this.pawnList.add(new Pawn());
        }
    }
    public ArrayList<Pawn> getCombination(){
        return this.pawnList;
    }

}
