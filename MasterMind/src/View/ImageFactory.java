package View;

import javax.swing.*;
import java.awt.*;

public class ImageFactory {
    private String filename;
    private static final String ERROR_PATH = "img/err.png";
    public ImageFactory(){
        this.filename = ERROR_PATH;
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
            return createImageIcon(ERROR_PATH,"Missing Image");
        }
    }
    public JLabel getLabel(){ // renvoie un label avec l'image contenu dedans
        JLabel label =new JLabel("",createImageIcon(this.filename,"ZIM"),JLabel.CENTER);
        label.setPreferredSize(new Dimension(250, 100));
        return label;
    }
    public Icon getIcon(){ // renvoie un icon avec l'image contenu dedans
        return createImageIcon(this.filename,"ZIM");
    }
}
