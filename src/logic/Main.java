package logic;

import java.io.IOException;


import javafx.application.Application;
import javafx.application.Platform;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	private int interval;
	private Console console;
	private Board board;
	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox menu = new VBox();
		menu.setSpacing(10);
		Label title = new Label("Cookie Clicker");
		title.setFont(new Font("Ravie", 100));
		title.setTextFill(Color.WHITE);
		title.setUnderline(true);
		Hyperlink start = new Hyperlink("",new ImageView(new Image("start.png")));
		start.setBorder(Border.EMPTY);
		menu.getChildren().addAll(title,start);
		menu.setAlignment(Pos.CENTER);
		Image menuimage = new Image("bg.jpeg");
		BackgroundSize backgroundSize = new BackgroundSize(1000, 500, true, true, true, false);
		BackgroundImage menubackgroundImage = new BackgroundImage(menuimage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
		Background menubackground = new Background(menubackgroundImage);
		menu.setBackground(menubackground);
		Scene firstscene = new Scene(menu, 1000, 500);
		primaryStage.setTitle("Cookie Clicker");
		primaryStage.setScene(firstscene);
		HBox root = new HBox();
		console = new Console();
		board = new Board();
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
		// new BackgroundImage(image, repeatX, repeatY, position, size)
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
		// new Background(images...)
		Background background = new Background(backgroundImage);
		root.setBackground(background);
		root.getChildren().addAll(console,board);
		Label timeElapsed = new Label();
		timeElapsed.setTextFill(Color.WHITE);
		console.getChildren().add(timeElapsed);
		Scene scene = new Scene(root, 1000, 500);
		start.setOnAction(e->{
			primaryStage.setScene(scene);
			interval =100;
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Time Out");
			alert.setHeaderText("Time Out");
			Thread timer = new Thread(() -> {
				while(interval>0){
				try {
				Thread.sleep(1000);
				Platform.runLater(()->timeElapsed.setText("Time Left: "+interval));
	            interval--;
				} catch (InterruptedException x) {
				x.printStackTrace();
				System.out.println("Stop Timer Thread");
				break;
				}
				}
				Platform.runLater(() -> {
					alert.setContentText("Your Score : "+console.getScore());	
		            alert.showAndWait();
		            primaryStage.setScene(firstscene);
		            console.reset();
					board.reset();
					board.addCookie(console);
				    }
				);
				});
			timer.start();
		});
		primaryStage.show();
		primaryStage.setResizable(false);
	}
	
	public static void main(String [] args) {
		launch(args);
	}

}
