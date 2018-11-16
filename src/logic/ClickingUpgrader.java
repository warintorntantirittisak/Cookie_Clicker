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
	
	/* The cost for upgrade clicking to level n 
	 * C(n) = C(n-1) + (n+1)*10    ; 2 <= n <= 10
	 * C(1) = 20 */
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

	@Override
	public String getName() {
		return this.name;
	}

}
