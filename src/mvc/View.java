package mvc;

import java.awt.Color;
import java.awt.Graphics;

import edu.ricm3.game.GameView;

public class View extends GameView {

	private static final long serialVersionUID = 1L;
	Color background = new Color(255, 255, 255, 0);
	Model m_model;
	Controller m_ctr;

	public View(Model m, Controller j1) {
		m_model = m;
		m_ctr = j1;
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
		if (m_model.timer == false) {
			Statistique s = m_model.getStatistique();
			s.paint(g);
		}

	}
}
