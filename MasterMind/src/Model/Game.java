package Model;

import java.util.ArrayList;

public class Game {
    private ArrayList<Round> roundList;
    private Settings settings;
    private Player player;

    public Game(){
        this.roundList = new ArrayList<Round>();
    }
    public void setRoundList(){
        for(int i = 0;i<this.getRoundAmount();i++){
            this.roundList.add(new Round(this));
        }
    }
    public void addScore(int score){
        this.player.addScore(score);
    }
    public int getRoundAmount(){
        return this.settings.getRoundAmount();
    }
    public int getPawnAmount(){
        return this.settings.getPawnAmount();
    }
    public int getCombinationPawnAmount(){
        return this.settings.getCombinationPawnAmount();
    }
    public int getAttemptAmount(){
        return this.settings.getAttemptAmount();
    }
}
