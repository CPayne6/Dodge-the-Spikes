package Game;



import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class SetupGame extends StateBasedGame{

	public static final String gameName = "Dodge The Spikes";
	
	public static final int windowHeight = 600;
	
	public static final int windowWidth = 400;
	
	public static final int menuID = 0;
	
	public static final int playID = 1;
	
	public SetupGame(String name) {
		super(name);
		this.addState(new Menu(menuID));
		this.addState(new Play(playID));
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer agc = new AppGameContainer(new SetupGame(gameName), windowWidth, windowHeight, false);
		agc.setMouseGrabbed(true);
		agc.start();
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menuID).init(gc, this);
		this.getState(playID).init(gc, this);
		this.enterState(menuID);
	}

}
