package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WelcomePage extends VBox{
	private Hyperlink start;
	private VBox playerName;
	private Hyperlink highscoresBtn;
	private Hyperlink exit;
	
	public WelcomePage() {
		setSpacing(10);
		setAlignment(Pos.CENTER);
		
		Label title = new Label("Cookie Clicker");
		title.setFont(new Font("Ravie", 100));
		title.setTextFill(Color.WHITE);
		title.setUnderline(true);
		
		start = new Hyperlink("",new ImageView(new Image("start.png")));
		start.setBorder(Border.EMPTY);
		
		playerName = new VBox();
		playerName.setAlignment(Pos.CENTER_LEFT);
		playerName.setPadding(new Insets(20,500,20,500));
		playerName.setSpacing(5);
		Label nameLabel = new Label("Player's Name: ");
		nameLabel.setFont(new Font("Ravie", 15));
		nameLabel.setTextFill(Color.WHITE);
		TextField nameTextfield = new TextField();
		nameTextfield.setPrefWidth(150);
		nameTextfield.setPromptText("Enter your name here.");
		playerName.getChildren().addAll(nameLabel,nameTextfield);
		
		highscoresBtn = new Hyperlink("",new ImageView(new Image("hs.png",150,150,true,true)));
		highscoresBtn.setBorder(Border.EMPTY);
		
		exit = new Hyperlink("",new ImageView(new Image("exit.png",150,150,true,true)));
		exit.setBorder(Border.EMPTY);
		
		HBox menubutton = new HBox();
		menubutton.setAlignment(Pos.CENTER);
		menubutton.setSpacing(10);
		menubutton.getChildren().addAll(highscoresBtn,start,exit);
	
		
		getChildren().addAll(title,menubutton,playerName);
		Image menuimage = new Image("bg.jpeg");
		BackgroundSize backgroundSize = new BackgroundSize(1000, 500, true, true, true, false);
		BackgroundImage menubackgroundImage = new BackgroundImage(menuimage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
		Background menubackground = new Background(menubackgroundImage);
		setBackground(menubackground);
	}
	
	public String getPlayerName() {
		String result = "";
		for (Node node: this.playerName.getChildren()) {
			if (node instanceof TextField) result = ((TextField) node).getText();
			if (result.equals("")) result="Unnamed Player";
		}
		
		return result.trim();
	}
	
	public Hyperlink getHighscoresBtn() {
		return this.highscoresBtn;
	}
	
	public Hyperlink getStartButton() {
		return this.start;
	}
	public Hyperlink getExitButton() {
		return this.exit;
	}
}

