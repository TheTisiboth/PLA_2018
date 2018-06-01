package mvc;

import java.awt.Color;
import java.awt.Graphics;

import edu.ricm3.game.GameView;
import mvc.*;
import physic.entity.Joueur;
import physic.entity.Obstacle;

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

		Joueur c = m_model.getJ1();
		Joueur c1 = m_model.getJ2();
		Obstacle[] obstacles = m_model.getObstacle(); 
		
		for(int i=0; i<obstacles.length ;i++) {
			obstacles[i].paint(g);
		}
		c.paint(g);
		c1.paint(g);
	}
}
