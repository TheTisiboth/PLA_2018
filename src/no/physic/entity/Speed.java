package no.physic.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import mvc.MesOptions;

public class Speed extends Bonus {
	BufferedImage m_thunder;

	public Speed(int x, int y, BufferedImage m_thunder) {
		super(x, y);
		this.m_thunder = m_thunder;

	}

	@Override
	public void paint(Graphics g) {
		Image img = m_thunder;
		int w = (int) (MesOptions.taille_case);
		int h = (int) (MesOptions.taille_case);
		g.drawImage(img, x * MesOptions.taille_case, y * MesOptions.taille_case, w, h, null);
	}

}
