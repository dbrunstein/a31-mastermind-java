package Model;

public class Pawn {
    private Color pawnColor;
    public Pawn(){
        this.pawnColor = MasterUtils.randomColor();
    }
    public Pawn(int range){
        this.pawnColor = MasterUtils.randomColorRange(range);
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
