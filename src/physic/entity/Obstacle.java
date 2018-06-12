package physic.entity;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import mvc.MesOptions;

public class Obstacle extends Physic_Entity {
	private Color couleur = Color.GRAY;
	private int health_point;
	private BufferedImage m_obstacle;
	BufferedImage m_sprite;
	int m_w, m_h;
	int m_x, m_y;
	int m_nrows, m_ncols;
	int m_idx;
	float m_scale = 1;
	long m_lastChange;
	BufferedImage[] m_sprites;

	public Obstacle(int x, int y, int hp, int rows, int columns, BufferedImage sprite, BufferedImage m_obstacle) {
		super(x, y);
		health_point = hp;
		this.m_obstacle = m_obstacle;
		m_sprite = sprite;
		m_ncols = columns;
		m_nrows = rows;
		int width = sprite.getWidth(null);
		int height = sprite.getHeight(null);

		m_sprites = new BufferedImage[rows * columns];
		m_w = width / columns;
		m_h = height / rows;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				int k = j * m_w;
				int l = i * m_h;
				m_sprites[(i * columns) + j] = m_sprite.getSubimage(k, l, m_w, m_h);
			}
		}
	}

	public boolean done() {
		return (m_idx == m_sprites.length);
	}

	public void setPosition(int x, int y, float scale) {
		m_x = (int) (x - scale * m_w / 2f);
		m_y = (int) (y - scale * m_h / 2f);
		m_scale = scale;
		m_idx = 0;
	}

	public void step(long now) {
		long elapsed = now - m_lastChange;
		if (elapsed > 10L) {
			m_lastChange = now;
			if (m_idx < m_sprites.length)
				m_idx++;
		}
	}

	public void paint(Graphics g) {
		Image img;
		if (health_point > 0) {
			img = m_obstacle;
			int w = (int) (MesOptions.taille_case);
			int h = (int) (MesOptions.taille_case);
			g.drawImage(img, x * MesOptions.taille_case, y * MesOptions.taille_case, w, h, null);
		} else {
			if (m_idx == m_sprites.length)
				img = m_sprites[m_idx - 1];
			else
				img = m_sprites[m_idx];
			int w = (int) (m_scale * m_w);
			int h = (int) (m_scale * m_h);
			g.drawImage(img, m_x, m_y, w, h, null);
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

}
