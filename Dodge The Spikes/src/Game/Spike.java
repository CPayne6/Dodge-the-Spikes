package Game;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Spike{

	Image pic;
	
	private Shape s;

	float picH,picW;
	
	private static double increment = 0.2; 

	private float x,y,x2,y2;

	Play p;

	public Spike(Play play, int xPos, int yPos) throws SlickException{
		pic = new Image("res/Spikes.png");
		picH = pic.getHeight();
		picW = pic.getWidth();
		x = xPos;
		y = yPos;
		x2 = x + picW;
		y2 = y + picH;

		s = new Rectangle(x+5, y+5, picW-10, picH-10);
		
		p = play;

	}

	public void render(GameContainer gc, Graphics g) throws SlickException{
		pic.draw(x,y);
		s.setLocation(x+5,y+5);
		Input i = gc.getInput();
		if(!p.getEscape() && !p.getKilled()){
			y+=increment;
			y2 = y + picH;
			
		}
		
		if(!p.getEscape() && i.isKeyPressed(Input.KEY_SPACE)){
			y = 0-picH;
		}



		x2 = x + picW;
		y2 = y + picH;
	}

	public float getX(){
		return x;
	}

	public float getY(){
		return y;
	}

	public float getX2(){
		return x2;
	}

	public float getY2(){
		return y2;
	}
	
	public Shape getShape(){
		return s;
	}


}
