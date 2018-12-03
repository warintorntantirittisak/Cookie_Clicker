package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HighScorePage extends VBox {
	private Label highscoreheaderlabel;
	private Label highscorelistlabel;
	private static String highscorePath ="res/highscore.txt";;
	private ArrayList<String> highscores;
	private int min;
	public HighScorePage() {
		setSpacing(10);
		setAlignment(Pos.CENTER);
	highscores = new ArrayList<String>();
	
	try {
		Scanner infile = new Scanner(new File(highscorePath));
		while (infile.hasNextLine()) {
			String line = infile.nextLine();
			this.highscores.add(line);
		}
		infile.close();
	} catch (FileNotFoundException f) {
		System.out.println(f.getMessage());
	}
	min=Integer.parseInt(highscores.get(highscores.size()-1));
	highscoreheaderlabel = new Label("Highscore Ranking");
	highscoreheaderlabel.setFont(Font.font(20));
	highscoreheaderlabel.setTextFill(Color.BLACK);
	
	highscorelistlabel = new Label("#1: " + highscores.get(0) + "\n#2 :" + highscores.get(1) + "\n#3 :" + highscores.get(2));
	highscorelistlabel.setTextFill(Color.BLACK);
	getChildren().addAll(highscoreheaderlabel,highscorelistlabel);
	}
	public int getMin(){
		return this.min;
	}
}
