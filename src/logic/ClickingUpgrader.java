package logic;

// This upgrader increase the number of score that you gets when clicking the cookie.
public class ClickingUpgrader extends Upgrader{
	
	private int upgradeCost;
	
	public ClickingUpgrader(int level) {
		super(level, "Upgrade your Clicking");
		this.setUpgradeCost(this.getLevel());
	}
	
	public void setUpgradeCost(int level) {
		int cost = 20;
		for (int i = 2; i <= level; ++i) {
			int x = (level+1)*10;
			cost += x;
		}
		this.upgradeCost = cost;
	}
	
	public int getUpgradeCost() {return this.upgradeCost;}
	
	@Override
	public void levelUp() {
		this.setLevel(this.getLevel()+1);
		this.setUpgradeCost(this.getLevel());
	}

}
