package logic;

import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;


public class Board extends Pane {
	private int score;
	private Cookie cookie;
    private static String ccPath = ClassLoader.getSystemResource("se/cookie_click.mp3").toString();
	
	Random ran = new Random();
	public Board() {
		setPadding(new Insets(15));
	}
	public void setUpCookie(Cookie cookie,Console console) {
		cookie.setOnAction(e-> {
			try {
				SoundPlayer clicking = new SoundPlayer(ccPath);
				clicking.play();
			} catch (Exception f) {
	            f.printStackTrace();
			}
			console.addScore(console.getClickingUpgrade().getLevel());
		});
	}
	public int getScore() {
		return this.score;
	}
	public void addCookie(Console console) {
		Cookie cookie = new Cookie();
		setPath(cookie);
		setUpCookie(cookie,console);
		getChildren().addAll(cookie);

    };
    public void setPath(Cookie cookie) {
    		PathTransition pathTransition = new PathTransition();

            pathTransition.setDuration(javafx.util.Duration.millis(600));
            pathTransition.setPath(createPath(cookie));
            pathTransition.setNode(cookie);
            pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition.setOnFinished(e -> {

                pathTransition.setPath(createPath(cookie));
                pathTransition.play();

            });
            pathTransition.play();
    }
    private Path createPath(Cookie cookie) {

        int x = ran.nextInt(700-75  + 1)+75; // min=300 , max=600
        int y = ran.nextInt(450-75  + 1)+75;
        Path path = new Path();
        path.getElements().add(new MoveTo(cookie.oldx, cookie.oldy));
        cookie.oldx=x;
        cookie.oldy=y;
        path.getElements().add(new LineTo(x, y));
        return path;

    }
}
