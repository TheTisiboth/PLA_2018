package mvc;

import java.awt.Graphics;
import javax.swing.JPanel;

public class Graphs extends JPanel {

	private static final long serialVersionUID = 1L;
	Model model;

	public void set_model(Model m) {
		this.model = m;
	}

	public void paint(Graphics g) {
		model.statistique.paint(g);
	}
}
