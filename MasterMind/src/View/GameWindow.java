package View;

import Controller.MasterController;
import Model.HintDisplayMode;
import Model.Observer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class GameWindow extends JFrame implements Observer {
    private JLabel scoreLabel; // contient le score
    private ColorButton[] tabSelectLabels; // contient les labels de selection du joueur
    private ColorLabel[][] tabCombinationLabels; // contient les labels des combinaisons affichées
    private ColorLabel[][] tabHintLabels;
    private String selectedColor;
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

        // tableaux des labels affichés
        tabCombinationLabels = new ColorLabel[masterController.getAttemptAmount()][masterController.getCombinationPawnAmount()];
        tabHintLabels = new ColorLabel[masterController.getAttemptAmount()][masterController.getCombinationPawnAmount()];
        tabSelectLabels = new ColorButton[masterController.getPawnAmount()];

        currentAttempt = 0;
        // Initialisation du panneau de jeu
        JPanel gamePanel = new JPanel(new BorderLayout());
        JPanel scoreAndRound = new JPanel(new FlowLayout());
        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        gamePanel.add(scoreLabel, BorderLayout.NORTH);
        roundLabel = new JLabel("Round: 1", SwingConstants.CENTER);
        scoreAndRound.add(scoreLabel);
        scoreAndRound.add(roundLabel);
        gamePanel.add(scoreAndRound, BorderLayout.NORTH);

        // Image factory pour créer les images des couleurs
        ImageFactory imageFactory = new ImageFactory();

        // panels contenant les labels des combinaisons et de selection
        JPanel mainPanel = new JPanel(new BorderLayout()); // contient affichage, indices et selection
        JPanel combinationsPanel = new JPanel(new GridLayout(0,1)); // affichage des combinaisons
        for(int i=0;i<masterController.getAttemptAmount();i++){ // affichage des combinaisons
            JPanel colorPanel = new JPanel(new FlowLayout()); // une combinaison

            for(int j=0;j<masterController.getCombinationPawnAmount();j++){ // met les combinaisons "à zéro"
                int[] position = {i,j};// i attempt, j position dans la combinaison
                tabCombinationLabels[i][j] = new ColorLabel(imageFactory.createImageIcon("img/colors/PINK.png", "color pink"),position);
                ColorLabel currentLabel = tabCombinationLabels[i][j];
                ColorLabel finalCurrentLabel = currentLabel;
                currentLabel.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent me) {
                        // met à jour l'image
                        int attemptNb = masterController.getAttemptAmount() - finalCurrentLabel.getPosition()[0] -1; // -1 car va de 0 à n
                        if(selectedColor!=null && attemptNb==currentAttempt){ // s'exécute si c'est la tentative actuelle
                            ImageFactory imageFactory = new ImageFactory();
                            finalCurrentLabel.setColor(selectedColor);
                        }
                    }
                });
                colorPanel.add(tabCombinationLabels[i][j]);
            }
            combinationsPanel.add(colorPanel);
        }
        JPanel hintPanel = new JPanel(new GridLayout(0,1)); // affichage des indices
        for(int i=0;i<masterController.getAttemptAmount();i++){
            JPanel hintLine = new JPanel(new FlowLayout()); // une ligne d'indice
            for(int j=0;j<masterController.getCombinationPawnAmount();j++){ // met les indices "à zéro"
                int[] position = {i,j};
                tabHintLabels[i][j] = new ColorLabel(imageFactory.createImageIcon("img/colors/BEIGE.png", "color white"),position);
                hintLine.add(tabHintLabels[i][j]);
            }
            hintPanel.add(hintLine);
        }

        JPanel selectPanel = new JPanel(new FlowLayout()); // panel de selection des couleurs (joueur)

        String[] allColors = masterController.getAllColorsString(); // obtient toutes les couleurs sous forme de string

        for(int j=0;j<masterController.getPawnAmount();j++){ // met l'ensemble des couleurs disponible
            ColorButton currentButton = new ColorButton(imageFactory.createImageIcon("img/colors/"+allColors[j]+".png", "color "+allColors[j]));
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
            tabSelectLabels[j] = currentButton;
            selectPanel.add(tabSelectLabels[j]);
        }
        selectPanel.setBackground(Color.lightGray);
        JPanel centerPanel = new JPanel(new GridLayout(1,0));
        centerPanel.add(combinationsPanel);
        centerPanel.add(hintPanel);
        mainPanel.add(centerPanel,BorderLayout.CENTER);

        mainPanel.add(selectPanel,BorderLayout.SOUTH);

        // Partie du controller joueur
        JPanel playerControl = new JPanel(new GridLayout(0,3)); // affichage des combinaisons
        Button btnSkip = new Button("Skip");
        Button btnSubmit = new Button("Submit");
        Button btnQuit = new Button("Quit");

        btnQuit.addActionListener(actionEvent -> {
            StartWindow startWindow = new StartWindow(masterController);
            this.dispose();
        }); // Ferme la fenêtre

        btnSkip.addActionListener(actionEvent -> {
            skip();
        });

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
    public void sendCombination(){ // envoie la combinaison au board via controller
        String[] combination = new String[tabCombinationLabels[currentAttempt].length];
        // vu que les labels vont de haut en bas, nécessité d'inversé, -1 pour rester dans l'index (0-n)
        int n = masterController.getAttemptAmount()-currentAttempt-1;
        for(int i=0;i<tabCombinationLabels[n].length;i++){
            if(tabCombinationLabels[n][i].getLabelColor()!= null){
                combination[i] = tabCombinationLabels[n][i].getLabelColor();
            }
            else{
                break;
            }

        }
        masterController.setPlayerCombination(combination);
        masterController.play(); // joue une manche
        currentAttempt++;
    }
    public void skip(){ // envoie la combinaison au board via controller
        String[] combination = new String[tabCombinationLabels[currentAttempt].length];
        // vu que les labels vont de haut en bas, nécessité d'inversé, -1 pour rester dans l'index (0-n)
        int n = masterController.getAttemptAmount()-currentAttempt-1;
        for(int i=0;i<tabCombinationLabels[n].length;i++){
            tabCombinationLabels[n][i].setColor("WHITE");
            combination[i] = "BEIGE";
        }
        masterController.setPlayerCombination(combination);
        masterController.play(); // joue une manche
        currentAttempt++;
    }
    public void reset(){ // reset la manche
        int n = masterController.getAttemptAmount();
        for(int i=0;i<n;i++){
            for(int j=0;j<tabCombinationLabels[i].length;j++){
                tabCombinationLabels[i][j].setColor("PINK");
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<tabHintLabels[i].length;j++){
                tabHintLabels[i][j].setColor("BEIGE");
            }
        }
        this.currentAttempt = -1; // il s'incrémente tout seul, donc gardé à -1 pour avoir 0 (à revoir)
    }
    public void endGame(int score){
        EndGameWindow endGameWindow = new EndGameWindow(masterController, score);
        this.dispose();
    }
    @Override
    public void update(int score, int round, int attempt, Boolean hasEnded) {
        // Mise à jour du score affiché à chaque notification
        int updatedScore = score;//masterController.getScore();
        scoreLabel.setText("Score: " + updatedScore);
        roundLabel.setText("Round: "+ (round+1)); // () pour éviter d'append au string
        currentAttempt = attempt;
        reset();
        if(hasEnded){
            endGame(updatedScore);
        }
    }

    @Override
    public void updateHints(ArrayList<String> hints) {
        if(!hints.isEmpty()){
            /*if(masterController.getDisplayMode() == HintDisplayMode.CLASSIC || masterController.getDisplayMode() == HintDisplayMode.DIGITAL){
                // fait en catastrophe
                // version classique
                ArrayList<String> blackTab = new ArrayList<String>();
                for (int i=0;i<hints.size();i++) { // version classique
                    if(hints.get(i).equals("BLACK")){
                        blackTab.add(hints.get(i));
                    }
                }
                for(String i : blackTab){
                    hints.remove(i);
                }
                for (int i=0;i<blackTab.size();i++) {
                    tabHintLabels[tabHintLabels.length-currentAttempt-1][i].setColor(blackTab.get(i));
                }
                for (int i=blackTab.size();i<hints.size()+blackTab.size()-2;i++) {
                    tabHintLabels[tabHintLabels.length-currentAttempt-1][i].setColor(hints.get(i));
                }

            }
            else{*/
                for (int i=0;i<hints.size();i++) { // version facile
                    tabHintLabels[tabHintLabels.length-currentAttempt-1][i].setColor(hints.get(i));
                }
            //}

        }
    }

    @Override
    public void updateScores(MasterController masterController) {

    }
}
