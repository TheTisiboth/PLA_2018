package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Case {
	private Color couleur;
	private BufferedImage m_couleur_init;
	private BufferedImage m_couleur;
	private Entity e;
	private boolean occuped;
	private boolean refresh;
	private boolean refresh_buffer2;

	public Case(Entity e, BufferedImage img) {

		this.e = e;
		if (e != null) {
			occuped = true;
		} else {
			occuped = false;
		}
		refresh = true;
		refresh_buffer2 = false;
		m_couleur = img;
		m_couleur_init = img;

	}

	// METHODE
	public void paint(Graphics g, int x, int y) {
		if(m_couleur != m_couleur_init) {
			Image img = m_couleur_init;
			int w = (int) (Options.taille_case);
			int h = (int) (Options.taille_case);
			g.drawImage(img, x * Options.taille_case, y * Options.taille_case, w, h, null);
		}
		Image img = m_couleur;
		int w = (int) (Options.taille_case);
		int h = (int) (Options.taille_case);
		g.drawImage(img, x * Options.taille_case, y * Options.taille_case, w, h, null);
		if (e != null) {
			e.paint(g);
		}
		
		if (refresh_buffer2) {
			refresh = false;
			refresh_buffer2 = false;
		} else {
			refresh_buffer2 = true;
		}

	}

	public BufferedImage getM_couleur() {
		return m_couleur;
	}

	// GETTER SETTER
	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public Entity getE() {
		return e;
	}

	public void setE(Entity e) {
		this.e = e;
		if (e == null) {
			occuped = false;
		} else {
			occuped = true;
		}
	}

	public boolean isOccuped() {
		return occuped;
	}

	public void setOccuped(boolean occuped) {
		this.occuped = occuped;
	}

	public boolean getRefresh() {
		return refresh;
	}

	public void setM_couleur(BufferedImage m_couleur) {
		this.m_couleur = m_couleur;
	}

	public void setRefresh(boolean b) {
		refresh = b;

	}

}
