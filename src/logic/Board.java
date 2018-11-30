package logic;

import java.io.File;
import java.util.Random;
import javafx.animation.PathTransition;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;


public class Board extends Pane {
	
	Random ran = new Random();
	public Board() {
		setPadding(new Insets(15));
	}
	
	public void addCookie(Console console) {
		Cookie cookie = new Cookie();
		cookie.setUpCookie(console);
		getChildren().addAll(cookie);
    }
    
    // Plays background music (Still not add music)
    public void startBgmLoop() {
    	try {
			Media sound = new Media(new File("bgmpath").toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.play();
		} catch (Exception f) {
            f.printStackTrace();
		}
    }
    public void reset() {
    	getChildren().clear();
    }
}
