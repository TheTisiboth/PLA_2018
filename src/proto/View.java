package proto;

import java.awt.Color;
import java.awt.Graphics;

import edu.ricm3.game.GameView;
import proto.*;

public class View extends GameView {
	Color background = Color.ORANGE;
	Model m_model;
	Controller m_ctr;

	public View(Model m, Controller c) {
		m_model = m;
		m_ctr = c;
	}

	@Override
	protected void _paint(Graphics g) {

		
		Personnage p2 = m_model.getP2();
		Personnage p1 = m_model.getP1();
		p1.paint(g);
		p2.paint(g);
	}
}
