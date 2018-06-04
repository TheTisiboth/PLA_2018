package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

import edu.ricm3.game.GameView;
import mvc.*;
import no.physic.entity.Bonus;
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


		Case[][] plateau = m_model.getPlateau();
		
		Joueur c = m_model.getJ1();
		Joueur c1 = m_model.getJ2();
		
//		Obstacle[] obstacles = m_model.getObstacle(); 
//>>>>>>> dev
		
		for(int i =0 ; i<Options.nbCol; i++) {
			for(int k=0; k<Options.nbLigne; k++) {
				if(plateau[i][k].getRefresh()) {
					plateau[i][k].paint(g,i,k);
				}
			}
		}
//		Joueur c = m_model.getCircle();
//		Joueur c1 = m_model.getCircle2();
//		
//		Obstacle[] obstacles = m_model.getObstacle(); 
//		for(int i=0; i<obstacles.length ;i++) {
//			obstacles[i].paint(g);
//		}
//		
//		LinkedList<Bonus> listBonus = m_model.getListBonus();
//		LinkedList<Bonus> used = (LinkedList<Bonus>) listBonus.clone();
//		for(Iterator iterator = used.iterator();iterator.hasNext();) {
//			Bonus b = ((Bonus)iterator.next());
//			b.paint(g);
//			if(b.getDurationPop()<0) {
//				listBonus.remove(b);
//			}
//		}
//		
//		
//		c.paint(g);
//		c1.paint(g);
	}
}
