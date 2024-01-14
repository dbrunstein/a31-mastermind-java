package View;

import Controller.MasterController;
import Model.ScoreManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class StartWindow extends JFrame {

    // Panneau latéral
    JPanel sidepanel = new JPanel();

    public StartWindow(MasterController masterController){
        // Création de la fenêtre principale
        super("MasterMind 2077");
        this.setResizable(true);
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création des boutons
        Button btnNewGame = new Button("New Game");
        Button btnSettings = new Button("Settings");
        Button btnQuit = new Button("Quit");

        btnQuit.addActionListener(actionEvent -> {
            // Ferme le jeu
            this.dispose();
        });

        btnNewGame.addActionListener(actionEvent -> {

            // Lance une nouvelle partie
            try {
                masterController.startNewGame(); // pour que les parametres soient pris en compte (taille combi,...)
                GameWindow gameWindow = new GameWindow(masterController);
                masterController.addObserver(gameWindow);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.dispose();
        });

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.PAGE_AXIS));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(1, BoxLayout.PAGE_AXIS));

        // Ajout des boutons au panneau de boutons
        btnPanel.add(btnNewGame);
        btnPanel.add(btnSettings);
        btnPanel.add(btnQuit);

        //JLabel playerName = new JLabel("Player : " + masterController.getPlayerName());
        JLabel score = new JLabel("High scores : ");
        sidepanel.add(score);
        updateScores(masterController);

        sidepanel.setLayout(new BoxLayout(sidepanel, BoxLayout.PAGE_AXIS));
        ImageFactory imageFactory = new ImageFactory();
        JLabel image = new JLabel(imageFactory.createImageIcon("img/zatus3.gif", "ZIM"));

        // Ajout du panneau de boutons au panneau principal
        mainPanel.add(btnPanel, BorderLayout.LINE_START);
        mainPanel.add(sidepanel, BorderLayout.LINE_END);
        mainPanel.add(image, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setVisible(true);

        btnSettings.addActionListener(actionEvent -> {

            //Lance la settings window
            this.dispose();
            SettingsWindow settingsWindow = new SettingsWindow(masterController);
        });
    }

    public void updateScores(MasterController masterController){
        List<HashMap<String, Integer>> scoresList = masterController.scores;
        if (!scoresList.isEmpty()) {
            // Parcourez la liste des scores
            for (HashMap<String, Integer> score : scoresList) {
                // Obtenez le pseudo et le score du joueur
                String playerName = score.keySet().iterator().next();
                int playerScore = score.get(playerName);

                // Affichez le pseudo et le score
                JLabel player = new JLabel(playerName + ": " + playerScore);
                sidepanel.add(player);
            }
        } else {
            System.out.println("Aucun score disponible.");
        }

    }



}
