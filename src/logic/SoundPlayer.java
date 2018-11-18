package logic;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 

// This class is used for play sound effects.
public class SoundPlayer {

	private Clip clip;
	private AudioInputStream audioInputStream;
    
    public SoundPlayer(String filePath) 
            throws UnsupportedAudioFileException, 
            IOException, LineUnavailableException  
        { 
            audioInputStream =  AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }
    
    public void play() { 
        clip.start();
    }

}
