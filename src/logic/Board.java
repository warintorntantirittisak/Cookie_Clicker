package logic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;


public class Board extends HBox {
	private int score;
	public Board(Console console) {
		setPadding(new Insets(15));
		setSpacing(10);
		setAlignment(Pos.CENTER);
		Cookie cookie = new Cookie();
		setUpCookie(cookie,console);
		getChildren().addAll(cookie);
	}
	public void setUpCookie(Cookie cookie,Console console) {
		cookie.setOnAction(e-> {
			console.addScore(1);
		});
	}
	public int getScore() {
		return this.score;
	}
}
