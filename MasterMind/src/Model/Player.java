package Model;

import java.util.ArrayList;

public class Player {
    private int score;
    private Combination combination;
    private final String NAME = "Jean Pierre"; // : String <<readonly>>
    private String name;
    public Player(){
        this.name = NAME;
        this.score = 0;
    }
    public Player(String name){
        this.name = name;
        this.score = 0;
    }
    public void setCombination(Combination combination){
        this.combination = combination;
    }

    public void sendCombination(String[] sentCombination){ // convertit la combinaison de string à combinaison
        // préférable à migrer vers combination elle même pour limité les relations avec pawn
        Combination combination = new Combination();
        ArrayList<Pawn> pawnArrayList = new ArrayList<Pawn>();
        Color[] colors = Color.values();
        for(int i=0;i<sentCombination.length;i++){
            for(int j=0;j<colors.length;j++){
                if(sentCombination[i].equals(colors[j].name())){
                    pawnArrayList.add(new Pawn(colors[j]));
                }
            }
        }
        combination.setCombination(pawnArrayList);
        setCombination(combination);
    }

    public void addScore(int score){
        this.score += score;
    }
    public Combination popCombination(){ // pop la combinaison comme dans une liste
        Combination returnCombination = this.combination;
        this.combination = null;
        return returnCombination;
    }
    public Combination getCombination(){
        return this.combination;
    }
    public int getScore(){
        return this.score;
    }
}
