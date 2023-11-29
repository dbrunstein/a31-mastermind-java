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
    public int getSize(){ // probablement inutile
        return this.pawnList.size();
    }
    public ArrayList<Pawn> getCombination(){
        return this.pawnList;
    }
    public void setCombination(ArrayList<Pawn> pawnList){
        this.pawnList = pawnList;
    }
    public ArrayList<Hint> testCombination(Combination combination){
        ArrayList<Pawn> combinationList = combination.getCombination();
        ArrayList<Color> alreadyInList = new ArrayList<Color>();
        ArrayList<Hint> hintList = new ArrayList<Hint>();

        for(int i=0;i<combinationList.size();i++){
            Pawn pawn = combinationList.get(i);
            if(!alreadyInList.contains(pawn.getPawnColor())){ // si la couleur n'est pas déja inclus
                if(this.getCombination().contains(pawn)){ // si la couleur est contenue dedans
                    if(this.positionIsKnown(pawn,combinationList)){ // la bonne couleur et position
                        hintList.add(new Hint(true,true)); // juste la bonne couleur
                    }
                    else{
                        hintList.add(new Hint(true)); // juste la bonne couleur
                    }
                }
                alreadyInList.add(pawn.getPawnColor());
            }
        }
        return hintList;
    }
    //fonction monstrueusement compliquée pour rien
    public Boolean positionIsKnown(Pawn pawn, ArrayList<Pawn> combinationList){ // test si la couleur est à la bonne position
        Boolean isKnown = false;
        ArrayList<Pawn> mainList = this.getCombination();
        for(int i=0;i<mainList.size();i++){
            if(this.comparePawn(mainList.get(i),pawn) && mainList.get(i)==combinationList.get(i)){ // && i==y
                isKnown = true;
                i = mainList.size()+1; // break
            }
        }
        return isKnown;
    }
    public Boolean comparePawn(Pawn pawnOne, Pawn pawnTwo){
        Boolean sameColor = false;
        if(pawnOne.getPawnColor()==pawnTwo.getPawnColor()){
            sameColor = true;
        }
        return sameColor;
    }
}
