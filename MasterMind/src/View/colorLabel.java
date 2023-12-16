package View;

import Model.Color;

import javax.swing.*;

public class colorLabel extends JLabel {
    private String labelColor;
    private int[] position;
    public colorLabel(ImageIcon icon, int[] position){
        super( icon);
        this.position = position;
    }
    public void setColor(String color){
        this.labelColor = color;
    }
    public String getLabelColor(){return this.labelColor;}
    public int[] getPosition(){return  this.position;}
}
