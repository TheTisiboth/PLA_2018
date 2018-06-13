package no.physic.entity;

import java.awt.Graphics;

import mvc.MesOptions;

public class Bonus extends No_Physic_Entity {

	private int duration, durationPop;

	public Bonus(int x, int y) {
		super(x, y);
		setDuration(3);
		durationPop = MesOptions.depopBonus;
	}

	public void paint(Graphics g) {

		if (this instanceof Speed) {
			Speed speed = (Speed) this;
			speed.paint(g);
		} else {
			Freeze freeze = (Freeze) this;
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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
