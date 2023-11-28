package View;

import Model.Observer;

import javax.swing.*;
import java.awt.*;

public class StartWindow extends JFrame implements Observer {

    public StartWindow(){
        super("MasterMind 2077");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


}
