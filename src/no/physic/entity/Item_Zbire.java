package no.physic.entity;

import java.awt.Color;
import java.awt.Graphics;

import mvc.MesOptions;

public class Item_Zbire extends No_Physic_Entity {
	
	private int duration;
	private int durationPop;
	
	public Item_Zbire(int x,int y) {
		super(x, y);
		duration = 3;
		durationPop = MesOptions.depopBonus;
	}

	@Override
	public void paint(Graphics g) {
			g.setColor(Color.GREEN);
			g.fillOval(x * MesOptions.taille_case + 6, y * MesOptions.taille_case + 6, 25,25);
		
	}
	
	public void step() {
		durationPop--;
	}

	public int getDurationPop() {
		return durationPop;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	
}	
