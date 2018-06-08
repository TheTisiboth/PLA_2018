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
	
	public void reduce_nb_case() {
		nb_case = nb_case -3;
	}
	
	public boolean life() {
		return nb_case>0;
	}
	
}
