package mvc;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import edu.ricm3.game.GameController;
import mvc.Model;

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
		// Joueur 2
		if (e.getKeyCode() == 39) {
			m_model.getJ1().setDirection('R');
		} else if (e.getKeyCode() == 38) {
			m_model.getJ1().setDirection('U');
		} else if (e.getKeyCode() == 37) {
			m_model.getJ1().setDirection('L');
		} else if (e.getKeyCode() == 40) {
			m_model.getJ1().setDirection('D');
		}else if (e.getKeyCode() == 16) {
			m_model.hit(m_model.getJ1());
		}

		else if (e.getKeyCode() == 150) // touche &
			m_model.spawnzbire(m_model.getJ1(),0,m_model.getJ1().getDirection());
		else if (e.getKeyCode() == 0) // touche é
			m_model.spawnzbire(m_model.getJ1(),1,m_model.getJ1().getDirection());
		else if (e.getKeyCode() == 152) // touche "
			m_model.spawnzbire(m_model.getJ1(),2,m_model.getJ1().getDirection());
		else if (e.getKeyCode() == 222) // touche '
			m_model.spawnzbire(m_model.getJ1(),3,m_model.getJ1().getDirection());
		
		// Joueur 2

		if (e.getKeyCode() == 68) {
			m_model.getJ2().setDirection('R');
		} else if (e.getKeyCode() == 90) {
			m_model.getJ2().setDirection('U');
		} else if (e.getKeyCode() == 81) {
			m_model.getJ2().setDirection('L');
		} else if (e.getKeyCode() == 83) {
			m_model.getJ2().setDirection('D');
		}else if (e.getKeyCode() == 32) {
			m_model.hit(m_model.getJ2());
		}
		
		else if (e.getKeyCode() == 79) // touche &
			m_model.spawnzbire(m_model.getJ2(),0,m_model.getJ2().getDirection());
		else if (e.getKeyCode() == 80) // touche é
			m_model.spawnzbire(m_model.getJ2(),1,m_model.getJ2().getDirection());
		else if (e.getKeyCode() == 130) // touche "
			m_model.spawnzbire(m_model.getJ2(),2,m_model.getJ2().getDirection());
		else if (e.getKeyCode() == 515) // touche '
			m_model.spawnzbire(m_model.getJ2(),3,m_model.getJ2().getDirection());


	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == 37 || keyCode == 38 | keyCode == 39 || keyCode == 40) {
			m_model.getJ1().setMovement(false); 
		}
		if (keyCode == 68 || keyCode == 81 | keyCode == 83 || keyCode == 90) {
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
