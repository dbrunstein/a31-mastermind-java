package View;

import Controller.MasterController;
import Model.Observer;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame implements Observer {

    //Déclaration des variables qui influent sur le changement visuel de la fenetre
    private JLabel scoreLabel;
    private JLabel roundLabel;

    //Déclaration du masterController
    private MasterController masterController;



    public GameWindow(MasterController masterController) throws InterruptedException {
        // Initialisation de la fenêtre
        super("MasterMind 2077");
        this.setResizable(true);
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        this.masterController = masterController;
        masterController.setGameWindow(this);

        // Initialisation du panneau de jeu
        JPanel gamePanel = new JPanel(new BorderLayout());

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        gamePanel.add(scoreLabel, BorderLayout.NORTH);

        // zone test panels couleurs
        ImageFactory imageFactory = new ImageFactory();
        JLabel blue = new JLabel(imageFactory.createImageIcon("img/colors/blue.png", "color blue"));
        JLabel red = new JLabel(imageFactory.createImageIcon("img/colors/red.png", "color red"));
        JLabel black = new JLabel(imageFactory.createImageIcon("img/colors/black.png", "color black"));
        JLabel green = new JLabel(imageFactory.createImageIcon("img/colors/green.png", "color green"));

        // à mettre en attribut une fois stable
        JPanel mainPanel = new JPanel(new BorderLayout()); // contient affichage et selection
        JPanel combinationsPanel = new JPanel(new GridLayout(0,1)); // juste affichage //




        for(int i=0;i<masterController.getAttemptAmount();i++){ // test pour afficher plusieurs combinaisons test
            JPanel colorPanel = new JPanel(new FlowLayout()); // une combinaison
            JLabel blue1 = new JLabel(imageFactory.createImageIcon("img/colors/blue.png", "color blue"));
            JLabel red1 = new JLabel(imageFactory.createImageIcon("img/colors/red.png", "color red"));
            JLabel black1 = new JLabel(imageFactory.createImageIcon("img/colors/black.png", "color black"));
            JLabel green1 = new JLabel(imageFactory.createImageIcon("img/colors/green.png", "color green"));
            colorPanel.add(blue1);
            colorPanel.add(red1);
            colorPanel.add(black1);
            colorPanel.add(green1);
            combinationsPanel.add(colorPanel);
        }

        JPanel selectPanel = new JPanel(new FlowLayout()); // panel de selection des couleur (joueur)

        selectPanel.add(blue);
        selectPanel.add(red);
        selectPanel.add(black);
        selectPanel.add(green);

        selectPanel.setBackground(Color.lightGray);

        mainPanel.add(combinationsPanel,BorderLayout.CENTER);
        mainPanel.add(selectPanel,BorderLayout.SOUTH);

        Button btnAddScore = new Button("Add");
        btnAddScore.addActionListener(actionEvent -> {
            masterController.addScore(1);
        });

        gamePanel.add(btnAddScore, BorderLayout.SOUTH);
        gamePanel.add(mainPanel,BorderLayout.CENTER);


        add(gamePanel);
    }

    @Override
    public void update() {
        // Mise à jour du score affiché à chaque notification
        int updatedScore = masterController.getScore();
        scoreLabel.setText("Score: " + updatedScore);

        int updatedRound = masterController.getRoundAmount();
        roundLabel.setText("Round: " + updatedRound);


        /*liste de choses que la fenetre devra update :


        - le nombre de manches restantes
        - le nombre de pions juste et mal placés
        - la combinaison secrète mais seulement à la fin
        - la combinaison du joueur

        puis on verra pour le reste
         */
    }
}
