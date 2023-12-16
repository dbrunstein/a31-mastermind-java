package Model;

import java.util.Random;

public class MasterUtils {
    public static Color randomColorRange(int maxRange){ // renvoie une couleur au ha
        Color[]colors=Color.values();
        Random random = new Random();
        return colors[random.nextInt(0,maxRange)];
    }
    public static Color randomColor(){ // renvoie une couleur au ha
        Color[]colors=Color.values();
        Random random = new Random();
        return colors[random.nextInt(colors.length)];
    }
}
