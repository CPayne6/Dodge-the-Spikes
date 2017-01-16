package Game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{

	int faceY,faceX;
	int faceH,faceW;
	
	String mouse = "";
	
	int playX1 = 90, playX2 = playX1 + 200, playY1 = 400, playY2 = playY1 + 40;
	int exitX1 = 90, exitX2  = exitX1 + 200, exitY1 = 450,exitY2 = exitY1 + 40;
	Image play, exit, happyFace;
	
	private int IDNum;

	public Menu(int id){
		IDNum = id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		happyFace = new Image("res/happyface.png");
		faceW = happyFace.getWidth();
		faceH = happyFace.getHeight();
		play = new Image("res/playNow.png");
		exit  = new Image("res/exitGame.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.fillRect(0, 0, 400, 600);
		g.setColor(Color.black);
		g.drawString("Welcome To Don't Touch The Spikes!!!", 40, 100);
		play.draw(playX1, playY1);
		exit.draw(exitX1, exitY1);
		g.drawString(mouse, 0, 50);
		happyFace.draw(faceX,faceY);
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		Input input = gc.getInput();
		if(Mouse.getX() < faceW/2){
			faceX = 0;
		}
		else if(Mouse.getX() > SetupGame.windowWidth - faceW/2){
			faceX = SetupGame.windowWidth - faceW;
		}
		else{
			faceX = Mouse.getX() - faceW/2;
		}
		
		if(Mouse.getY() < faceH/2){
			faceY = SetupGame.windowHeight - faceH;
		}
		else if(Mouse.getY() > SetupGame.windowHeight - faceH/2){
			faceY = 0;
		}
		else{
			faceY = SetupGame.windowHeight - Mouse.getY()-faceH/2;
		}
		
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			int mX = Mouse.getX();
			int mY = SetupGame.windowHeight - Mouse.getY();
			if(mX < playX2 && mX>playX1 && mY<playY2 && mY>playY1){
				sbg.enterState(SetupGame.playID);
			}
			else if(mX < exitX2 && mX>exitX1 && mY<exitY2 && mY>exitY1){
				gc.exit();
			}
			mouse = "mouse position x: "+mX+" y: "+mY;
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return IDNum;
	}

}
