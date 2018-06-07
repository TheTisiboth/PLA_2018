package mvc;
import java.awt.Dimension;

import edu.ricm3.game.GameUI;
import fenetre.HomeWindow;

public class GameMain {
	public static void main(String[] args) {
		Dimension d = new Dimension(1200, 600);
		new GameUI(d);
	}
}