package View;

import Controller.MasterController;
import Model.Observer;

import javax.swing.*;
import java.awt.*;

public class EndGameWindow extends JFrame implements Observer {

    //JLabel finalScore; // sert à rien

    public EndGameWindow(MasterController masterController, int score){
        super("Victory");
        this.setResizable(true);
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


        JPanel gamePanel = new JPanel(new BorderLayout());

        JLabel victoryLabel = new JLabel("YOU WIN", SwingConstants.CENTER);
        gamePanel.add(victoryLabel, BorderLayout.NORTH);
        JLabel scoreLabel = new JLabel("SCORE : "+score, SwingConstants.CENTER);

        Button btnQuit = new Button("Quit");

        btnQuit.addActionListener(actionEvent -> {
            StartWindow startWindow = new StartWindow(masterController);
            this.dispose();
        }); // Ferme la fenêtre
        gamePanel.add(btnQuit,BorderLayout.SOUTH);
        gamePanel.add(scoreLabel, BorderLayout.CENTER);
        add(gamePanel);
    }
    @Override
    public void update(int score, int round, int attempt, Boolean hasWon) { // inutile pour l'instant

    }
}
