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

	private Color couleur; // couleur de la peinture utilisée
	private int nb_case; // nombre de case qu'il reste a parcourir pour le Zbire
	private int type, joueur; // Permet de connaitre le sprite à charger
	int m_w, m_h, m_idx, m_nrows, m_ncols; // utilisés pour le chargement des sprites
	float m_scale;
	BufferedImage m_sprite; // matrice de sprite de tous les zbires
	public BufferedImage[] m_sprites; // tableau des sprite du zbire
	char direction; // direction du zbire

	public Zbire(int x, int y, Color c, int n, int type, float scale, int joueur, BufferedImage zbires) {
		super(x, y);
		m_nrows = 3;
		m_ncols = 4;
		m_idx = 4;
		this.setCouleur(c);
		nb_case = n;
		this.setType(type);
		m_scale = scale;
		this.joueur = joueur;
		m_sprite = zbires;
		splitSprite();
	}

	public int getJoueur() {
		return joueur;
	}

	public void setJoueur(int joueur) {
		this.joueur = joueur;
	}

	// permet de trouver les sprites correspondants au zbire en question
	void splitSprite() {
		// 1er temps: On divise la grande matrice de sprites en 8 matrices, selon les
		// couleurs des zbires
		int width = m_sprite.getWidth(null);
		int height = m_sprite.getHeight(null);
		BufferedImage[] m_temp = new BufferedImage[8];
		m_w = width;
		m_h = height / 8;
		for (int i = 0; i < 8; i++) {
			int x = 0;
			int y = i * m_h;
			m_temp[i] = m_sprite.getSubimage(x, y, m_w, m_h);
		}
		// 2eme temps: On split simplement la matrice et on stock toutes les images
		// utilisable dans un tableau
		int indiceSprite = (joueur == 1) ? 0 : 4;
		m_sprites = new BufferedImage[m_nrows * m_ncols];
		width = m_temp[type + indiceSprite].getWidth(null);
		height = m_temp[type + indiceSprite].getHeight(null);
		m_w = width / m_ncols;
		m_h = height / m_nrows;
		for (int i = 0; i < m_nrows; i++) {
			for (int j = 0; j < m_ncols; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_sprites[(i * m_ncols) + j] = m_temp[type + indiceSprite].getSubimage(x, y, m_w, m_h);
			}
		}
	}

	// choisis le bon sprite et peint le zbire dans la case
	public void paint(Graphics g) {
		Image img = m_sprites[m_idx];
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, x * MesOptions.taille_case, y * MesOptions.taille_case, w, h, null);
	}

	public void step(long now) {
		m_idx = (m_idx == 4) ? 0 : 4;
		nb_case--;
	}

	// reduit le nombre de case si le zbire se fait taper
	public void reduce_nb_case() {
		nb_case = nb_case - 3;
	}

	// return true si le zbire a de la vie
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
