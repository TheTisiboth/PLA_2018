package physic.entity;

import java.awt.Color;
import java.awt.Graphics;

public class Zbire extends Physic_Entity{
	
	private Color couleur;
	private int nb_case;
	private int type;
	
	public Zbire(int x, int y, Color c, int n, int type) {
		super(x, y);
		this.couleur =c;
		nb_case =n;
		this.type = type;
	}

	@Override
	public void paint(Graphics g) {
		
		
	}
	
}
