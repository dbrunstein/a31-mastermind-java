package Model;

public class Pawn {
    private Color pawnColor;
    public Pawn(){
        this.pawnColor = MasterUtils.randomColor();
    }
    public Pawn(Color color){
        this.pawnColor = color;
    }
    public Color getPawnColor(){
        return this.pawnColor;
    }

}
