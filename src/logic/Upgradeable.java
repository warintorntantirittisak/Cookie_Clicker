package logic;

public interface Upgradeable {
	public void setUpgradeCost(int level);
	public int getUpgradeCost();
	public void levelUp() ;
}
