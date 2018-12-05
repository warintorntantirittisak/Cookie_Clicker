package application;

import java.io.File;
import java.util.Random;

import javafx.animation.PathTransition;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import logic.Cookie;

public class Board extends Pane {
	
	Random ran = new Random();
	public Board() {
		setPadding(new Insets(15));
		setPrefWidth(850);
	}
	
	public void addCookie(Console console,LevelPane lp) {
		Cookie cookie = new Cookie();
		cookie.setUpCookie(console,lp);
		getChildren().addAll(cookie);
    }
    
    // Plays background music (Still not add music)
    public void startBgmLoop() {
		AudioClip bgm = new AudioClip("bgmpath");
		bgm.play();
    }
    public void reset() {
    	getChildren().clear();
    }
}
