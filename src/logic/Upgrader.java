package logic;

public abstract class Upgrader {
	
	private int level;
	// Max level of all upgraders is 10.
	
	public Upgrader(int level) {
		if (level < 1 || level > 10) this.level = 1;
		else this.level = level;
	}
	
	public int getLevel() {return this.level;}
	
	public void setLevel(int level) {this.level = level;}
	
	public abstract void levelUp();

}
