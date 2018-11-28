package logic;


import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;

public class Cookie extends Hyperlink {
	public int oldx;
	public  int oldy;
	public Cookie() {
		super("",new ImageView(new Image("Cookie.gif")));
		setBorder(Border.EMPTY);
		oldx=75;
		oldy=10;
	}

}

