package logic;

import javafx.geometry.Insets;
import javafx.scene.layout.TilePane;

public class Board extends TilePane {
	private int score;
	public Board(Console console) {
		setPadding(new Insets(15));
		setHgap(10);
		Cookie cookie = new Cookie();
		setUpCookie(cookie,console);
		getChildren().addAll(cookie);
	}
	public void addCookie(Console console) {
		Cookie cookie = new Cookie();
		setUpCookie(cookie,console);
		getChildren().addAll(cookie);
	}
	public void setUpCookie(Cookie cookie,Console console) {
		cookie.setOnAction(e-> {
			console.addScore(1);
			addCookie(console);
		});
	}
	public int getScore() {
		return this.score;
	}
}
