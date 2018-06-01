package proto;

import java.awt.Color;
import java.awt.Graphics;

import edu.ricm3.game.GameView;
import proto.*;

public class View extends GameView{
	Color background = Color.orange;
	Model m_model;
	Controller m_ctr;
	
	public View(Model m, Controller c) {
		m_model = m;
		m_ctr = c;
	}

	@Override
	protected void _paint(Graphics g) {
		
	}
}
