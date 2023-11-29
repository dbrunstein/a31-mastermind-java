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
    public int getSize(){
        return this.pawnList.size();
    }
    public ArrayList<Pawn> deleteDupplicate(ArrayList<Pawn> list){
        for(){

        }
        return
    }
    public ArrayList<Pawn> getCombination(){
        return this.pawnList;
    }
    public ArrayList<Hint> testCombination(Combination combination){
        ArrayList<Pawn> combinationList = combination.getCombination();
        ArrayList<Hint> hintList = new ArrayList<Hint>();
        for(int i=0;i<combinationList.size();i++){
            Pawn pawn = combinationList.get(i);
            if (this.pawnList.contains(pawn)) {
                hintList.add(new Hint(false));
            }
        }
        for(){

        }
        return hintList;
    }
}
