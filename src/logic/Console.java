package logic;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Console extends VBox {
	private int score;
	private ArrayList<String> highscores;
	private Label scorelabel;
	private Label highscorelabel;
	private Label clickinglevellabel;
	private Label autoclickerlabel;
	private Label addlabel;
	private int clickingcost;
	private int autoclickercost;
	private int addcost;
	private ClickingUpgrader cookieup;
	private AutoClickerBuyer autoclickerup;
	private ClickingUpgrader addcookie;
	private  String HIGHSCOREPATH = ("res/highscore.txt").toString();
	private  String UPGRADINGPATH = ("res/upgrading.mp3").toString();
 
	
	public Console() throws IOException {
		setAlignment(Pos.CENTER);
		setPrefWidth(250);
		setPadding(new Insets(15));
		setSpacing(10);
		setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		setBackground(new Background(new BackgroundFill(Color.IVORY, null, null)));
		scorelabel = new Label("Score: "+ score);
		scorelabel.setFont(Font.font(20));
		highscores = new ArrayList<String>();
		try {
			Scanner infile = new Scanner(new File(HIGHSCOREPATH));
			
			while (infile.hasNextLine()) {
				String line = infile.nextLine();
				this.highscores.add(line);
			}
			infile.close();
		} catch (FileNotFoundException f) {
			System.out.println(f.getMessage());
		}
		
		highscorelabel = new Label("Highscore Ranking" + "\n#1" + highscores.get(0) + "\n#2" + highscores.get(1) + "\n#3" + highscores.get(2));

		cookieup = new ClickingUpgrader();
		autoclickerup = new AutoClickerBuyer();
		addcookie= new ClickingUpgrader();
		
		clickingcost = cookieup.getUpgradeCost();
		autoclickercost = autoclickerup.getCost();
		addcost = addcookie.getUpgradeCost();
		
		clickinglevellabel = new Label("Clicking Level: "+cookieup.getLevel()+"\nCost to next level: "+clickingcost);
		autoclickerlabel = new Label("Number of Clickers: "+autoclickerup.getCount()+"\nCost to buy next Clickers: "+autoclickercost);
		addlabel = new Label("Number of Cookies: "+addcookie.getLevel()+"\nCost to add more: "+addcost);
		
		cookieup.setOnAction((e-> {
			if (score >= clickingcost) {
				try {
					Media sound = new Media(new File(UPGRADINGPATH).toURI().toString());
					MediaPlayer mediaPlayer = new MediaPlayer(sound);
					mediaPlayer.play();
				} catch (Exception f) {
		            f.printStackTrace();
				}
				addScore(-clickingcost);
				cookieup.levelUp();
				clickingcost = cookieup.getUpgradeCost();
				clickinglevellabel.setText("Clicking Level: "+cookieup.getLevel()+"\nCost to next level: "+clickingcost);
			}
		}));
		
		autoclickerup.setOnAction((e -> {
			if (score >= autoclickercost) {
				try {
					Media sound = new Media(new File(UPGRADINGPATH).toURI().toString());
					MediaPlayer mediaPlayer = new MediaPlayer(sound);
					mediaPlayer.play();
				} catch (Exception f) {
		            f.printStackTrace();
				}
				addScore(-autoclickercost);
				autoclickerup.getMoreClicker();
				autoclickerup.setCost(autoclickerup.getCount());
				autoclickercost = autoclickerup.getCost();
				autoclickerlabel.setText("Number of Clickers: "+autoclickerup.getCount()+"\nCost to buy next Clickers: "+autoclickercost);
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
		getChildren().addAll(scorelabel,highscorelabel,clickinglevellabel,cookieup,autoclickerlabel,autoclickerup,addlabel,addcookie);
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
}
