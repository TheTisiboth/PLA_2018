package no.physic.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import mvc.MesOptions;

public class Recharge extends No_Physic_Entity {

	BufferedImage m_paint;

	public Recharge(int x, int y, BufferedImage b) {
		super(x, y);
		m_paint = b;
	}

	@Override
	public void paint(Graphics g) {
		Image img = m_paint;
		int w = (int) (MesOptions.taille_case);
		int h = (int) (MesOptions.taille_case);
		g.drawImage(img, x * MesOptions.taille_case, y * MesOptions.taille_case, w, h, null);
	}

}
