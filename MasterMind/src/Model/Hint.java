package Model;

public class Hint {
    private Boolean rightColor;
    private Boolean knownPosition;
    public Hint(){ // si un pion est bien placé
        this.rightColor = false;
        this.knownPosition = false;
    }
    public Hint(Boolean rightColor){ // si un pion est bien placé
        this.rightColor = rightColor;
        this.knownPosition = false;
    }
    public Hint(Boolean rightColor, Boolean knownPosition){ // si un pion est bien placé
        this.rightColor = rightColor;
        this.knownPosition = knownPosition;
    }

    public void setHint(Boolean rightColor, Boolean knownPosition){
        this.rightColor = rightColor;
        this.knownPosition = knownPosition;
    }
}
