package proto;

import edu.ricm3.game.GameModel;

public class Model extends GameModel {
	int nbCol;
	int nbLigne;
	
	public Model(){
		nbCol = 10;
		nbLigne = 10;
	}

	@Override
	public void step(long now) {
		
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	
}
