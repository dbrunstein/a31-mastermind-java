/*package View;

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
}*/
package View;

import Controller.MasterController;

import javax.swing.*;
import java.awt.*;

public class EndGameWindow extends JFrame {

    public EndGameWindow(MasterController masterController, int score) {
        // Créer une nouvelle fenêtre de fin de jeu
        super("End");
        this.setResizable(true);
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel fullPanel = new JPanel(new BorderLayout());
        // Récupérer le nom du joueur depuis le modèle (à remplacer avec votre fonction)
        String playerName = masterController.getPlayerName(); // Remplacer avec la fonction appropriée

        // Créer un panneau pour afficher les informations de fin de jeu
        JPanel endGamePanel = new JPanel(new GridLayout(4, 1)); // Utiliser GridLayout
        endGamePanel.setBackground(new Color(255, 223, 186)); // Couleur de fond

        // Afficher le titre
        JLabel titleLabel = new JLabel("Game Over", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Ajouter de la marge en haut
        endGamePanel.add(titleLabel);

        // Afficher le score
        JLabel scoreLabel = new JLabel("Your Score: " + score, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // Ajouter de la marge en bas

        // Afficher le nombre de rounds joués (à remplacer avec le nombre réel de rounds joués)
        JLabel roundLabel = new JLabel("Rounds Played: 5", SwingConstants.CENTER);
        roundLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        // Afficher le nom du joueur
        JLabel playerNameLabel = new JLabel("Player: " + playerName, SwingConstants.CENTER);
        playerNameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        playerNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // Ajouter de la marge en bas

        endGamePanel.add(titleLabel);
        endGamePanel.add(playerNameLabel);
        endGamePanel.add(roundLabel);
        endGamePanel.add(scoreLabel);

        JPanel restartQuitPanel = new JPanel(new BorderLayout());
        restartQuitPanel.setBackground(new Color(255, 223, 186));

        JButton btnQuit = new JButton("Quit");
        btnQuit.setFont(new Font("Arial", Font.PLAIN, 18));

        btnQuit.addActionListener(actionEvent -> {
            this.dispose();
        });

        JButton btnRestart = new JButton("Restart");
        btnRestart.setFont(new Font("Arial", Font.PLAIN, 18));

        btnRestart.addActionListener(actionEvent -> {
            masterController.resetPlayerScore();
            StartWindow startWindow = new StartWindow(masterController);
            masterController.startNewGame(); // reset la partie
            this.dispose();
        });

        restartQuitPanel.add(btnQuit,BorderLayout.WEST);
        restartQuitPanel.add(btnRestart,BorderLayout.EAST);

        fullPanel.add(endGamePanel);
        fullPanel.add(restartQuitPanel,BorderLayout.SOUTH);
        // Ajouter le panneau à la fenêtre
        this.add(fullPanel);
        // Centrer la fenêtre
        this.setLocationRelativeTo(null);

        // Rendre la fenêtre visible
        this.setVisible(true);
    }
}


