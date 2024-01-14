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
    private void notifyObservers(Boolean hasWon, int round) {
        for (Observer observer : observers) {
            observer.update(getScore(), round, getAttemptAmount(), hasWon); // ou une méthode spécifique d'update selon le besoin
        }
    }

    public void startNewGame(){ // start game basique pour l'instant
        this.game = new Game(this);
        //this.game.play();
        //System.out.println("Score final : " + this.player.getScore()); pas besoin d'afficher le score final pour le moment
    }
    public void play(){
        this.game.play();
        System.out.println("!!! ATTEMPT !!!");
    }
    //// GET-VALEUR OPTIONS
    public int getScore(){
        return player.getScore();
    }
    public String getPlayerName(){return this.player.getName();}
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
    public Combination popPlayerCombination(){
        return this.player.popCombination();
    }
    public void setPlayerCombination(String[] combination){ // envoie la combinaison sous string au player
        this.player.sendCombination(combination);
    }
    public Settings getSettings(){ return this.settings; }

    // SETTERS
    public void setSettings(Settings settings) {
        this.settings = settings;
       }
    public void setPlayerName(String name){
        this.player.setName(name);}
    public void resetPlayerScore(){
        player.resetScore();
    }
    // Peut-être changer en fonction fourre-tout pour update les vues ?
    public void addScore(int score, int round){ this.player.addScore(score); notifyObservers(false,round);}
    public void notifyObs(Boolean hasWon, int round){ // à revoir
        notifyObservers(hasWon, round);
    }
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
        Color color = Color.BLACK;              // à migrer vers MasterUtils
        Color[]colors=Color.values();
        for(int i=0;i<colors.length;i++){
            if(colorString.compareTo(colors[i].name())==0){
                color = colors[i];
                i = colors.length+1;
            }
        }
        return color;
    }

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
