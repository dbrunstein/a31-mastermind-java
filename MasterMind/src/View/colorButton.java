package View;

import Model.Color;

import javax.swing.*;

public class colorButton extends JButton {
    private String buttonColor;
    public colorButton(ImageIcon icon){
        super(icon);
    }
    public void setColor(String color){
        this.buttonColor = color;
    }
    public String getButtonColor(){return this.buttonColor;}
}
