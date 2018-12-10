package logic;

import javafx.scene.control.Button;

public class AddCookie extends Button implements Upgradeable{
	private int level;
	private int upgradeCost;
	
	public AddCookie() {
		super("Add");
		this.level = 1;
		setUpgradeCost(this.level);
		setDisable(true);
	}
	
	public void setUpgradeCost(int level) {
		int cost = 40;
		if (level > 10) level = 1;
		for (int i = 2; i <= level; ++i) {
			int x = (level+1)*5;
			cost += x;
		}
		this.upgradeCost = cost;
	}
	public int getUpgradeCost() {return this.upgradeCost;}
	
	public int getLevel() {return this.level;}
	public void setLevel(int level) {this.level = level;}
	
	public void levelUp() {
		if (this.level == 10) return;
		this.setLevel(this.level+1);
		this.setUpgradeCost(this.level);
	}
}
