package no.physic.entity;

import java.awt.Color;
import java.awt.Graphics;

import mvc.Options;

public class Bonus extends No_Physic_Entity {

	private int duration;
	private int durationPop;
	
	public Bonus(int x, int y) {
		super(x, y);
		duration = 3;
		durationPop = Options.depopBonus;
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(x * Options.taille_case + 6, y * Options.taille_case + 6, 25,25);
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
