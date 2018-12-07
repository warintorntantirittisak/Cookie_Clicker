package application;

import java.io.IOException;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.AutoClickerBuyer;
import logic.ClickingUpgrader;

public class Console extends VBox {
	private int score;
	private Label scorelabel;
	private Label clickinglevellabel;
	private Label clickinglevelcostlabel;
	private Label autoclickerlabel;
	private Label autoclickercostlabel;
	private Label addlabel;
	private Label addcostlabel;
	private int clickingcost;
	private int autoclickercost;
	private int addcost;
	private ClickingUpgrader cookieup;
	private AutoClickerBuyer autoclickerup;
	private ClickingUpgrader addcookie;
	private static String upgradingPath;
	private static String consolebgPath;
	
	public Console() throws IOException {
		setAlignment(Pos.CENTER);
		setPrefWidth(250);
		setPadding(new Insets(15));
		setSpacing(10);
		setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		loadPath();
		
		Image consoleimage = new Image(consolebgPath);
		BackgroundSize backgroundSize = new BackgroundSize(1000, 500, true, true, true, false);
		BackgroundImage consolebackgroundImage = new BackgroundImage(consoleimage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
		Background consolebackground = new Background(consolebackgroundImage);
		setBackground(consolebackground);
		
		scorelabel = new Label("Score: "+ score);
		scorelabel.setFont(Font.font(40));
		scorelabel.setTextFill(Color.WHITE);
		
		cookieup = new ClickingUpgrader();
		autoclickerup = new AutoClickerBuyer();
		addcookie= new ClickingUpgrader();
		
		clickingcost = cookieup.getUpgradeCost();
		autoclickercost = autoclickerup.getCost();
		addcost = addcookie.getUpgradeCost();
		
		clickinglevellabel = new Label("Clicking Proficiency\n(Level "+cookieup.getLevel()+")");
		clickinglevelcostlabel = new Label("Cost to next level: "+clickingcost);
		autoclickerlabel = new Label("Auto-Clickers\n("+autoclickerup.getCount()+" clickers)");
		autoclickercostlabel = new Label("Cost to buy next Auto-Clickers: "+autoclickercost);
		addlabel = new Label("Number of Cookies\n("+addcookie.getLevel()+" cookies)");
		addcostlabel = new Label("Cost to add one more cookie: "+addcost);
		clickinglevellabel.setTextFill(Color.WHITE);
		clickinglevelcostlabel.setTextFill(Color.WHITE);
		autoclickerlabel.setTextFill(Color.WHITE);
		autoclickercostlabel.setTextFill(Color.WHITE);
		addlabel.setTextFill(Color.WHITE);
		addcostlabel.setTextFill(Color.WHITE);
		clickinglevellabel.setFont(Font.font(15));
		autoclickerlabel.setFont(Font.font(15));
		addlabel.setFont(Font.font(15));
		
		cookieup.setOnAction((e-> {
			if (score >= clickingcost) {
				AudioClip sound = new AudioClip(upgradingPath);
				sound.play();
				addScore(-clickingcost);
				cookieup.levelUp();
				clickingcost = cookieup.getUpgradeCost();
				clickinglevellabel.setText("Clicking Proficiency (Level "+cookieup.getLevel()+")");
				clickinglevelcostlabel.setText("Cost to next level: "+clickingcost);
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
				autoclickerlabel.setText("Auto-Clickers ("+autoclickerup.getCount()+" clickers)");
				autoclickercostlabel.setText("Cost to buy next Auto-Clickers: "+autoclickercost);
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
	
		getChildren().addAll(scorelabel,clickinglevellabel,clickinglevelcostlabel,cookieup,autoclickerlabel,autoclickercostlabel,autoclickerup,addlabel,addcostlabel,addcookie);
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
	public Label getLevelCostlabel() {
		return this.clickinglevelcostlabel;
	}
	public Label getCountlabel() {
		return this.autoclickerlabel;
	}
	public Label getCountCostlabel() {
		return this.autoclickercostlabel;
	}
	public Label getAddlabel() {
		return this.addlabel;
	}
	public Label getAddCostlabel() {
		return this.addcostlabel;
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
		score = 0;
		cookieup = new ClickingUpgrader();
		autoclickerup = new AutoClickerBuyer();
		addcookie= new ClickingUpgrader();
		clickingcost = cookieup.getUpgradeCost();
		autoclickercost = autoclickerup.getCost();
		addcost = addcookie.getUpgradeCost();
		clickinglevellabel.setText("Clicking Proficiency (Level "+cookieup.getLevel()+")");
		clickinglevelcostlabel.setText("Cost to next level: "+clickingcost);
		autoclickerlabel.setText("Auto-Clickers ("+autoclickerup.getCount()+" clickers)");
		autoclickercostlabel.setText("Cost to buy next Auto-Clickers: "+autoclickercost);
		addlabel.setText("Cookies ("+addcookie.getLevel()+" cookies)");
		addcostlabel.setText("Cost to add one more cookie: "+addcost);
	}
	private static void loadPath() {
		consolebgPath = ClassLoader.getSystemResource("image/consolebg.jpg").toString();
		upgradingPath = ClassLoader.getSystemResource("audio/upgrading.mp3").toString();
	}
}
