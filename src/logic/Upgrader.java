package logic;

import javafx.scene.control.Button;

public abstract class Upgrader extends Button {
	
	private int level;
	// Max level of all upgraders is 10.
	
	public Upgrader(int level) {
		super("Upgrade");
		if (level < 1 || level > 10) this.level = 1;
		else this.level = level;
	}
	
	public int getLevel() {return this.level;}
	
	public void setLevel(int level) {this.level = level;}
	
	public abstract void levelUp();
	
	public abstract String getName() ;

}
