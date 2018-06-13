package mvc;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import edu.ricm3.game.GameController;

public class Controller extends GameController {
	private Model m_model;

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

		// player 1
		// key d (right)
		if (e.getKeyCode() == 68) {
			m_model.getJ1().setDirection('R');
		}
		// key z (up)
		else if (e.getKeyCode() == 90) {
			m_model.getJ1().setDirection('U');
		}
		// key q (left)
		else if (e.getKeyCode() == 81) {
			m_model.getJ1().setDirection('L');
		}
		// key s (down)
		else if (e.getKeyCode() == 83) {
			m_model.getJ1().setDirection('D');
		}
		// key space (hit)
		else if (e.getKeyCode() == 32) {
			m_model.hit(m_model.getJ1());
		}
		// key & (1)
		else if (e.getKeyCode() == 150)
			m_model.spawnzbire(m_model.getJ1(), 0, m_model.getJ1().getDirection());
		// key é (2)
		else if (e.getKeyCode() == 0)
			m_model.spawnzbire(m_model.getJ1(), 1, m_model.getJ1().getDirection());
		// key " (3)
		else if (e.getKeyCode() == 152)
			m_model.spawnzbire(m_model.getJ1(), 2, m_model.getJ1().getDirection());
		// key ' (4)
		else if (e.getKeyCode() == 222)
			m_model.spawnzbire(m_model.getJ1(), 3, m_model.getJ1().getDirection());

		// player 2
		// key right
		if (e.getKeyCode() == 39) {
			m_model.getJ2().setDirection('R');
		}
		// key up
		else if (e.getKeyCode() == 38) {
			m_model.getJ2().setDirection('U');
		}
		// key left
		else if (e.getKeyCode() == 37) {
			m_model.getJ2().setDirection('L');
		}
		// key down
		else if (e.getKeyCode() == 40) {
			m_model.getJ2().setDirection('D');
		}
		// key shift (hit)
		else if (e.getKeyCode() == 16) {
			m_model.hit(m_model.getJ2());
		} else if (e.getKeyCode() == 44) // touche ,
			m_model.spawnzbire(m_model.getJ2(), 0, m_model.getJ2().getDirection());
		else if (e.getKeyCode() == 59) // touche p
			m_model.spawnzbire(m_model.getJ2(), 1, m_model.getJ2().getDirection());
		else if (e.getKeyCode() == 513) // touche ^
			m_model.spawnzbire(m_model.getJ2(), 2, m_model.getJ2().getDirection());
		else if (e.getKeyCode() == 517) // touche $
			m_model.spawnzbire(m_model.getJ2(), 3, m_model.getJ2().getDirection());

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		// player 1
		// 68 : d 81 : q 83 : s 90 : z
		if (keyCode == 68 || keyCode == 81 | keyCode == 83 || keyCode == 90) {
			m_model.getJ1().setMovement(false);
		}
		// player 2
		// 37 : left 38 : up 39 : right 40 : down
		if (keyCode == 37 || keyCode == 38 | keyCode == 39 || keyCode == 40) {
			m_model.getJ2().setMovement(false);
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
