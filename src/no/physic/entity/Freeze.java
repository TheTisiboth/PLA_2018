package no.physic.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import mvc.Options;

public class Freeze extends Bonus {
	BufferedImage m_stop;
	
	public Freeze(int x, int y, BufferedImage m_stop) {
		super(x, y);
		this.m_stop =m_stop;
		
	}
	
	@Override
	public void paint(Graphics g) {
		Image img = m_stop;
		int w = (int) (Options.taille_case);
		int h = (int) (Options.taille_case);
		g.drawImage(img, x * Options.taille_case, y * Options.taille_case, w, h, null);
	}

}
