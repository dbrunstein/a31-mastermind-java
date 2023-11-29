package Model;

public class Hint {
    private Boolean wellPlaced;
    public Hint(Boolean wellPlaced){ // si un pion est bien placé
        this.wellPlaced = wellPlaced;
    }
    public void setHint(Boolean wellPlaced){
        this.wellPlaced = wellPlaced;
    }
}
