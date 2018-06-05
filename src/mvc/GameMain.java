package mvc;

import java.awt.Dimension;
import java.awt.Toolkit;

import edu.*;
import edu.ricm3.game.*;

public class GameMain {
	public static void main(String[] args) {

	    // construct the game elements: model, controller, and view.
	    Model model = new Model();
	    Controller controller = new Controller(model);
	    View view = new View(model,controller);
//	    Dimension d = new Dimension(1200, 600);
	    Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
	    int longueur = tailleMoniteur.width * 2/3;
	    int hauteur = tailleMoniteur.height * 2/3;
	    Dimension d = new Dimension(longueur,hauteur);
	    new GameUI(model,view,controller,d);
}
}