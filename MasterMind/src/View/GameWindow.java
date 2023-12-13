package View;

import Controller.MasterController;
import Model.Observer;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.channels.Channel;

public class GameWindow extends JFrame implements Observer {
    private JLabel scoreLabel; // contient le score
    private JButton[] tabSelectLables; // contient les labels de selection du joueur
    private JLabel[][] tabCombinationLabels; // contient les labels des combinaisons affichées
    private Icon selectedColor;
    private JLabel roundLabel;
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

        // nombre de tentatives + taille de la combinaison
        tabCombinationLabels = new JLabel[masterController.getAttemptAmount()][masterController.getCombinationPawnAmount()];
        tabSelectLables = new JButton[masterController.getPawnAmount()];

        // Initialisation du panneau de jeu
        JPanel gamePanel = new JPanel(new BorderLayout());

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        gamePanel.add(scoreLabel, BorderLayout.NORTH);

        // Image factory pour créer les images des couleurs
        ImageFactory imageFactory = new ImageFactory();

        // panels contenant les labels des combinaisons et de selection
        JPanel mainPanel = new JPanel(new BorderLayout()); // contient affichage et selection
        JPanel combinationsPanel = new JPanel(new GridLayout(0,1)); // affichage des combinaisons

        for(int i=0;i<masterController.getAttemptAmount();i++){ // affichage des combinaisons
            JPanel colorPanel = new JPanel(new FlowLayout()); // une combinaison

            for(int j=0;j<masterController.getCombinationPawnAmount();j++){ // met les combinaisons "à zéro"
                tabCombinationLabels[i][j] = new JLabel(imageFactory.createImageIcon("img/colors/pink.png", "color pink"));
                JLabel currentLabel = tabCombinationLabels[i][j];
                JLabel finalCurrentLabel = currentLabel;
                currentLabel.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent me) {
                        System.out.println("Changement couleur");
                        // met à jour l'image
                        if(selectedColor!=null){
                            finalCurrentLabel.setIcon(selectedColor);
                        }
                    }
                });
                colorPanel.add(tabCombinationLabels[i][j]);
            }
            combinationsPanel.add(colorPanel);
        }
        JPanel selectPanel = new JPanel(new FlowLayout()); // panel de selection des couleurs (joueur)

        String[] allColors = masterController.getAllColors(); // obtient toutes les couleurs sous forme de string

        for(int j=0;j<masterController.getPawnAmount();j++){ // met l'ensemble des couleurs disponible
            //JLabel currentLabel = new JLabel(imageFactory.createImageIcon("img/colors/"+allColors[j]+".png", "color "+allColors[j]));

            JButton currentButton = new JButton(imageFactory.createImageIcon("img/colors/"+allColors[j]+".png", "color "+allColors[j]));
            currentButton.setContentAreaFilled(false);
            currentButton.setBorderPainted(false);
            currentButton.addActionListener(actionEvent -> {
                selectColor(currentButton.getIcon());
            });
            /*
            currentLabel.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    System.out.println("Changement couleur");
                    // met à jour l'image
                    currentLabel.setIcon(imageFactory.createImageIcon("img/colors/pink.png", "color pink"));
                    currentLabel.getIcon();// utilise pour mettre dans la case clickable, trouver un moyen d'obtenir la couleur
                }
            });
            */
            tabSelectLables[j] = currentButton;
            selectPanel.add(tabSelectLables[j]);
        }

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
    public void selectColor(Icon icon){
        this.selectedColor = icon;
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
