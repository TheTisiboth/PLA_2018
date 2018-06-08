package mvc;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graphs extends JPanel {
	Model model;
	public void set_model(Model m) {
		this.model = m;
	}
	public void paint(Graphics g) {
			model.statistique.paint(g);
	}
}
