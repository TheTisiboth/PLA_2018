package physic.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import mvc.MesOptions;

public class Zbire extends Physic_Entity {

	private Color couleur;
	private int nb_case, type, joueur;
	int m_w, m_h, m_idx, m_nrows, m_ncols;
	float m_scale;
	BufferedImage m_sprite;
	BufferedImage[] m_sprites;
	char direction;
	private long m_lastMove;

	public Zbire(int x, int y, Color c, int n, int type, float scale, int joueur) {
		super(x, y);
		m_nrows = 3;
		m_ncols = 4;
		m_idx = 4;
		this.setCouleur(c);
		nb_case = n;
		this.setType(type);
		m_scale = scale;
		this.joueur = joueur;
		m_lastMove = 0;
		loadSprite(type);
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

	private void loadSprite(int type) {
		File sprite = null;
		if (joueur == 1) {
			if (type == 0)
				sprite = new File("images/sbires_rose.png");
			else if (type == 1)
				sprite = new File("images/sbires_bleu.png");
			else if (type == 2)
				sprite = new File("images/sbires_jaune.png");
			else if (type == 3)
				sprite = new File("images/sbires_vert.png");
		} else if (joueur == 2) {
			if (type == 0)
				sprite = new File("images/sbires_bleu_fonce.png");
			else if (type == 1)
				sprite = new File("images/sbires_violet.png");
			else if (type == 2)
				sprite = new File("images/sbires_rouge.png");
			else if (type == 3)
				sprite = new File("images/sbires_orange.png");
		}
		try {
			m_sprite = ImageIO.read(sprite);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}

	public void paint(Graphics g) {
		Image img = m_sprites[m_idx];
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, x * MesOptions.taille_case, y * MesOptions.taille_case, w, h, null);
	}

	public void step(long now) {
		if (m_lastMove == 0) {
			m_lastMove = now - 101L;
		}
		long elapsed = now - m_lastMove;
		if (elapsed > 200L) {
			System.out.println("Le zbire doit bougé, nbcase: " + nb_case);
			m_idx = (m_idx == 4) ? 0 : 4;
			m_lastMove = now;
			nb_case--;
		}
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

}
