package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WelcomePage extends VBox{
	private Hyperlink start;
	private Button highscoresBtn;
	
	public WelcomePage() {
		setSpacing(10);
		setAlignment(Pos.CENTER);
		
		Label title = new Label("Cookie Clicker");
		title.setFont(new Font("Ravie", 100));
		title.setTextFill(Color.WHITE);
		title.setUnderline(true);
		
		start = new Hyperlink("",new ImageView(new Image("start.png")));
		start.setBorder(Border.EMPTY);
		
		highscoresBtn = new Button("Highscore Ranking");
		
		getChildren().addAll(title,start,highscoresBtn);
	
		Image menuimage = new Image("bg.jpeg");
		
		BackgroundSize backgroundSize = new BackgroundSize(1000, 500, true, true, true, false);
		BackgroundImage menubackgroundImage = new BackgroundImage(menuimage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
		Background menubackground = new Background(menubackgroundImage);
		setBackground(menubackground);
	}
	
	public Button getHighscoresBtn() {
		return this.highscoresBtn;
	}
	
	public Hyperlink getStartButton() {
		return this.start;
	}
}
