package logic;


import java.io.File;
import java.util.Random;

import application.Console;
import application.LevelPane;
import javafx.animation.PathTransition;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class Cookie extends ImageView {
	public int oldx;
	public  int oldy;
	private int speed =2000;
	Random ran = new Random();
	private static final String COOKIECLICKPATH ="res/cookie_click.mp3";
	public Cookie() {
		super(new Image(ClassLoader.getSystemResource("Cookie.gif").toString()));
		oldx=75;
		oldy=10;
	}
	public void setUpCookie(Console console,LevelPane lp) {
		setOnMouseClicked(e-> {
			try {
				Media sound = new Media(new File(COOKIECLICKPATH).toURI().toString());
				MediaPlayer mediaPlayer = new MediaPlayer(sound);
				mediaPlayer.play();
			} catch (Exception f) {
	            f.printStackTrace();
			}
			console.addScore(console.getClickingUpgrade().getLevel());
			lp.setBarProgress(0.05);
			if(lp.getBarProgress()>=1) {
				lp.setLevel(lp.getLevel()+1);
				lp.setLabel();
				lp.getBar().setProgress(0);
				lp.setBarProgress(-lp.getBarProgress());
				this.speed-=100;
			}
		});
		setPath();
	}
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
    int x = ran.nextInt(850-75  + 1)+75; // min=300 , max=600
    int y = ran.nextInt(700-75  + 1)+75;
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
}

