package View;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundMaker {
    public SoundMaker(){

    }
    public void playClip(String soundName) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        //String soundName = "sounds/pipe.wav";
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(soundName));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();

    }
}
