package proto;

import java.awt.Color;

import edu.ricm3.game.GameModel;

public class Model extends GameModel {
	private Circle c;
	private Circle c1;
	int nbCol;
	int nbLigne;
	Case mat[][];
	
	public Model(){
		c = new Circle(0,0,Color.BLUE);
		c1 = new Circle(2,2,Color.RED);
		nbCol = 10;
		nbLigne = 10;
		mat= new Case[18][32];
	}

	@Override
	public void step(long now) {
		c.step(now);
		c1.step(now);
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	public Circle getCircle() {
		return c;
	}

	public Circle getCircle2() {
		return c1;
	}
}
