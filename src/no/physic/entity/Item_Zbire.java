package no.physic.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import mvc.MesOptions;

public class Item_Zbire extends No_Physic_Entity {
	
	BufferedImage m_item;
	
	public Item_Zbire(int x,int y, BufferedImage m_item) {
		super(x, y);
		this.m_item = m_item;
	}

	@Override
	public void paint(Graphics g) {
		Image img = m_item;
		int w = (int) (MesOptions.taille_case);
		int h = (int) (MesOptions.taille_case);
		g.drawImage(img, x * MesOptions.taille_case, y * MesOptions.taille_case, w, h, null);
		
	}
	
}	
