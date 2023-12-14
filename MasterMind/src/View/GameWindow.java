package View;

import Controller.MasterController;
import Model.Observer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GameWindow extends JFrame implements Observer {
    private JLabel scoreLabel; // contient le score
    private colorButton[] tabSelectLabels; // contient les labels de selection du joueur <--- probablement inutile
    private colorLabel[][] tabCombinationLabels; // contient les labels des combinaisons affichées
    private Model.Color selectedColor;
    private JLabel roundLabel;
    private int currentAttempt;
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
        tabCombinationLabels = new colorLabel[masterController.getAttemptAmount()][masterController.getCombinationPawnAmount()];
        tabSelectLabels = new colorButton[masterController.getPawnAmount()];

        currentAttempt = 0; // faire un update dessus
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
                int[] position = {i,j};// i attempt, j position dans la combinaison
                tabCombinationLabels[i][j] = new colorLabel(imageFactory.createImageIcon("img/colors/PINK.png", "color pink"),position);
                colorLabel currentLabel = tabCombinationLabels[i][j];
                colorLabel finalCurrentLabel = currentLabel;
                currentLabel.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent me) {
                        System.out.println("Changement couleur");
                        // met à jour l'image
                        int attemptNb = masterController.getAttemptAmount() - finalCurrentLabel.getPosition()[0] -1; // -1 car va de 0 à n
                        System.out.println(attemptNb);
                        if(selectedColor!=null && attemptNb==currentAttempt){ // s'exécute si c'est la tentative actuelle
                            ImageFactory imageFactory = new ImageFactory();
                            finalCurrentLabel.setIcon(imageFactory.createImageColor(selectedColor));
                            finalCurrentLabel.setColor(selectedColor);
                            //updateColor(finalCurrentLabel.getPosition());
                            // debug
                            System.out.println(finalCurrentLabel.getLabelColor());
                            System.out.println(finalCurrentLabel.getPosition()[1]);// affiche la position dans la combi
                        }
                    }
                });
                colorPanel.add(tabCombinationLabels[i][j]);
            }
            combinationsPanel.add(colorPanel);
        }
        JPanel selectPanel = new JPanel(new FlowLayout()); // panel de selection des couleurs (joueur)

        Model.Color[] allColors = masterController.getAllColors(); // obtient toutes les couleurs sous forme de string

        for(int j=0;j<masterController.getPawnAmount();j++){ // met l'ensemble des couleurs disponible
            //JLabel currentLabel = new JLabel(imageFactory.createImageIcon("img/colors/"+allColors[j]+".png", "color "+allColors[j]));

            colorButton currentButton = new colorButton(imageFactory.createImageIcon("img/colors/"+allColors[j].name()+".png", "color "+allColors[j].name()));
            currentButton.setContentAreaFilled(false);
            currentButton.setBorderPainted(false);
            currentButton.setColor(allColors[j]);
            currentButton.addActionListener(actionEvent -> {
                selectedColor = currentButton.getButtonColor(); // obtient la couleur du bouton
                SoundMaker snd = new SoundMaker(); // lance un son lorsque le bouton est pressé
                try {
                    snd.playClip("sounds/button.wav");
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                }
            });
            /*
            currentLabel.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    System.out.println("Changement couleur");
                    // met à jour l'image
                    currentLabel.setIcon(imageFactory.createImageIcon("img/colors/PINK.png", "color pink"));
                    currentLabel.getIcon();// utilise pour mettre dans la case clickable, trouver un moyen d'obtenir la couleur
                }
            });
            */
            tabSelectLabels[j] = currentButton;
            selectPanel.add(tabSelectLabels[j]);
        }

        selectPanel.setBackground(Color.lightGray);

        mainPanel.add(combinationsPanel,BorderLayout.CENTER);
        mainPanel.add(selectPanel,BorderLayout.SOUTH);

        /*
        Button btnAddScore = new Button("Add");
        btnAddScore.addActionListener(actionEvent -> {
            masterController.addScore(1);
        });

        gamePanel.add(btnAddScore, BorderLayout.SOUTH);

         */


        // Partie du controller joueur
        JPanel playerControl = new JPanel(new GridLayout(0,3)); // affichage des combinaisons
        Button btnSkip = new Button("Skip");
        Button btnSubmit = new Button("Submit");
        Button btnQuit = new Button("Quit");

        btnQuit.addActionListener(actionEvent -> {
            StartWindow startWindow = new StartWindow(masterController);
            this.dispose();
        }); // Ferme la fenêtre

        btnSubmit.addActionListener(actionEvent -> {
            sendCombination();
        });
        playerControl.add(btnSkip);
        playerControl.add(btnSubmit);
        playerControl.add(btnQuit);
        gamePanel.add(playerControl, BorderLayout.SOUTH);
        gamePanel.add(mainPanel,BorderLayout.CENTER);


        add(gamePanel);
    }
    public void selectColor(Model.Color color){
        this.selectedColor = color;
    }
    /* inutile, je croyais que les labels se mettaient pas à jour, car final
    public void updateColor(int[] position){
        tabCombinationLabels[position[0]][position[1]].setColor(selectedColor);
    }*/
    public void sendCombination(){ // envoie la combinaison au board via controller
        String[] combination = new String[tabCombinationLabels[currentAttempt].length];
        // vu que les labels vont de haut en bas, nécessité d'inversé, -1 pour rester dans l'index (0-n)
        int n = masterController.getAttemptAmount()-currentAttempt-1;
        for(int i=0;i<tabCombinationLabels[n].length;i++){
            System.out.println(tabCombinationLabels[n][i].getLabelColor());
            combination[i] = tabCombinationLabels[n][i].getLabelColor().name();
        }
        masterController.setPlayerCombination(combination);
    }
    @Override
    public void update(int score, int round, int attempt) {
        // Mise à jour du score affiché à chaque notification
        int updatedScore = score;//masterController.getScore();
        scoreLabel.setText("Score: " + updatedScore);

        int updatedRound = round;//masterController.getRoundAmount();
        roundLabel.setText("Round: " + updatedRound);

        currentAttempt = attempt;
        /*liste de choses que la fenetre devra update :

        - le nombre de manches restantes
        - le nombre de pions juste et mal placés
        - la combinaison secrète mais seulement à la fin
        - la combinaison du joueur

        puis on verra pour le reste
         */
    }
}
