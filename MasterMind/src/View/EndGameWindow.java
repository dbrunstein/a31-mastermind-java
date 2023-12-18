package View;

import Controller.MasterController;
import Model.Observer;

import javax.swing.*;
import java.awt.*;

public class EndGameWindow extends JFrame implements Observer {

    //JLabel finalScore; // sert à rien

    public EndGameWindow(MasterController masterController, int score){
        super("Game Over");
        this.setResizable(true);
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


        JPanel gamePanel = new JPanel(new BorderLayout());

        JPanel centerPanel = new JPanel(new BorderLayout());

        JLabel victoryLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        gamePanel.add(victoryLabel, BorderLayout.NORTH);
        JLabel scoreLabel = new JLabel("SCORE : "+score, SwingConstants.CENTER);

        Button btnQuit = new Button("Quit");

        btnQuit.addActionListener(actionEvent -> {
            StartWindow startWindow = new StartWindow(masterController);
            this.dispose();
        }); // Ferme la fenêtre
        ImageFactory imgFact = new ImageFactory();
        JLabel winLabel = new JLabel(imgFact.createImageIcon("img/misc/zimcolored.gif","Victory gif"));

        centerPanel.add(winLabel,BorderLayout.CENTER);
        centerPanel.add(scoreLabel, BorderLayout.NORTH);
        gamePanel.add(btnQuit,BorderLayout.SOUTH);
        gamePanel.add(centerPanel,BorderLayout.CENTER);
        add(gamePanel);
    }
    @Override
    public void update(int score, int round, int attempt, Boolean hasWon) { // inutile pour l'instant

    }
}
