package logic;


import application.Board;
import application.Console;
import application.LevelPane;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class Cookie extends ClickingObject {
	private static final String COOKIECLICKPATH = ClassLoader.getSystemResource("audio/cookie_click.mp3").toString();
	
	public Cookie() {
		super(new Image(ClassLoader.getSystemResource("image/Cookie.gif").toString()));
		oldx=75;
		oldy=10;
	}
	
	public Cookie(Image image) {
		super(image);
		oldx=75;
		oldy=10;
	} 
	@Override
	public void setUp(Console console,LevelPane lp) {
		setSpeed(2000-lp.getLevel()*100);
		setOnMouseClicked(e-> {
			try {
				AudioClip sound = new AudioClip(COOKIECLICKPATH);
				sound.play();
			} catch (Exception f) {
	            f.printStackTrace();
			}
			console.addScore(console.getClickingUpgrade().getLevel());
			if(lp.getLevel()>10) {
			lp.setBarProgress(0.02);
			}else {
			lp.setBarProgress(0.05);
			}
			if(lp.getBarProgress()>=1) {
				lp.setLevel(lp.getLevel()+1);
				lp.setLabel();
				lp.getBar().setProgress(0);
				lp.setBarProgress(-lp.getBarProgress());
				setSpeed(getSpeed()-100);
			}
		});
		setPath();
	}
	
	
}

