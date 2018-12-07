package application;

import java.util.Random;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
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
	
	private static void loadPath() {
		bgmPath = ClassLoader.getSystemResource("audio/Fluffing a Duck.mp3").toString();;
	}
    
    // Plays background music
    public void startBgmLoop() {
		AudioClip bgm = new AudioClip(bgmPath);
		bgm.setCycleCount(AudioClip.INDEFINITE);
		bgm.play();
    }
    public void reset() {
    	getChildren().clear();
    }
}
