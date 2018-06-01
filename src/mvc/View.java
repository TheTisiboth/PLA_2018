package mvc;

import java.awt.Color;
import java.awt.Graphics;

import edu.ricm3.game.GameView;
import mvc.*;
import physic.entity.Joueur;

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

		Joueur c = m_model.getCircle();
		Joueur c1 = m_model.getCircle2();
		c1.paint(g);
		c.paint(g);
	}
}
