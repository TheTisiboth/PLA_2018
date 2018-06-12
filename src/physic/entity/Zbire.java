package physic.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import interpreter.Automaton_I;
import mvc.MesOptions;

public class Zbire extends Physic_Entity {

	private Color couleur;
	private int nb_case, type, joueur;
	int m_w, m_h, m_idx = 1, m_nrows, m_ncols;
	float m_scale;
	BufferedImage m_sprite;
	BufferedImage[] m_sprites;
	char direction;
	Automaton_I automate;
	String etatCourant;

	public Zbire(BufferedImage sprite, Integer rows, Integer columns, int x, int y, Color c, int n, int type,
			float scale, int joueur) {
		super(x, y);
		m_sprite = sprite;
		m_nrows = rows;
		m_ncols = columns;
		this.setCouleur(c);
		nb_case = n;
		this.setType(type);
		m_scale = scale;
		this.joueur = joueur;
		splitSprite();
	}

	public int getJoueur() {
		return joueur;
	}

	public void setJoueur(int joueur) {
		this.joueur = joueur;
	}

	void splitSprite() {
		int width = m_sprite.getWidth(null);
		int height = m_sprite.getHeight(null);
		m_sprites = new BufferedImage[m_nrows * m_ncols];
		m_w = width / m_ncols;
		m_h = height / m_nrows;
		for (int i = 0; i < m_nrows; i++) {
			for (int j = 0; j < m_ncols; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_sprites[(i * m_ncols) + j] = m_sprite.getSubimage(x, y, m_w, m_h);
			}
		}
	}

	public void paint(Graphics g) {
		Image img = m_sprites[m_idx];
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, x * MesOptions.taille_case, y * MesOptions.taille_case, w, h, null);
	}

	public void step(long now) {
	}

	public void reduce_nb_case() {
		nb_case = nb_case - 3;
	}

	public boolean life() {
		return nb_case > 0;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
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
	public boolean cell(String dir, String entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closest(String dir, String entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gotStuff() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void wizz() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(String dir) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void protect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jeter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void store() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void get() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void power() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kamikaze() {
		// TODO Auto-generated method stub
		
	}

}
