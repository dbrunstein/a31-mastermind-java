package View;

import Model.Color;

import javax.swing.*;

public class colorLabel extends JLabel {
    private Color labelColor;
    public colorLabel(ImageIcon icon){
        super( icon);
    }
    public void setColor(Color color){
        this.labelColor = color;
    }
    public Color getButtonColor(){return this.labelColor;}
}
