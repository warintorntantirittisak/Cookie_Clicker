package logic;

// This upgrader increase the number of score that you gets when clicking the cookie.
public class ClickerUpgrader extends Upgrader{
	
	private int scorePerClick;
	private int upgradeCost;
	
	public ClickerUpgrader(int level) {
		super(level);
		this.scorePerClick = this.getLevel();
		this.setUpgradeCost(this.getLevel());
	}
	
	public void setUpgradeCost(int level) {
		int cost = 0;
		for (int i = 1; i <= level; ++i) {
			int x = (level+2) * 10;
			cost += x;
		}
		this.upgradeCost = cost;
	}
	
	public int getMultiplier() {return this.scorePerClick;}
	public int getUpgradeCost() {return this.upgradeCost;}
	
	@Override
	public void levelUp() {
		this.setLevel(this.getLevel()+1);
		this.setUpgradeCost(this.getLevel());
	}

}
