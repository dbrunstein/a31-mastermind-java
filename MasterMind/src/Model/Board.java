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
        startNewGame();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    private void notifyObservers(Boolean hasEnded, int round) {
        for (Observer observer : observers) {
            observer.update(getScore(), round, getAttemptAmount(), hasEnded); // ou une méthode spécifique d'update selon le besoin
        }
    }

    public void startNewGame(){ // start game basique pour l'instant
        this.game = new Game(this);
        this.observers.clear(); // clear les potentiels ancienne gamewindow
    }
    public void play(){
        this.game.play();
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
    public void notifyObs(Boolean hasEnded, int round){ // à revoir
        notifyObservers(hasEnded, round);
    }
    public void notifyObsHint(ArrayList<String> hintList){
            for (Observer observer : observers) {
                observer.updateHints(hintList);
            }
    }
}
