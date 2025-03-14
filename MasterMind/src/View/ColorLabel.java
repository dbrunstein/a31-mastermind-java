package View;

import javax.swing.*;

public class ColorLabel extends JLabel {
    private String labelColor;
    private int[] position;
    public ColorLabel(ImageIcon icon, int[] position){
        super( icon);
        this.position = position;
        this.labelColor = "BEIGE";
    }
    public void setColor(String color){
        ImageFactory img = new ImageFactory();
        this.setIcon(img.createImageColorString(color));
        this.labelColor = color;
    }
    public String getLabelColor(){return this.labelColor;}
    public int[] getPosition(){return  this.position;}
}
