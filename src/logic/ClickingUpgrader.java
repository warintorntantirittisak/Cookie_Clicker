package logic;

// This upgrader increase the number of score that you gets when clicking the cookie.
public class ClickingUpgrader extends Upgrader{
	
	private int upgradeCost;
	private String name;
	
	public ClickingUpgrader(int level) {
		super(level);
		name="Upgrade Clicker";
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
	
	public int getUpgradeCost() {return this.upgradeCost;}
	
	@Override
	public void levelUp() {
		this.setLevel(this.getLevel()+1);
		this.setUpgradeCost(this.getLevel());
	}

	@Override
	public String getName() {
		return this.name;
	}

}