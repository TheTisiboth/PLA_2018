package no.physic.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import mvc.MesOptions;

public class Portal extends No_Physic_Entity{

	private BufferedImage m_portal;
	
	
	public Portal(int x, int y, BufferedImage m_portal) {
		super(x, y);
		this.m_portal = m_portal;
	}

	@Override
	public void paint(Graphics g) {
		Image img = m_portal;
		int w = (int) (MesOptions.taille_case);
		int h = (int) (MesOptions.taille_case);
		g.drawImage(img, x * MesOptions.taille_case, y * MesOptions.taille_case, w, h, null);
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

}
