package mvc;

import java.awt.Color;
import java.awt.Graphics;

public class Case {
	private Color couleur;
	private Entity e;
	private boolean occuped;
	private boolean refresh;
	private boolean refresh_buffer2;

	public Case(Entity e) {
		couleur = Color.ORANGE;
		this.e = e;
		if (e != null) {
			occuped = true;
		} else {
			occuped = false;
		}
		refresh = false;
		refresh_buffer2 = false;

	}

	// METHODE
	public void paint(Graphics g, int x, int y) {
		if (e == null) {
			g.setColor(couleur);
			g.fillRect(x * Options.taille_case + 3, y * Options.taille_case +3, Options.taille_case-4,
					Options.taille_case-4);
		} else {
			e.paint(g);
		}
		
		if (refresh_buffer2) {
			refresh = false;
			refresh_buffer2 = false;
		} else {
			refresh_buffer2 = true;
		}

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

	public void setRefresh(boolean b) {
		refresh = b;

	}

}
