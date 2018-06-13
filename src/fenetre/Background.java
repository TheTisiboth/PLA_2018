package fenetre;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Background extends JPanel {

	private static final long serialVersionUID = 1L;
	File imageFile;
	BufferedImage img;

	public Background(Dimension d, int ctrl) {
		loadImage(ctrl);
		setLayout(null);
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);
		setSize(d);
	}

	private void loadImage(int ctrl) {

		if (ctrl == 1)
			imageFile = new File("images/accueil.png");
		if (ctrl == 2)
			imageFile = new File("images/game.png");
		if (ctrl == 3)
			imageFile = new File("images/regles.png");
		if (ctrl == 4)
			imageFile = new File("images/credits.png");
		// if the player 1 has won
		if (ctrl == 5) {
			imageFile = new File("images/statsj1.png");
		}
		// if the player 2 has won
		if (ctrl == 6) {
			imageFile = new File("images/statsj2.png");
		}
		if (ctrl == 7) {
			imageFile = new File("images/automates.png");
		}
		try {
			img = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
}
