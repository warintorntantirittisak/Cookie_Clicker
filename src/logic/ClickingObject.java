package logic;

import java.util.Random;

import application.Board;
import application.Console;
import application.LevelPane;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public abstract class ClickingObject extends ImageView{
	public int oldx;
	public int oldy;
	private int speed =2000;
	private Random ran = new Random();
	public ClickingObject() {};
	public ClickingObject(Image image) {
		super(image);
	};
	public abstract void setUp(Console console,LevelPane lp);
	public void setPath() {
		PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(javafx.util.Duration.millis(this.speed));
        pathTransition.setPath(createPath());
        pathTransition.setNode(this);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setOnFinished(e -> {
        	pathTransition.setDuration(javafx.util.Duration.millis(this.speed));
            pathTransition.setPath(createPath());
            pathTransition.play();
        });
        pathTransition.play();
	}

	private Path createPath() {
		int x = ran.nextInt(800-75  + 1)+75; // min=300 , max=600
		int y = ran.nextInt(675-75  + 1)+75;
		Path path = new Path();
		path.getElements().add(new MoveTo(oldx, oldy));
		this.oldx=x;
		this.oldy=y;
		path.getElements().add(new LineTo(x, y));
		return path;
	}
	public void setSpeed(int s) {
		this.speed=s;
	}
	public int getSpeed() {
		return this.speed;
	}
}
