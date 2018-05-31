package proto;

import edu.ricm3.game.GameModel;

public class Model extends GameModel {
	private Circle c;
	int nbCol;
	int nbLigne;
	
	public Model(){
		c = new Circle();
		nbCol = 32;
		nbLigne = 18;
	}

	@Override
	public void step(long now) {
		c.step(now);
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	public Circle getCircle() {
		return c;
	}
}
