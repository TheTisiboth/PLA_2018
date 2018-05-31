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

		Circle c = m_model.getCircle();
		Circle c1 = m_model.getCircle2();
		c1.paint(g);
		c.paint(g);
	}
}
