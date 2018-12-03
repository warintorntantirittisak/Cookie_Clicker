package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
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
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.AutoClickerBuyer;
import logic.ClickingUpgrader;

public class Console extends VBox {
	private int score;
	private ArrayList<String> highscores;
	private Label scorelabel;
	private Label highscoreheaderlabel;
	private Label highscorelistlabel;
	private Label clickinglevellabel;
	private Label autoclickerlabel;
	private Label addlabel;
	private int clickingcost;
	private int autoclickercost;
	private int addcost;
	private ClickingUpgrader cookieup;
	private AutoClickerBuyer autoclickerup;
	private ClickingUpgrader addcookie;
	private static String highscorePath;
	private static String upgradingPath;
	
	public Console() throws IOException {
		setAlignment(Pos.CENTER);
		setPrefWidth(250);
		setPadding(new Insets(15));
		setSpacing(10);
		setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		Image consoleimage = new Image("consolebg.jpg");
		BackgroundSize backgroundSize = new BackgroundSize(1000, 500, true, true, true, false);
		BackgroundImage consolebackgroundImage = new BackgroundImage(consoleimage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
		Background consolebackground = new Background(consolebackgroundImage);
		setBackground(consolebackground);
		
		loadPath();
		
		scorelabel = new Label("Score: "+ score);
		scorelabel.setFont(Font.font(20));
		scorelabel.setTextFill(Color.WHITE);
		
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
		highscoreheaderlabel = new Label("Highscore Ranking");
		highscoreheaderlabel.setFont(Font.font(20));
		highscoreheaderlabel.setTextFill(Color.WHITE);
		
		highscorelistlabel = new Label("#1: " + highscores.get(0) + "\n#2 :" + highscores.get(1) + "\n#3 :" + highscores.get(2));
		highscorelistlabel.setTextFill(Color.WHITE);
		
		cookieup = new ClickingUpgrader();
		autoclickerup = new AutoClickerBuyer();
		addcookie= new ClickingUpgrader();
		
		clickingcost = cookieup.getUpgradeCost();
		autoclickercost = autoclickerup.getCost();
		addcost = addcookie.getUpgradeCost();
		
		clickinglevellabel = new Label("Clicking Level: "+cookieup.getLevel()+"\nCost to next level: "+clickingcost);
		autoclickerlabel = new Label("Number of Auto-Clickers: "+autoclickerup.getCount()+"\nCost to buy next Auto-Clickers: "+autoclickercost);
		addlabel = new Label("Number of Cookies: "+addcookie.getLevel()+"\nCost to add more: "+addcost);
		clickinglevellabel.setTextFill(Color.WHITE);
		autoclickerlabel.setTextFill(Color.WHITE);
		addlabel.setTextFill(Color.WHITE);
		
		cookieup.setOnAction((e-> {
			if (score >= clickingcost) {
				AudioClip sound = new AudioClip(new File(upgradingPath).toURI().toString());
				sound.play();
				addScore(-clickingcost);
				cookieup.levelUp();
				clickingcost = cookieup.getUpgradeCost();
				clickinglevellabel.setText("Clicking Level: "+cookieup.getLevel()+"\nCost to next level: "+clickingcost);
			}
		}));
		
		autoclickerup.setOnAction((e -> {
			if (score >= autoclickercost) {
				AudioClip sound = new AudioClip(upgradingPath);
				sound.play();
				addScore(-autoclickercost);
				autoclickerup.getMoreClicker();
				autoclickerup.setCost(autoclickerup.getCount());
				autoclickercost = autoclickerup.getCost();
				autoclickerlabel.setText("Number of Auto-Clickers: "+autoclickerup.getCount()+"\nCost to buy next Auto-Clickers: "+autoclickercost);
			}
		}));
		
		Timer produce = new Timer();
		produce.scheduleAtFixedRate(new TimerTask() {
	        public void run() {
	        	Platform.runLater(() -> {
	                addScore(autoclickerup.getCount());
	            });
	        }
	    }, 1000, 1000);
	
		getChildren().addAll(scorelabel,highscoreheaderlabel,highscorelistlabel,clickinglevellabel,cookieup,autoclickerlabel,autoclickerup,addlabel,addcookie);
		setMinSize(200,200);
	}
	
	public void addScore(int n) {
		this.score+=n;
		scorelabel.setText("Score: "+score);
	}
	public int getScore() {
		return this.score;
	}
	public int getClickingCost() {
		return this.clickingcost;
	}
	public int getAutoClickerCost() {
		return this.autoclickercost;
	}
	public int getAddCost() {
		return this.addcost;
	}
	public Label getLevellabel() {
		return this.clickinglevellabel;
	}
	public Label getCountlabel() {
		return this.autoclickerlabel;
	}
	public Label getAddlabel() {
		return this.addlabel;
	}
	public ClickingUpgrader getClickingUpgrade() {
		return this.cookieup;
	}
	public AutoClickerBuyer getAutoClickerBuy() {
		return this.autoclickerup;
	}
	public ClickingUpgrader getAddUpgrade() {
		return this.addcookie;
	}
	public void setCost(int cost) {
		this.clickingcost=cost;
	}
	public void setAddCost(int cost) {
		this.addcost=cost;
	}
	public void reset() {
		score=0;
		cookieup = new ClickingUpgrader();
		autoclickerup = new AutoClickerBuyer();
		addcookie= new ClickingUpgrader();
		clickingcost = cookieup.getUpgradeCost();
		autoclickercost = autoclickerup.getCost();
		addcost = addcookie.getUpgradeCost();
		clickinglevellabel.setText("Clicking Level: "+cookieup.getLevel()+"\nCost to next level: "+clickingcost);
		autoclickerlabel.setText("Number of Auto-Clickers: "+autoclickerup.getCount()+"\nCost to buy next Auto-Clickers: "+autoclickercost);
		addlabel.setText("Number of Cookies: "+addcookie.getLevel()+"\nCost to add more: "+addcost);
	}
	public static void loadPath() {
		highscorePath ="res/highscore.txt";
		upgradingPath = "res/upgrading.mp3".toString();
	}
}
