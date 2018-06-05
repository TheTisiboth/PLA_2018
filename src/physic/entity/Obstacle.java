package physic.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import mvc.MesOptions;

public class Obstacle extends Physic_Entity{
	private Color couleur = Color.GRAY;
	private int health_point;
	private BufferedImage m_obstacle;
	
	public Obstacle(int x, int y,int hp, BufferedImage m_obstacle) {
		super(x, y);
		health_point = hp;
		this.m_obstacle =m_obstacle;
	}


	public void paint(Graphics g) {
//		g.setColor(couleur);
//		g.fillRect(x * MesOptions.taille_case + 1, y * MesOptions.taille_case + 1, MesOptions.taille_case, MesOptions.taille_case);
//		
		Image img = m_obstacle;
		int w = (int) (MesOptions.taille_case);
		int h = (int) (MesOptions.taille_case);
		g.drawImage(img, x * MesOptions.taille_case, y * MesOptions.taille_case, w, h, null);
		
	}

	public Color getCouleur() {
		return couleur;
	}
	

}
