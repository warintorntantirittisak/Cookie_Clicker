package logic;

public class ClickerUpgrader extends Upgrader{
	
	private int multiplier;
	private int upgradeCost;
	
	public ClickerUpgrader(int level) {
		super(level);
		this.multiplier = this.getLevel();
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
	
	public int getMultiplier() {return this.multiplier;}
	public int getUpgradeCost() {return this.upgradeCost;}
	
	@Override
	public void levelUp() {
		this.setLevel(this.getLevel()+1);
		this.setUpgradeCost(this.getLevel());
	}

}
