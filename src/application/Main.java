package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;

public class Main extends Application {
	private int interval;
	private Console console;
	private Board board;
	private LevelPane levelpane;
	private WelcomePage menu ;
	private HighScorePage hspage ;
	private Thread timer ;
	private static String bgPath;
	private static String bgmPath;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		menu = new WelcomePage();
		hspage = new HighScorePage();
		Scene firstscene = new Scene(menu, 1200,700);
		Scene hsscene = new Scene(hspage, 1200,700);
		primaryStage.setTitle("Cookie Clicker");
		primaryStage.setScene(firstscene);
		
		HBox root = new HBox();
		
		console = new Console();
		board = new Board();
		levelpane= new LevelPane();
		board.addCookie(console,levelpane);
		board.addBomb(console,levelpane);
		board.addBomb(console,levelpane);
		
		bgPath = ClassLoader.getSystemResource("image/bg.jpeg").toString();
		Image image = new Image(bgPath);
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
		
		startBgmLoop();
		Scene scene = new Scene(root, 1200,700);
		
		menu.getStartButton().setOnAction(e->{
			console.setAddUpgradeOnAction(board, levelpane);
			console.getChildren().add(timeElapsed);
			timeElapsed.setTextFill(Color.WHITE);
			primaryStage.setScene(scene);
			interval = 100;
			timeElapsed.setText("Time Left: "+ interval);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Time Out");
			alert.setHeaderText("Time Out");
			timer = new Thread(() -> {
				while (interval > 0) {
					try {
						console.updateButton();
						Thread.sleep(1000);
						Platform.runLater(()->timeElapsed.setText("Time Left: "+ interval));
						interval--;
						if (interval <= 10) timeElapsed.setTextFill(Color.RED);
					} catch (InterruptedException x) {
						break;
					}
				}
				Platform.runLater(() -> {
					if(interval<=0) {
					timeElapsed.setTextFill(Color.WHITE);
					int finalscore = console.getScore();
					alert.setContentText("Your Score : " + finalscore);	
		            alert.showAndWait();
		            if (finalscore > hspage.getFirst().getValue()) {
		            	hspage.setThird(hspage.getSecond());
		            	hspage.setSecond(hspage.getFirst());
		            	Pair<String,Integer> pair = new Pair<String,Integer>(menu.getPlayerName(),finalscore);
		            	hspage.setFirst(pair);
		            	hspage.refreshHighscores();
		            } else if (finalscore > hspage.getSecond().getValue()) {
		            	hspage.setThird(hspage.getSecond());
		            	Pair<String,Integer> pair = new Pair<String,Integer>(menu.getPlayerName(),finalscore);
		            	hspage.setSecond(pair);
		            	hspage.refreshHighscores();
		            } else if (finalscore > hspage.getThird().getValue()) {
		            	Pair<String,Integer> pair = new Pair<String,Integer>(menu.getPlayerName(),finalscore);
		            	hspage.setThird(pair);
		            	hspage.refreshHighscores();
		            }
		            
	            	primaryStage.setScene(hsscene);
		            console.reset();
		            levelpane.reset();
					board.reset();
					board.addCookie(console,levelpane);
					board.addBomb(console,levelpane);
					board.addBomb(console,levelpane);
					}
				   });
			});
			timer.start();
		});
		menu.getHighscoresBtn().setOnAction(x->{primaryStage.setScene(hsscene);});
		hspage.getMenuBtn().setOnAction(x->{primaryStage.setScene(firstscene);});
		levelpane.getMenuBtn().setOnAction(x->{
			timer.interrupt();
			primaryStage.setScene(firstscene);
			console.reset();
            levelpane.reset();
			board.reset();
			board.addCookie(console,levelpane);
			board.addBomb(console,levelpane);
			board.addBomb(console,levelpane);
			});
		menu.getExitButton().setOnAction(x->{ 
		Platform.exit();
        System.exit(0);
        });
		primaryStage.show();
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(x->{ 
		Platform.exit();
        System.exit(0);});
	}
	
	private static void loadPath() {
		bgmPath = ClassLoader.getSystemResource("audio/Fluffing a Duck.mp3").toString();;
	}
    
    // Plays background music
    public static void startBgmLoop() {
    	loadPath();
		AudioClip bgm = new AudioClip(bgmPath);
		bgm.setCycleCount(AudioClip.INDEFINITE);
		bgm.play();
    }
    
	public static void main(String [] args) {
		launch(args);
	}

}
