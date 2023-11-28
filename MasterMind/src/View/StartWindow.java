package View;

import Model.Observer;

import javax.swing.*;
import java.awt.*;

public class StartWindow extends JFrame implements Observer {

    public StartWindow(){
        super("MasterMind 2077");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Button btnNewGame = new Button("New Game"); //
        Button btnSettings = new Button("Settings"); // permet de modifier les options
        Button btnQuit = new Button("Quit"); // ferme le jeu

        btnQuit.addActionListener( actionEvent  -> { // ferme le jeu
            this.dispose();
        });

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel,BoxLayout.PAGE_AXIS));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(1,BoxLayout.PAGE_AXIS));
        //mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));

        // BTN PANEL
        btnPanel.add(btnNewGame);
        btnPanel.add(btnSettings);
        btnPanel.add(btnQuit);
        btnPanel.add(btnQuit);

        // SIDE PANEL
        JPanel sidepanel = new JPanel();
        JLabel score = new JLabel("High Score :");
        sidepanel.add(score);
        sidepanel.setLayout(new BoxLayout(sidepanel,BoxLayout.PAGE_AXIS));

        ImageFactory imgMenu = new ImageFactory("img/zatus3.gif");

        //MAIN PANEL
        mainPanel.add(imgMenu.getLabel(),BorderLayout.CENTER);
        mainPanel.add(btnPanel,BorderLayout.LINE_START);
        mainPanel.add(sidepanel,BorderLayout.LINE_END);

        setContentPane(mainPanel);
        setVisible(true);
    }


}
