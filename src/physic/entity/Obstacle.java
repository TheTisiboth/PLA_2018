package physic.entity;

import java.awt.Color;
import java.awt.Graphics;

import mvc.Options;

public class Obstacle extends Physic_Entity{
	private Color couleur = Color.GRAY;
	private int health_point;
	
	public Obstacle(int x, int y,int hp) {
		super(x, y);
		health_point = hp;
	}

	public void paint(Graphics g) {
		g.setColor(couleur);
		g.fillRect(x * Options.taille_case + 2, y * Options.taille_case + 2, Options.taille_case, Options.taille_case);
		
	}

	public Color getCouleur() {
		return couleur;
	}
	

}
