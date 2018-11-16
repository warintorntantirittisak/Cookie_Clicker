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
	private Label scoreLabel;
	private Label levelLabel;
	private int cost;
	private ClickingUpgrader cookieUp;
	
	public Console() {
		setAlignment(Pos.CENTER);
		setPrefWidth(250);
		setPadding(new Insets(15));
		setSpacing(10);
		setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			setBackground(new Background(new BackgroundFill(Color.IVORY, null, null)));
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
		setMinSize(200,200);
	}
	
	public void addScore(int n) {
		this.score+=n;
		scoreLabel.setText("Score: "+score);
	}
	
	public ClickingUpgrader getUpgrade() {
		return this.cookieUp;
	}
}
