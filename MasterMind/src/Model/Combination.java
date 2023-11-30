package Model;

import java.util.ArrayList;

public class Combination {
    private ArrayList<Pawn> pawnList;
    public Combination(){
        this.pawnList = new ArrayList<Pawn>();
        this.hintsline = new ArrayList<Hint>();
    }
    public void createCombination(int nbPawn){
        for (int i=0;i<nbPawn;i++){
            this.pawnList.add(new Pawn());
        }
    }
    private ArrayList<Hint> hintsline;
    public int getSize(){ // probablement inutile
        return this.pawnList.size();
    }
    public ArrayList<Pawn> getCombination(){
        return this.pawnList;
    }
    public void setCombination(ArrayList<Pawn> pawnList){
        this.pawnList = pawnList;
    }
    public ArrayList<Hint> getHintsline(){
        return this.hintsline;
    }
    public void displayCombination(){
        System.out.println("SEKRIT COMBINATION --- CLASSIFIED VERY SEKRIT --- DO NOT SHARE");
        for(Pawn pawn : this.pawnList){
            System.out.println(pawn.getPawnColor());
        }
    }
    // test pour les hints, probablement à refaire vu la tronche monstrueuse du truc
    public void testCombination(Combination combination){
        ArrayList<Pawn> combinationList = combination.getCombination();
        ArrayList<Color> alreadyInList = new ArrayList<Color>();
        ArrayList<Hint> hintList = new ArrayList<Hint>();

        for(int i=0;i<combinationList.size();i++){
            Pawn pawn = combinationList.get(i);
            //if(!alreadyInList.contains(pawn.getPawnColor())){ // si la couleur n'est pas déja inclus
                if(this.containsColor(pawn)){ // si la couleur est contenue dedans

                    if(this.positionIsKnown(pawn,i)){ // la bonne couleur et position
                        hintList.add(new Hint(true,true));
                        System.out.println(" SAME POSITION : PAWN NUMBER : "+i);
                    }
                    else{
                        hintList.add(new Hint(true)); // juste la bonne couleur
                        System.out.println("SAME COLOR : PAWN NUMBER : "+i);
                    }
                }
                System.out.println("TOTAL FAILURE : PAWN NUMBER : "+i);
                alreadyInList.add(pawn.getPawnColor());
            //}
        }
        this.displayCombination();
        this.hintsline = hintList;
    }
    public Boolean combinationIsEqual(Combination combination){ // si les combinaisons sont égal (même couleur/position)
        Boolean isEqual = false;
        int nbRight = 0;
        ArrayList<Pawn> pawnList = combination.getCombination();
        ArrayList<Pawn> pawnListSecret = this.getCombination();
        for(int i=0;i<pawnList.size();i++){
            if(this.comparePawn(pawnList.get(i),pawnListSecret.get(i))){
                nbRight++;
            }
        }
        if(nbRight==pawnList.size()){
            isEqual = true;
        }
        return isEqual;
    }
    //fonction monstrueusement compliquée pour rien
    public Boolean positionIsKnown(Pawn pawn,int index){ // test si la couleur est à la bonne position
        Boolean isKnown = false;
        ArrayList<Pawn> mainList = this.getCombination();
        if(this.comparePawn(mainList.get(index),pawn)){
            isKnown = true;
        }
        return isKnown;
    }
    // verifie que la couleur est contenue dans la combinaison
    public Boolean containsColor(Pawn pawn){
        Boolean contains = false;
        for(int i=0; i<this.pawnList.size();i++){
            Pawn localPawn = this.pawnList.get(i);
            if(this.comparePawn(localPawn,pawn)){
                contains = true;
                i =this.pawnList.size()+1;
            }
        }

        return contains;
    }
    // compare la couleur de 2 pions
    public Boolean comparePawn(Pawn pawnOne, Pawn pawnTwo){
        Boolean sameColor = false;
        if(pawnOne.getPawnColor()==pawnTwo.getPawnColor()){
            sameColor = true;
        }
        return sameColor;
    }

}
