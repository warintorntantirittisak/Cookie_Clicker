package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

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
import javafx.util.Pair;

public class HighScorePage extends VBox {
	private Label highscoreheaderlabel;
	private Label highscorelistlabel;
	private static String highscorePath ="res/highscore.txt";;
	private ArrayList<Pair<String,Integer>> highscores;
	private Hyperlink MainMenu;
	
	public HighScorePage() {
		setSpacing(10);
		setAlignment(Pos.CENTER);
		Image menuimage = new Image("bg.jpeg");
		BackgroundSize backgroundSize = new BackgroundSize(1000, 500, true, true, true, false);
		BackgroundImage menubackgroundImage = new BackgroundImage(menuimage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
		Background menubackground = new Background(menubackgroundImage);
		setBackground(menubackground);
		highscores = new ArrayList<Pair<String,Integer>>(3);
	
		try {
			Scanner infile = new Scanner(new File(highscorePath));
			while (infile.hasNextLine()) {
				String name = infile.nextLine();
				int score = Integer.parseInt(infile.nextLine());
				Pair<String,Integer> pair = new Pair<String,Integer>(name,score);
				this.highscores.add(pair);
			}
			infile.close();
		} catch (FileNotFoundException f) {
			System.out.println(f.getMessage());
		}
	
		highscoreheaderlabel = new Label("Highscore Ranking");
		highscoreheaderlabel.setFont(Font.font(100));
		highscoreheaderlabel.setTextFill(Color.WHITE);
	
		highscorelistlabel = new Label("#1: " + highscores.get(0).getKey() + "     " + highscores.get(0).getValue() 
				+ "\n#2 :" + highscores.get(1).getKey() + "     " + highscores.get(1).getValue() 
				+ "\n#3 :" + highscores.get(2).getKey() + "     " + highscores.get(2).getValue());
		highscorelistlabel.setTextFill(Color.WHITE);
		highscorelistlabel.setFont(Font.font(60));
		MainMenu = new Hyperlink("",new ImageView(new Image("main.png",150,150,true,true)));
		MainMenu.setBorder(Border.EMPTY);
		
		getChildren().addAll(highscoreheaderlabel,highscorelistlabel,MainMenu);
	}
	
	public void refreshHighscores() {
		try {
			PrintStream outfile = new PrintStream(new File(highscorePath));
			for (int i = 0; i < highscores.size(); i++) {
				outfile.println(highscores.get(i).getKey());
				outfile.println(highscores.get(i).getValue());
			}
			outfile.close();
		} catch (FileNotFoundException f) {
			System.out.println(f.getMessage());
		}
		highscorelistlabel.setText("#1: " + highscores.get(0).getKey() + "     " + highscores.get(0).getValue()
				+ "\n#2 :" + highscores.get(1).getKey() + "     " + highscores.get(1).getValue() 
				+ "\n#3 :" + highscores.get(2).getKey() + "     "+ highscores.get(2).getValue());
	}
	
	public Hyperlink getMenuBtn() {
		return this.MainMenu;
	}
	
	public Pair<String,Integer> getThird(){
		return this.highscores.get(2);
	}
	
	public void setThird(Pair<String,Integer> pair) {
		this.highscores.remove(2);
		this.highscores.add(2, pair);
	}
	
	public Pair<String,Integer> getSecond() {
		return this.highscores.get(1);
	}
	
	public void setSecond(Pair<String,Integer> pair) {
		this.highscores.remove(1);
		this.highscores.add(1, pair);
	}
	
	public Pair<String,Integer> getFirst() {
		return this.highscores.get(0);
	}
	
	public void setFirst(Pair<String,Integer> pair) {
		this.highscores.remove(0);
		this.highscores.add(0, pair);
	}
}
