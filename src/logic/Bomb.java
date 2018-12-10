package logic;


import application.Console;
import application.LevelPane;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;



public class Bomb extends ClickingObject {
	private static final String BOMBCLICKPATH = ClassLoader.getSystemResource("audio/Bomb.mp3").toString();
	public Bomb() {
		super(new Image(ClassLoader.getSystemResource("image/Bomb.png").toString(),150,150,false,false));
	}

	@Override
	public void setUp(Console console,LevelPane lp) {
		setSpeed(2000-lp.getLevel()*100);
		
		setOnMouseClicked(e-> {
			console.addScore(-console.getScore());
			try {
				AudioClip sound = new AudioClip(BOMBCLICKPATH);
				sound.play();
			} catch (Exception f) {
	            f.printStackTrace();
			}
		});
		setPath();
	}

}
