package logic;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Console extends VBox {
	private int score;
	private Label scorelabel;
	private Label clickinglevellabel;
	private Label autoclickerlabel;
	private Label addlabel;
	private int clickingcost;
	private int autoclickercost;
	private int addcost;
	private ClickingUpgrader cookieup;
	private AutoClickerBuyer autoclickerup;
	private ClickingUpgrader addcookie;
	
	public Console() {
		setAlignment(Pos.CENTER);
		setPrefWidth(250);
		setPadding(new Insets(15));
		setSpacing(10);
		setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		setBackground(new Background(new BackgroundFill(Color.IVORY, null, null)));
		
		scorelabel = new Label("Score: "+ score);
		scorelabel.setFont(Font.font(20));
		
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
				addScore(-clickingcost);
				cookieup.levelUp();
				clickingcost = cookieup.getUpgradeCost();
				clickinglevellabel.setText("Clicking Level: "+cookieup.getLevel()+"\nCost to next level: "+clickingcost);
			}
		}));
		
		autoclickerup.setOnAction((e -> {
			if (score >= autoclickercost) {
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
		getChildren().addAll(scorelabel,clickinglevellabel,cookieup,autoclickerlabel,autoclickerup,addlabel,addcookie);
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
