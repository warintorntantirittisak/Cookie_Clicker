package logic;

import javafx.scene.control.Button;

// This class increases the number of score that you gets when clicking the cookie. Max level of clicking is 10.
public class ClickingUpgrader extends Button {
	
	private int level;
	private int upgradeCost;
	
	public ClickingUpgrader() {
		super("Upgrade");
		this.level = 1;
		setUpgradeCost(this.level);
	}
	
	public void setUpgradeCost(int level) {
		int cost = 20;
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
