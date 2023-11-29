package Model;

import java.util.ArrayList;
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
    public void askCombination(){ // demande combination au joueur
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        Combination playerCombination = new Combination();
        ArrayList<Pawn> pawnList = new ArrayList<>(); // à grand remplacer par une factory peut-être ?
        System.out.println("Enter combination : RED, GREEN, BLUE, YELLOW, ORANGE, BLACk, WHITE");
        for(int i =0;i<this.getPawnAmount();i++){
            String response = myObj.nextLine();  // Read user input
            pawnList.add(new Pawn(this.whatColor(response)));
        }
        playerCombination.setCombination(pawnList);
        this.player.setCombination(playerCombination);
    }
    public Color whatColor(String colorString){ // traduction foireuse du string à l'enum
        Color color = Color.WHITE;
        switch (colorString){
            case"RED":
                color = Color.RED;
                break;
            case"GREEN":
                color = Color.GREEN;
                break;
            case"BLUE":
                color = Color.BLUE;
                break;
            case"YELLOW":
                color = Color.YELLOW;
                break;
            case"ORANGE":
                color = Color.ORANGE;
                break;
            case"BLACK":
                color = Color.BLACK;
                break;
            case"WHITE":
                break;
            default:
                break;
        }
        return color;
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
