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
		
		for(int i =0 ; i<Options.nbCol; i++) {
			for(int k=0; k<Options.nbLigne; k++) {
				if(plateau[i][k].getRefresh()) {
					plateau[i][k].paint(g,i,k);
				}
			}
		}
	}
}
