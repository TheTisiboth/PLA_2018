package mvc;

import java.awt.Color;

public class Case {
	private Color couleur;
	private Entity e;
	private boolean occuped;
	
	
	public Case(Entity e) {
		couleur = Color.ORANGE;
		this.e =e;
		if(e != null) {
			occuped = true;
		}else {
			occuped =false;
		}
			
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
		if(e== null) {
			occuped =false;
		}else {
			occuped =true;
		}
	}

	public boolean isOccuped() {
		return occuped;
	}

	public void setOccuped(boolean occuped) {
		this.occuped = occuped;
	}
	
	
}
