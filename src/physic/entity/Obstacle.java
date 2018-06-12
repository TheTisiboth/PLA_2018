package physic.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import mvc.Case;
import mvc.MesOptions;

public class Obstacle extends Physic_Entity {
	private Color couleur = Color.GRAY;
	private int health_point;
	private BufferedImage m_obstacle;

	public Obstacle(int x, int y, int hp, BufferedImage m_obstacle) {
		super(x, y);
		health_point = hp;
		this.m_obstacle = m_obstacle;
	}

	public void paint(Graphics g) {
		if (health_point > 0) {
			Image img = m_obstacle;
			int w = (int) (MesOptions.taille_case);
			int h = (int) (MesOptions.taille_case);
			g.drawImage(img, x * MesOptions.taille_case, y * MesOptions.taille_case, w, h, null);
		}
	}

	public Color getCouleur() {
		return couleur;
	}

	public void reduce_life() {
		health_point--;
	}

	public boolean life() {
		return health_point > 0;
	}

	@Override
	public boolean gotPower() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean key(String cle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean myDir(String dir) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cell(String dir, String entity, Case[][] plateau) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closest(String dir, String entity, Case[][] plateau) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gotStuff() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void wizz(Case[][] plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pop(Case[][] plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(String dir, Case[][] plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turn(Case[][] plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jump(Case[][] plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hit(Case[][] plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void protect(Case[][] plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pick(Case[][] plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jeter(Case[][] plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void store(Case[][] plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void get(Case[][] plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void power(Case[][] plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kamikaze(Case[][] plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEtatCourant(String target) {
		// TODO Auto-generated method stub
		
	}

}
