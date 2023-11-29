package Model;

import java.util.Scanner;

public class Board {
    private Game game;
    private Player player;
    private Settings settings;
    public Board(Player player){
        this.player = player;
        this.settings = new Settings();
    }

    public void startNewGame(){
        this.game = new Game(this);
    }
    //// GET-VALEUR OPTIONS
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
    //// PLAYER-RELATED
    public Combination getPlayerCombination(){
        return this.player.getCombination();
    }
    public void addScore(int score){
        this.player.addScore(score);
    }
    public Boolean wantToPlay(){ // pour le joueur, sera probablement manipulé par le controller plus tard
        Boolean reply = false;
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Wanna play a game? [Y/N]");

        String response = myObj.nextLine();  // Read user input
        if(reply.equals("Y")){
            reply = true;
        }
        return reply;
    }
}
