package no.physic.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import mvc.Options;

public class Speed extends Bonus{
	BufferedImage m_thunder;
	
	public Speed(int x, int y, BufferedImage m_thunder) {
		super(x, y);
		this.m_thunder =m_thunder;
		
	}
	
	@Override
	public void paint(Graphics g) {
		Image img = m_thunder;
		int w = (int) (Options.taille_case);
		int h = (int) (Options.taille_case);
		g.drawImage(img, x * Options.taille_case, y * Options.taille_case, w, h, null);
	}
	

}
