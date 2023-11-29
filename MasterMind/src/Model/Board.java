package Model;

public class Board {
    private Game game;
    private Player player;
    private Settings settings;
    public Board(Player player){
        this.player = player;
        this.settings = new Settings();
    }
    public void addScore(int score){
        this.player.addScore(score);
    }
    public void startNewGame(){
        this.game = new Game(this);
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
    public Combination getPlayerCombination(){
        return this.player.getCombination();
    }

}
