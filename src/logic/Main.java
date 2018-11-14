package logic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		HBox root = new HBox();
		Console console = new Console();
		Board board = new Board(console);
		root.getChildren().addAll(console,board);
		Scene scene = new Scene(root, 900, 500);
		primaryStage.setTitle("Cookie Clicker");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String [] args) {
		launch(args);
	}

}
