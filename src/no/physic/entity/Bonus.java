package no.physic.entity;

import java.awt.Color;
import java.awt.Graphics;


import mvc.MesOptions;

public class Bonus extends No_Physic_Entity {

	private int duration;
	private int durationPop;
	
	public Bonus(int x, int y) {
		super(x, y);
		duration = 3;
		durationPop = MesOptions.depopBonus;
	}

	public void paint(Graphics g) {

		if(this instanceof Speed ) {
			Speed speed = (Speed) this;
			speed.paint(g);
		}
		else {
			Freeze freeze = (Freeze)this;
			freeze.paint(g);
		}
		

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
