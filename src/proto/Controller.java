package proto;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import edu.ricm3.game.GameController;
import proto.Model;

public class Controller extends GameController {
	private Model m_model;
	boolean P1_move;

	public Controller(Model m) {
		m_model = m;
	}

	@Override
	public void notifyVisible() {
		// TODO Auto-generated method stub

	}

	@Override
	public void step(long now) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 39) {
			P1_move = true;
			m_model.getCircle().step('R');
		} else if (e.getKeyCode() == 38) {
			P1_move = true;
			m_model.getCircle().step('U');
		} else if (e.getKeyCode() == 37) {
			P1_move = true;
			m_model.getCircle().step('L');
		} else if (e.getKeyCode() == 40) {
			P1_move = true;
			m_model.getCircle().step('D');
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == 37 || keyCode == 38 | keyCode == 39 || keyCode == 40) {
			P1_move = false;
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
