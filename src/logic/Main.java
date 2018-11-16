package logic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		HBox root = new HBox();
		Console console = new Console();
		Board board = new Board();
		board.addCookie(console);
		console.getAddUpgrade().setOnAction(e-> {
			if(console.getScore()>=console.getAddCost()) {
			board.addCookie(console);
			console.getAddUpgrade().levelUp();
			console.addScore(-console.getAddCost());
			console.setAddCost(console.getAddUpgrade().getLevel()*50);
			console.getAddlabel().setText("Number of Cookies: "+console.getAddUpgrade().getLevel()+"\n Cost to next level: "+console.getAddCost());
			}
		});
		Image image = new Image("bg.jpeg");
		// new BackgroundSize(width, height, widthAsPercentage, heightAsPercentage, contain, cover)
		BackgroundSize backgroundSize = new BackgroundSize(1000, 500, true, true, true, false);
		// new BackgroundImage(image, repeatX, repeatY, position, size)
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
		// new Background(images...)
		Background background = new Background(backgroundImage);
		root.setBackground(background);
		root.getChildren().addAll(console,board);
		Scene scene = new Scene(root, 1000, 500);
		primaryStage.setTitle("Cookie Clicker");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
	}
	
	public static void main(String [] args) {
		launch(args);
	}

}
