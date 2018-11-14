package logic;

import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class EventManager {
	private int score;
	private Console console;
	private Board board;
	public EventManager(Board board,Console console) {
		this.score=board.getScore();
		this.board= board;
		this.console=console;
	}
	public int getScore() {
		return this.score;
	}
}
