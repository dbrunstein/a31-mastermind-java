package Model;

public class Pawn {
    private Color pawnColor;
    public Pawn(){
        this.pawnColor = MasterUtils.randomColor();
    }
    public void setPawnColor(Color pawnColor){
        this.pawnColor = pawnColor;
    }
    public Pawn(Color color){
        this.pawnColor = color;
    }
    public Color getPawnColor(){
        return this.pawnColor;
    }

}
