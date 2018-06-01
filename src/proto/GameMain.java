package proto;

import java.awt.Dimension;

import edu.*;
import edu.ricm3.game.*;

public class GameMain {
	public static void main(String[] args) {

	    // construct the game elements: model, controller, and view.
	    Model model = new Model();
	    Controller controller = new Controller(model);
	    View view = new View(model,controller);

	    Dimension d = new Dimension(1200, 600);
	    new GameUI(model,view,controller,d);
}
}