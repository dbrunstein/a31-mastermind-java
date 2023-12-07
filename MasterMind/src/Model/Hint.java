package Model;

public class Hint {
    private Boolean rightColor;
    private Boolean knownPosition;
    private HintType type;
    public Hint(){ // si un pion est bien placé
        this.type = HintType.KNOWN_COLOR; // si un indice est créé, on part du principe qu'il connait la couleur
    }
    public Hint(HintType type){ // si un pion est bien placé
        this.type = type;
    }
    public void setHint(HintType type){
        this.type = type;
    }
    public HintType getHintType(){
        return this.type;
    }
}
