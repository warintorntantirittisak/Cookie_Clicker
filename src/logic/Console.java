package logic;

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
<<<<<<< HEAD
	private Label label;
	private Label levellabel;
	private Label addlabel;
	private int cost;
	private int addcost;
	private ClickingUpgrader cookieup;
	private ClickingUpgrader addcookie;
=======
	private Label scoreLabel;
	private Label levelLabel;
	private int cost;
	private ClickingUpgrader cookieUp;
	
>>>>>>> 685dbb6fc59dea40e955cf6da6b5085cef2322e8
	public Console() {
		setAlignment(Pos.CENTER);
		setPrefWidth(250);
		setPadding(new Insets(15));
		setSpacing(10);
		setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			setBackground(new Background(new BackgroundFill(Color.IVORY, null, null)));
<<<<<<< HEAD
		label = new Label("Score: "+ score);
		label.setFont(Font.font(20));
		cookieup = new ClickingUpgrader(1);
		addcookie= new ClickingUpgrader(1) ;
		cost =cookieup.getLevel()*20;
		addcost=addcookie.getLevel()*50;
		levellabel = new Label("Level: "+cookieup.getLevel()+" Cost to next level: "+cost);
		addlabel = new Label("Number of Cookies: "+addcookie.getLevel()+"\n Cost to add more: "+addcost);
		cookieup.setOnAction((e-> {
			if(score>=cost) {
			addScore(-cost);
			cookieup.levelUp();
			cost =cookieup.getLevel()*20;
			levellabel.setText("Level: "+cookieup.getLevel()+" Cost to next level: "+cost);
			}
		}));
		getChildren().addAll(label,levellabel,cookieup,addlabel,addcookie);
=======
		scoreLabel = new Label("Score: "+ score);
		scoreLabel.setFont(Font.font(20));
		cookieUp = new ClickingUpgrader(1);
		cost = cookieUp.getUpgradeCost();
		levelLabel = new Label("Level: "+cookieUp.getLevel()+" Cost to next level: "+cost);
		cookieUp.setOnAction((e-> {
			if(score>=cost) {
				addScore(-cost);
				cookieUp.levelUp();
				cost = cookieUp.getUpgradeCost();
				levelLabel.setText("Level: "+cookieUp.getLevel()+" Cost to next level: "+cost);
			}
		}));
		getChildren().addAll(scoreLabel,cookieUp,levelLabel);
>>>>>>> 685dbb6fc59dea40e955cf6da6b5085cef2322e8
		setMinSize(200,200);
	}
	
	public void addScore(int n) {
		this.score+=n;
		scoreLabel.setText("Score: "+score);
	}
<<<<<<< HEAD
	public int getScore() {
		return this.score;
	}
	public int getCost() {
		return this.cost;
	}
	public int getAddCost() {
		return this.addcost;
	}
	public Label getLevellabel() {
		return this.levellabel;
	}
	public Label getAddlabel() {
		return this.addlabel;
	}
=======
	
>>>>>>> 685dbb6fc59dea40e955cf6da6b5085cef2322e8
	public ClickingUpgrader getUpgrade() {
		return this.cookieUp;
	}
	public ClickingUpgrader getAddUpgrade() {
		return this.addcookie;
	}
	public void setCost(int cost) {
		this.cost=cost;
	}
	public void setAddCost(int cost) {
		this.addcost=cost;
	}
}
