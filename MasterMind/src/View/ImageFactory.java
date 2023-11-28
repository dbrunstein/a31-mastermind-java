package View;

import javax.swing.*;
import java.awt.*;

public class ImageFactory {
    private String filename;
    private static final String error_path = "img/err.png";
    public ImageFactory(){
        this.filename = error_path;
    } // en absence de chemin, envoie l'image par défaut
    public ImageFactory(String filename){
        this.filename = filename;
    }
    public ImageIcon createImageIcon(String path,String description) { // crée l'image à partir du chemin
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return createImageIcon(error_path,"Missing Image");
        }
    }
    public JLabel getLabel(){ // renvoie un label avec l'image contenu dedans
        JLabel label =new JLabel("",createImageIcon(this.filename,"ZIM"),JLabel.CENTER);
        label.setPreferredSize(new Dimension(250, 100));
        return label;
    }
}
