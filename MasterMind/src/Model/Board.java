package Model;

public class Board {
    private Game game;
    private Player player;
    public Board(Player player){
        this.player = player;
    }
    public void addScore(int score){
        this.player.addScore(score);
    }
    public void startNewGame(){
        this.game = new Game();
    }
}
