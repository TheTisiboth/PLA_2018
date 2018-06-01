package physic.entity;

import java.awt.Color;

public class Zbire extends Physic_Entity{
	
	private Color couleur;
	private int nb_case;
	
	public Zbire(int x, int y, Color c, int n) {
		super(x, y);
		this.couleur =c;
		nb_case =n;
	}
	
}
