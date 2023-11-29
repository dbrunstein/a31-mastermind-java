package Model;

public class Player {
    private int score;
    private final String CONST_NAME = "Jean Pierre";
    private String name;
    public Player(){
        this.name = CONST_NAME;
        this.score = 0;
    }
    public Player(String name){
        this.name = name;
        this.score = 0;
    }
    public void addScore(int score){
        this.score += score;
    }
}
