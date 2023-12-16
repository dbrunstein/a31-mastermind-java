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
        ImageFactory img = new ImageFactory();
        this.setIcon(img.createImageColorString(color));
        this.labelColor = color;
    }
    public String getLabelColor(){return this.labelColor;}
    public int[] getPosition(){return  this.position;}
}
