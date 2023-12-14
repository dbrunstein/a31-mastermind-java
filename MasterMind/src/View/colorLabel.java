package View;

import Model.Color;

import javax.swing.*;

public class colorLabel extends JLabel {
    private Color labelColor;
    private int[] position;
    public colorLabel(ImageIcon icon, int[] position){
        super( icon);
        this.position = position;
    }
    public void setColor(Color color){
        this.labelColor = color;
    }
    public Color getLabelColor(){return this.labelColor;}
    public int[] getPosition(){return  this.position;}
}
