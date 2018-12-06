package application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LevelPane extends VBox {
	private int level;
	private Label levellabel;
	private ProgressBar levelbar ;
	private double progress;
	public LevelPane() {
		setSpacing(10);
		setAlignment(Pos.CENTER);
		setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		Image consoleimage = new Image("consolebg.jpg");
		BackgroundSize backgroundSize = new BackgroundSize(1000, 500, true, true, true, false);
		BackgroundImage consolebackgroundImage = new BackgroundImage(consoleimage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
		Background consolebackground = new Background(consolebackgroundImage);
		setBackground(consolebackground);
		this.level=1;
		progress=0;
		levellabel = new Label("Level :"+level);
		levellabel.setTextFill(Color.WHITE);
		levelbar = new  ProgressBar();
		levelbar.setProgress(progress);
		getChildren().addAll(levellabel,levelbar);
	}
	public ProgressBar getBar() {
		return this.levelbar;
	}
	public void setLabel() {
		levellabel.setText("Level :"+level);
	}
	public void setLevel(int l) {
		this.level=l;
	}
	public int getLevel() {
		return this.level;
	}
	public void setBarProgress(double p) {
		progress+=p;
		levelbar.setProgress(progress);
	}
	public double getBarProgress() {
		return this.progress;
	}
	public void reset() {
		level=1;
		setLabel();
		setBarProgress(-progress);
	}
}
