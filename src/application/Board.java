package application;

import java.util.Random;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;

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
	
	
    public void reset() {
    	getChildren().clear();
    }
}
