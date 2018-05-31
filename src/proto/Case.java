package proto;

import java.awt.Color;

public class Case {
	private Color couleur;
	private Entity e;
	
	public Case() {
		couleur = Color.ORANGE;
	}

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
	}
	
	
}
