package Game;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{

	private int IDNum;

	public Shape circle = null;

	ArrayList<Spike> spikeList = new ArrayList<Spike>(0);

	float waveNum;

	public boolean collides = false;

	private boolean killed = false; 

	private boolean escape = false;

	int faceY,faceX;
	int faceH,faceW;

	Image happyFace, dead;

	public Play(int id){
		IDNum = id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub

		happyFace = new Image("res/happyface.png");
		dead = new Image("res/Bloodsplatter.png");
		faceW = happyFace.getWidth();
		faceH = happyFace.getHeight();
		circle = new Circle(0,0,faceW/2);
		waveNum = 0;

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.fillRect(0, 0, 400, 600);
		if(!killed){
			happyFace.draw(faceX,faceY);
			circle.setLocation(faceX, faceY);
		}
		else{
			g.drawImage(dead, faceX-5, faceY-5);
		}
		if(!spikeList.isEmpty()){
			for(Spike s:spikeList){
				s.render(gc, g);
			}
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		Input i = gc.getInput();

		if(i.isKeyPressed(Input.KEY_ESCAPE)){
			if(!escape){
				gc.setMouseGrabbed(false);
				escape = true;
			}
			else{
				gc.setMouseGrabbed(true);
				escape = false;
			}
		}

		if(!escape && !killed){

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
			waveNum--;
		}

		if(!spikeList.isEmpty()){
			checkCollision();
		}
		if(waveNum < 0){
			waveGen((int)(Math.random()*6));
		}
	}

	public boolean getEscape(){
		return escape;
	}

	public boolean getKilled(){
		return killed;
	}

	public void checkCollision(){
		for(int i = 0; i < spikeList.size(); i++){
			if(circle.intersects(spikeList.get(i).getShape())){//if((faceX-faceW/3<=s.getX2() && faceX+faceW/3 > s.getX2() && faceY-faceH/3 <= s.getY2() && faceY+faceH/3 > s.getY2()) || (faceX-faceW/3<=s.getX2() && faceX+faceW/3 > s.getX2() && faceY-faceH/3 <= s.getY2() && faceY+faceH/3 > s.getY())){
				killed = true;
			}
			else if(spikeList.get(i).getY()>600 || spikeList.get(i).getX()>400 || spikeList.get(0).getX2() < 0){
				spikeList.remove(i);
				i--;
			}
		}
	}

	public void waveGen(int num) throws SlickException{
		if(num == 0){
			waveNum = 1500;
			for(int i = 1;i<=7; i++){
				spikeList.add(new Spike(this, i*50, i*(-40)));
			}
		}
		else if(num == 1){
			waveNum = 1500;
			int max = 8;
			for(int i = 7; i >= 1;i--){
				spikeList.add(new Spike(this, i*(50), (max-i)*(-40)));
			}
		}
		else if(num == 2){
			waveNum = 1000;
			for(int i = 1; i <= 7; i++){
				spikeList.add(new Spike(this, i*50, -50));
			}
		}
		else if(num == 3){
			waveNum = 1500;
			for(int i = 0;i<=6; i++){
				spikeList.add(new Spike(this, i*50, (i+1)*(-40)));
			}
		}
		else if(num == 4){
			waveNum = 1500;
			int max = 7;
			for(int i = 6; i >= 0;i--){
				spikeList.add(new Spike(this, i*(50), (max-i)*(-40)));
			}
		}
		else if(num == 5){
			waveNum = 1000;
			for(int i = 0; i <= 6; i++){
				spikeList.add(new Spike(this, i*50, -50));
			}
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return IDNum;
	}

}
