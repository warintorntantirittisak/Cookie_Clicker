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
	private Label label;
	private Label levellable;
	private int cost;
	private ClickingUpgrader cookieup;
	public Console() {
		setAlignment(Pos.CENTER);
		setPrefWidth(250);
		setPadding(new Insets(15));
		setSpacing(10);
		setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			setBackground(new Background(new BackgroundFill(Color.IVORY, null, null)));
		label = new Label("Score: "+ score);
		label.setFont(Font.font(20));
		cookieup = new ClickingUpgrader(1);
		cost =cookieup.getLevel()*10;
		levellable = new Label("Level: "+cookieup.getLevel()+" Cost to next level: "+cost);
		cookieup.setOnAction((e-> {
			if(score>=cost) {
			addScore(-cost);
			cookieup.levelUp();
			cost =cookieup.getLevel()*10;
			levellable.setText("Level: "+cookieup.getLevel()+" Cost to next level: "+cost);
			}
		}));
		getChildren().addAll(label,cookieup,levellable);
		setMinSize(200,200);
	}
	public void addScore(int n) {
		this.score+=n;
		label.setText("Score: "+score);
	}
	public ClickingUpgrader getUpgrade() {
		return this.cookieup;
	}
}
