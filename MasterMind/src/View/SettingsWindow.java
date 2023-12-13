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
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2)); // GridLayout pour organiser les composants

        // Initialisation des composants
        initComponents();

        // Ajout des composants à la fenêtre
        addComponents();

        // Rendre la fenêtre visible
        setVisible(true);
    }

    private void initComponents() {
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
        backButton.addActionListener(e -> this.dispose()); // Ferme la fenêtre
        submitButton.addActionListener(e -> applySettings());
    }

    private void addComponents() {
        // Ajout des composants au GridLayout
        add(new JLabel("Nombre de manches :"));
        add(roundAmountComboBox);
        add(new JLabel("Nombre de pions :"));
        add(pawnAmountComboBox);
        add(new JLabel("Nombre de pions dans la combinaison :"));
        add(combinationPawnAmountComboBox);
        add(new JLabel("Nombre de tentatives :"));
        add(attemptAmountComboBox);
        add(new JLabel("Mode d'affichage des indices :"));
        add(displayModeComboBox);

        add(new JLabel("")); // Espace vide pour l'alignement
        add(new JButton("Retour"));
        add(new JLabel(""));
        add(new JButton("Soumettre"));
    }

    private void applySettings() {
        // Création d'un objet Settings avec les valeurs sélectionnées
        Settings newSettings = new Settings();
        newSettings.setRoundAmount((Integer) roundAmountComboBox.getSelectedItem());
        newSettings.setPawnAmount((Integer) pawnAmountComboBox.getSelectedItem());
        newSettings.setCombinationPawnAmount((Integer) combinationPawnAmountComboBox.getSelectedItem());
        newSettings.setAttemptAmount((Integer) attemptAmountComboBox.getSelectedItem());
        newSettings.setDisplayMode((HintDisplayMode) displayModeComboBox.getSelectedItem());

        // Application des nouveaux paramètres via le MasterController
        masterController.setSettings(newSettings);
    }
}
