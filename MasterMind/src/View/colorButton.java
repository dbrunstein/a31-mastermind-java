package View;

import Model.Color;

import javax.swing.*;

public class colorButton extends JButton {
    private Color buttonColor;
    public colorButton(ImageIcon icon){
        super(icon);
    }
    public void setColor(Color color){
        this.buttonColor = color;
    }
    public Color getButtonColor(){return this.buttonColor;}
}
