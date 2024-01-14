package View;

import Controller.MasterController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AskNameWindow extends JFrame {

    public AskNameWindow(MasterController masterController) {
        // Configurer la fenêtre
        this.setTitle("Enter Player Name");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        // Créer un champ de texte pour entrer le nom
        JTextField nameField = new JTextField();
        nameField.setBounds(50, 50, 200, 30);
        this.add(nameField);

        // Créer un bouton pour soumettre
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(100, 100, 100, 30);
        this.add(submitButton);

        // Ajouter un écouteur d'événements au bouton
        submitButton.addActionListener(actionEvent -> {
            String playerName = nameField.getText();
            System.out.println("Player Name: " + playerName);
            masterController.setPlayerName(playerName);

            // Fermer la fenêtre après avoir cliqué sur Submit
            this.dispose();

            // Créer et afficher la fenêtre de démarrage
            try {
                StartWindow startWindow = new StartWindow(masterController);
                startWindow.setVisible(true);
                //EndGameWindow endGameWindow = new EndGameWindow(masterController, 1000);
                //endGameWindow.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        // Afficher la fenêtre
        this.setLocationRelativeTo(null); // Pour centrer la fenêtre
        this.setVisible(true);
    }
}
