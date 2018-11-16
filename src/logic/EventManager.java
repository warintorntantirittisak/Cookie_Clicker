package logic;

public class EventManager {
	private Console console;
	private Board board;
	public EventManager(Board board){
		this.board=board;
	}
	public EventManager(Console console){
		this.console=console;
	}
	public void setClickerUpgrade(ClickingUpgrader cookieup) {
	cookieup.setOnAction((e-> {
		if(console.getScore()>=console.getCost()) {
		console.addScore(-console.getCost());
		cookieup.levelUp();
		console.setCost(cookieup.getLevel()*10) ;
		console.getLevellable().setText("Level: "+cookieup.getLevel()+" Cost to next level: "+console.getCost());
		}
	}));
	}
}
