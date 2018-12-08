package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.geometry.Pos;
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
	private ArrayList<Pair<String,Integer>> highscores;
	private Hyperlink MainMenu;
	private static ClassLoader classLoader;
	private static String bgPath;
	private static String highscorePath;
	private static String mainPath;
	
	public HighScorePage() {
		setSpacing(10);
		setAlignment(Pos.CENTER);
		
		loadPath();
		
		Image menuimage = new Image(bgPath);
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
	
		String hs = "";
		for (int i = 0; i < 3; i++) {
			hs += "#" + i + ": " + highscores.get(i).getKey() + "     " + highscores.get(i).getValue() + "\n";
		}
		highscorelistlabel = new Label(hs);
		highscorelistlabel.setTextFill(Color.WHITE);
		highscorelistlabel.setFont(Font.font(60));
		
		MainMenu = new Hyperlink("",new ImageView(new Image(mainPath,150,150,true,true)));
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
		String hs = "";
		for (int i = 0; i < 3; i++) {
			hs += "#" + i + ": " + highscores.get(i).getKey() + "     " + highscores.get(i).getValue() + "\n";
		}
		highscorelistlabel.setText(hs);
	}
	
	private static void loadPath() {
		try {
			bgPath = ClassLoader.getSystemResource("image/bg.jpeg").toString();
			highscorePath = ClassLoader.getSystemResource("data/highscore.txt").getFile();
			mainPath = ClassLoader.getSystemResource("image/main.png").toString();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
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
