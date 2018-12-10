package logic;

import javafx.scene.control.Button;

// This class gives you a cookie clicker that continuous generates 1 score. Max number of cookie clickers is 10. 
public class AutoClickerBuyer extends Button implements Upgradeable {
	
	private int count;
	private int cost;
	
	public AutoClickerBuyer() {
		super("Buy");
		this.count = 0;
		setUpgradeCost(0);
		setDisable(true);
		
	}
	
	public void setUpgradeCost(int count) {
		int cost = 30;
		if (count > 10) count = 1;
		for (int i = 1; i <= count; ++i) {
			cost += 10;
		}
		this.cost = cost;
	}
	public int getUpgradeCost() {return this.cost;}
	public int getCount() {return this.count;}
	
	public void levelUp() {
		if (this.count == 10) return;
		this.count = this.count + 1;
	}
	
	
}
