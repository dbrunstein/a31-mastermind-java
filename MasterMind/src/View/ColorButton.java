package View;

import javax.swing.*;

public class ColorButton extends JButton {
    private String buttonColor;
    public ColorButton(ImageIcon icon){
        super(icon);
    }
    public void setColor(String color){
        this.buttonColor = color;
    }
    public String getButtonColor(){return this.buttonColor;}
}
