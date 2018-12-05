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
	private static String bgmPath;
	
	Random ran = new Random();
	public Board() {
		setPadding(new Insets(15));
		setPrefWidth(850);
		loadPath();
	}
	
	public void addCookie(Console console,LevelPane lp) {
		Cookie cookie = new Cookie();
		cookie.setUpCookie(console,lp);
		getChildren().addAll(cookie);
    }
	
	public static void loadPath() {
		bgmPath = "res/Fluffing a Duck.mp3";
	}
    
    // Plays background music (Still not add music)
    public void startBgmLoop() {
		AudioClip bgm = new AudioClip(new File(bgmPath).toURI().toString());
		bgm.play();
    }
    public void reset() {
    	getChildren().clear();
    }
}
