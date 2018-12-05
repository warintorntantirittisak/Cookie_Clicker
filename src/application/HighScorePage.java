package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HighScorePage extends VBox {
	private Label highscoreheaderlabel;
	private Label highscorelistlabel;
	private static String highscorePath ="res/highscore.txt";;
	private ArrayList<Integer> highscores;
	private Button menuBtn;
	
	public HighScorePage() {
		setSpacing(10);
		setAlignment(Pos.CENTER);
		Image menuimage = new Image("bg.jpeg");
		BackgroundSize backgroundSize = new BackgroundSize(1000, 500, true, true, true, false);
		BackgroundImage menubackgroundImage = new BackgroundImage(menuimage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
		Background menubackground = new Background(menubackgroundImage);
		setBackground(menubackground);
		highscores = new ArrayList<Integer>();
	
		try {
			Scanner infile = new Scanner(new File(highscorePath));
			while (infile.hasNextLine()) {
				String line = infile.nextLine();
				this.highscores.add(Integer.parseInt(line));
			}
			infile.close();
		} catch (FileNotFoundException f) {
			System.out.println(f.getMessage());
		}
	
		highscoreheaderlabel = new Label("Highscore Ranking");
		highscoreheaderlabel.setFont(Font.font(100));
		highscoreheaderlabel.setTextFill(Color.WHITE);
	
		highscorelistlabel = new Label("#1: " + highscores.get(0) + "\n#2 :" + highscores.get(1) + "\n#3 :" + highscores.get(2));
		highscorelistlabel.setTextFill(Color.WHITE);
		highscorelistlabel.setFont(Font.font(60));
		menuBtn = new Button("Main Menu");
		
		getChildren().addAll(highscoreheaderlabel,highscorelistlabel,menuBtn);
	}
	
	public void refreshHighscores() {
		try {
			PrintStream outfile = new PrintStream(new File(highscorePath));
			for (int i = 0; i < highscores.size(); i++) {
				outfile.println(highscores.get(i).toString());
			}
			outfile.close();
		} catch (FileNotFoundException f) {
			System.out.println(f.getMessage());
		}
		highscorelistlabel.setText("#1: " + highscores.get(0) + "\n#2 :" + highscores.get(1) + "\n#3 :" + highscores.get(2));
	}
	
	public Button getMenuBtn() {
		return this.menuBtn;
	}
	
	public int getThird(){
		return this.highscores.get(2);
	}
	
	public void setThird(int score) {
		this.highscores.remove(2);
		this.highscores.add(2, score);
	}
	
	public int getSecond() {
		return this.highscores.get(1);
	}
	
	public void setSecond(int score) {
		this.highscores.remove(1);
		this.highscores.add(1, score);
	}
	
	public int getFirst() {
		return this.highscores.get(0);
	}
	
	public void setFirst(int score) {
		this.highscores.remove(0);
		this.highscores.add(0, score);
	}
}
