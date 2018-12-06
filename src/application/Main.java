package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import javafx.scene.layout.HBox;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	private int interval;
	private Console console;
	private Board board;
	private LevelPane levelpane;
	private WelcomePage menu ;
	private HighScorePage hspage ;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		menu = new WelcomePage();
		hspage =new HighScorePage();
		Scene firstscene = new Scene(menu, 1200,700);
		Scene hsscene = new Scene(hspage, 1200,700);
		primaryStage.setTitle("Cookie Clicker");
		primaryStage.setScene(firstscene);
		
		HBox root = new HBox();
		
		console = new Console();
		board = new Board();
		levelpane= new LevelPane();
		board.addCookie(console,levelpane);
		console.getAddUpgrade().setOnAction(e-> {
			if(console.getScore()>=console.getAddCost()) {
				board.addCookie(console,levelpane);
				console.getAddUpgrade().levelUp();
				console.addScore(-console.getAddCost());
				console.setAddCost(console.getAddUpgrade().getLevel()*50);
				console.getAddlabel().setText("Cookies ("+console.getAddUpgrade().getLevel());
				console.getAddCostlabel().setText("Cost to add one more cookie: "+console.getAddCost());
			}
		});
		
		Image image = new Image("bg.jpeg");
		// new BackgroundSize(width, height, widthAsPercentage, heightAsPercentage, contain, cover)
		// new BackgroundImage(image, repeatX, repeatY, position, size)
		BackgroundSize backgroundSize = new BackgroundSize(1000, 500, true, true, true, false);
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
		// new Background(images...)
		Background background = new Background(backgroundImage);
		
		root.setBackground(background);
		root.getChildren().addAll(console,board,levelpane);
		
		Label timeElapsed = new Label();
		timeElapsed.setTextFill(Color.WHITE);
		timeElapsed.setFont(Font.font(30));
		
		console.getChildren().add(timeElapsed);
		board.startBgmLoop();
		Scene scene = new Scene(root, 1200,700);
		
		menu.getStartButton().setOnAction(e->{
			primaryStage.setScene(scene);
			interval = 60;
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Time Out");
			alert.setHeaderText("Time Out");
			Thread timer = new Thread(() -> {
				while (interval > 0) {
					try {
						Thread.sleep(1000);
						Platform.runLater(()->timeElapsed.setText("Time Left: "+ interval));
						interval--;
						if (interval <= 10) timeElapsed.setTextFill(Color.RED);
					} catch (InterruptedException x) {
						x.printStackTrace();
						System.out.println("Stop Timer Thread");
						break;
					}
				}
				Platform.runLater(() -> {
					timeElapsed.setTextFill(Color.WHITE);
					alert.setContentText("Your Score : " + console.getScore());	
		            alert.showAndWait();
		            
		            if (console.getScore() > hspage.getFirst()) {
		            	hspage.setThird(hspage.getSecond());
		            	hspage.setSecond(hspage.getFirst());
		            	hspage.setFirst(console.getScore());
		            	hspage.refreshHighscores();
		            } else if (console.getScore() > hspage.getSecond()) {
		            	hspage.setThird(hspage.getSecond());
		            	hspage.setSecond(console.getScore());
		            	hspage.refreshHighscores();
		            } else if (console.getScore() > hspage.getThird()) {
		            	hspage.setThird(console.getScore());
		            	hspage.refreshHighscores();
		            }
		            
	            	primaryStage.setScene(hsscene);
		            console.reset();
		            levelpane.reset();
					board.reset();
					board.addCookie(console,levelpane);
				   });
			});
			timer.start();
		});
		menu.getHighscoresBtn().setOnAction(x->{primaryStage.setScene(hsscene);});
		hspage.getMenuBtn().setOnAction(x->{primaryStage.setScene(firstscene);});
		
		primaryStage.show();
		primaryStage.setResizable(false);
	}
	
	public static void main(String [] args) {
		launch(args);
	}

}
