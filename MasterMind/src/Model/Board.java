package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    private Game game;
    private final Player player;
    private Settings settings;

    private final ArrayList<Observer> observers;

    public Board(Player player){
        this.player = player;
        this.observers = new ArrayList<>();
        this.settings = new Settings(); // settings par défaut
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(); // ou une méthode spécifique d'update selon le besoin
        }
    }

    public void startNewGame(){ // start game basique pour l'instant
        this.game = new Game(this);
        this.game.play();
        //System.out.println("Score final : " + this.player.getScore()); pas besoin d'afficher le score final pour le moment
    }
    //// GET-VALEUR OPTIONS
    public int getScore(){
        return player.getScore();
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
    public HintDisplayMode getDisplayMode(){
        return this.settings.getDisplayMode();
    }
    //// PLAYER-RELATED
    public Combination getPlayerCombination(){
        return this.player.getCombination();
    }
    public Settings getSettings(){ return this.settings; }

    // SETTERS
    public void setSettings(Settings settings) { this.settings = settings; }

    public void addScore(int score){ this.player.addScore(score); notifyObservers();}
    /*
    public void askCombination(){ // demande combination au joueur
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        Combination playerCombination = new Combination();
        ArrayList<Pawn> pawnList = new ArrayList<>(); // à grand remplacer par une factory peut-être ?
        System.out.println("Enter combination : RED, GREEN, BLUE, YELLOW, ORANGE, BLACk, WHITE");
        for(int i =0;i<this.getCombinationPawnAmount();i++){
            String response = myObj.nextLine();  // Read user input
            pawnList.add(new Pawn(this.whatColor(response)));
        }
        playerCombination.setCombination(pawnList);
        this.player.setCombination(playerCombination);
    }
    public Color whatColor(String colorString){ // traduction foireuse du string à l'enum
        Color color = Color.WHITE;              // à migrer vers MasterUtils
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
    }*/

    public Boolean wantToPlay(){ // pour le joueur, sera probablement manipulé par le controller plus tard
        Boolean reply = false;
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Wanna play a game? [y/n]");

        String response = myObj.next();  // Read user input
        if(response.equals("y")){
            reply = true;
            System.out.println("OUI");
        }
        return reply;
    }



}
