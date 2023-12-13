package View;

import Controller.MasterController;
import Model.Settings;
import Model.HintDisplayMode;

import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JFrame {

    private MasterController masterController;
    private JComboBox<Integer> roundAmountComboBox;
    private JComboBox<Integer> pawnAmountComboBox;
    private JComboBox<Integer> combinationPawnAmountComboBox;
    private JComboBox<Integer> attemptAmountComboBox;
    private JComboBox<HintDisplayMode> displayModeComboBox;

    public SettingsWindow(MasterController masterController) {
        this.masterController = masterController;

        // Configuration initiale de la fenêtre
        setTitle("Paramètres du Mastermind");
        this.setResizable(true);
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        setLayout(new GridBagLayout()); // Utiliser GridBagLayout pour une meilleure organisation
        // Initialisation des composants
        //initComponents();
        // Création des JComboBox pour chaque paramètre
        roundAmountComboBox = new JComboBox<>(new Integer[]{3, 4, 5});
        pawnAmountComboBox = new JComboBox<>(new Integer[]{4, 5, 6, 7, 8});
        combinationPawnAmountComboBox = new JComboBox<>(new Integer[]{4, 5, 6});
        attemptAmountComboBox = new JComboBox<>(new Integer[]{10, 11, 12});
        displayModeComboBox = new JComboBox<>(HintDisplayMode.values());

        // Boutons
        JButton backButton = new JButton("Retour");
        JButton submitButton = new JButton("Soumettre");

        // Ajout des gestionnaires d'événements pour les boutons
        backButton.addActionListener(actionEvent -> {
            StartWindow startWindow = new StartWindow(masterController);
            this.dispose();
        }); // Ferme la fenêtre
        submitButton.addActionListener(actionEvent -> { applySettings();
            StartWindow startWindow = new StartWindow(masterController);
            this.dispose();
        }); // Change les settings

        // Ajout des composants à la fenêtre
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        add(new JLabel("Nombre de manches :"), gbc);
        add(roundAmountComboBox, gbc);
        add(new JLabel("Nombre de pions :"), gbc);
        add(pawnAmountComboBox, gbc);
        add(new JLabel("Nombre de pions dans la combinaison :"), gbc);
        add(combinationPawnAmountComboBox, gbc);
        add(new JLabel("Nombre de tentatives :"), gbc);
        add(attemptAmountComboBox, gbc);
        add(new JLabel("Mode d'affichage des indices :"), gbc);
        add(displayModeComboBox, gbc);

        JPanel mainPanel = new JPanel(new BorderLayout()); // contient affichage et selection
        mainPanel.add(backButton, BorderLayout.LINE_START);
        mainPanel.add(submitButton, BorderLayout.LINE_END);

        add(mainPanel, gbc);

        // Rendre la fenêtre visible
        setVisible(true);
    }

    private void applySettings() {
        // Création d'un objet Settings avec les valeurs sélectionnées
        Settings newSettings = new Settings();
        newSettings.setRoundAmount((Integer) roundAmountComboBox.getSelectedItem());
        newSettings.setPawnAmount((Integer) pawnAmountComboBox.getSelectedItem());
        newSettings.setCombinationPawnAmount((Integer) combinationPawnAmountComboBox.getSelectedItem());
        newSettings.setAttemptAmount((Integer) attemptAmountComboBox.getSelectedItem());
        newSettings.setDisplayMode((HintDisplayMode) displayModeComboBox.getSelectedItem());
        System.out.println("test 3");
        // Application des nouveaux paramètres via le MasterController
        masterController.setSettings(newSettings);
    }
}
