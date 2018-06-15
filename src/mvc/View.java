package mvc;

import java.awt.Color;
import java.awt.Graphics;

import edu.ricm3.game.GameView;

public class View extends GameView {

	private static final long serialVersionUID = 1L;
	Color background = new Color(255, 255, 255, 0); // transparent
	Model m_model;
	Controller m_ctr;

	// constructor 
	public View(Model m, Controller c) {
		m_model = m;
		m_ctr = c;
	}

	@Override
	protected void _paint(Graphics g) {

		Case[][] plateau = m_model.getPlateau();

		for (int i = 0; i < MesOptions.nbCol; i++) {
			for (int k = 0; k < MesOptions.nbLigne; k++) {
				if (plateau[i][k].getRefresh()) {
					plateau[i][k].paint(g, i, k);
				}
			}
		}
	}
}
