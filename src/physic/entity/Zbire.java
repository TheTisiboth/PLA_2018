package physic.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import interpreter.Automaton_I;
import mvc.Case;
import mvc.MesOptions;
import no.physic.entity.No_Physic_Entity;
import no.physic.entity.Portal;

public class Zbire extends Physic_Entity {

	private Color couleur;
	private int nb_case, type, joueur;
	int m_w, m_h, m_idx, m_nrows, m_ncols;
	float m_scale;
	BufferedImage m_sprite;
	public BufferedImage[] m_sprites;
	String direction;

	Automaton_I automate;
	String etatCourant;

	private long m_lastMove;

	public Zbire(int x, int y, Color c, int n, int type, float scale, int joueur, Automaton_I automate,
			String etatCourant) {
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
		this.automate = automate;
		this.etatCourant = etatCourant;
		direction = "N";
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

	public void step(long now, Case[][] plateau) {
		long elapsed = now - m_lastMove;
		if (elapsed > 150L) {
			automate.step(this, etatCourant, plateau);
			m_idx = (m_idx == 4) ? 0 : 4;
			m_lastMove = now;
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

	@Override
	public void setEtatCourant(String target) {
		etatCourant = target;
	}

	@Override
	public boolean gotPower() {
		return nb_case > 0;
	}

	@Override
	public boolean key(String cle) {
		return false;
	}

	@Override
	public boolean myDir(String dir) {
		return dir.equals(direction);
	}

	public Case getC(int x, int y, Case[][] plateau) {
		if (x >= 0 && x < MesOptions.nbCol && y >= 0 && y < MesOptions.nbLigne) {
			return plateau[x][y];
		}
		return null;
	}

	@Override
	public boolean cell(String dir, String entity, Case[][] plateau) {
		Case c = null;

		switch (dir) {
		case "N":
			c = getC(x, y - 1, plateau);
			break;
		case "S":
			c = getC(x, y + 1, plateau);
			break;
		case "E":
			c = getC(x + 1, y, plateau);
			break;
		case "O":
			c = getC(x - 1, y, plateau);
			break;
		case "F":
			switch (direction) {
			case "N":
				c = getC(x, y - 1, plateau);
				break;
			case "S":
				c = getC(x, y + 1, plateau);
				break;
			case "E":
				c = getC(x + 1, y, plateau);
				break;
			case "O":
				c = getC(x - 1, y, plateau);
				break;
			default:
				System.out.println("Pb direction sbire");
				break;
			}
			break;
		case "B":
			switch (direction) {
			case "N":
				c = getC(x, y + 1, plateau);
				break;
			case "S":
				c = getC(x, y - 1, plateau);
				break;
			case "E":
				c = getC(x - 1, y, plateau);
				break;
			case "O":
				c = getC(x + 1, y, plateau);
				break;
			default:
				System.out.println("Pb direction sbire");
				break;
			}
			break;
		case "L":
			switch (direction) {
			case "N":
				c = getC(x - 1, y, plateau);
				break;
			case "S":
				c = getC(x + 1, y, plateau);
				break;
			case "E":
				c = getC(x, y - 1, plateau);
				break;
			case "O":
				c = getC(x, y + 1, plateau);
				break;
			default:
				System.out.println("Pb direction sbire");
				break;
			}
			break;
		case "R":
			switch (direction) {
			case "N":
				c = getC(x + 1, y, plateau);
				break;
			case "S":
				c = getC(x - 1, y, plateau);
				break;
			case "E":
				c = getC(x, y + 1, plateau);
				break;
			case "O":
				c = getC(x, y - 1, plateau);
				break;
			default:
				System.out.println("Pb direction sbire");
				break;
			}
			break;
		default:
			return false;
		}

		if (c == null)
			return false;
		else {
			return entite(c, entity);
		}
	}

	boolean entite(Case c, String entity) {
		mvc.Entity e = c.getE();
		switch (entity) {
		case "V":
			return e == null;
		case "T":
			if (e instanceof Joueur) {
				return couleur == ((Joueur) e).getColor();
			} else if (e instanceof Zbire) {
				return couleur == ((Zbire) e).getCouleur();
			} else {
				return false;
			}
		case "A":
			if (e instanceof Joueur) {
				return couleur != ((Joueur) e).getColor();
			} else if (e instanceof Zbire) {
				return couleur != ((Zbire) e).getCouleur();
			} else {
				return false;
			}
		case "D":
			return false;
		case "P":
			return (e instanceof No_Physic_Entity);
		case "J":
			return false;
		case "G":
			return (e instanceof Portal);
		case "M":
			return false;
		default:
			return false;
		}
	}

	@Override
	public boolean closest(String dir, String entity, Case[][] plateau) {
		String m_dir = null;
		int nb_case = 0;
		int min = 100;
		boolean trouve = false;
		int x_recherche = x;
		int y_recherche = y;
		Case c;

		// recherche dans la direction nord indice 0
		while (y_recherche > 0 && !trouve) {
			y_recherche--;
			nb_case++;
			c = plateau[x_recherche][y_recherche];
			trouve = entite(c, entity);
		}
		if (trouve) {
			m_dir = "N";
			min = nb_case;
		}

		// recherche dans la direction sud indice 1
		y_recherche = y;
		nb_case = 0;
		while (y_recherche < MesOptions.nbLigne - 1 && !trouve) {
			y_recherche++;
			nb_case++;
			c = plateau[x_recherche][y_recherche];
			trouve = entite(c, entity);
		}
		if (trouve) {
			if (nb_case < min) {
				min = nb_case;
				m_dir = "S";
			}
		}

		// recherche dans la direction est indice 2
		y_recherche = y;
		nb_case = 0;
		while (x_recherche < MesOptions.nbCol - 1 && !trouve) {
			x_recherche++;
			nb_case++;
			c = plateau[x_recherche][y_recherche];
			trouve = entite(c, entity);
		}
		if (trouve) {
			if (nb_case < min) {
				min = nb_case;
				m_dir = "E";
			}
		}

		// recherche dans la direction ouest indice 3
		x_recherche = x;
		nb_case = 0;
		while (x_recherche > 0 && !trouve) {
			x_recherche--;
			nb_case++;
			c = plateau[x_recherche][y_recherche];
			trouve = entite(c, entity);
		}
		if (trouve) {
			if (nb_case < min) {
				min = nb_case;
				m_dir = "O";
			}
		}

		if (m_dir == null)
			return false;

		switch (dir) {
		case "N":
		case "S":
		case "E":
		case "O":
		case "F":
			return m_dir.equals(dir);
		case "B":
			switch (direction) {
			case "N":
				return m_dir.equals("S");
			case "S":
				return m_dir.equals("N");
			case "E":
				return m_dir.equals("O");
			case "O":
				return m_dir.equals("E");
			default:
				System.out.println("Pb direction sbire");
				break;
			}
		case "L":
			switch (direction) {
			case "N":
				return m_dir.equals("O");
			case "S":
				return m_dir.equals("E");
			case "E":
				return m_dir.equals("N");
			case "O":
				return m_dir.equals("S");
			default:
				System.out.println("Pb direction sbire");
				break;
			}
		case "R":
			switch (direction) {
			case "N":
				return m_dir.equals("E");
			case "S":
				return m_dir.equals("O");
			case "E":
				return m_dir.equals("S");
			case "O":
				return m_dir.equals("N");
			default:
				System.out.println("Pb direction sbire");
				break;
			}
		default:
			return false;
		}
	}

	@Override
	public boolean gotStuff() {
		return false;
	}

	String choixDir(String dir) {
		switch (dir) {
		case "N":
			return "N";
		case "S":
			return "S";
		case "E":
			return "E";
		case "O":
			return "O";
		case "F":
			return direction;
		case "B":
			switch (direction) {
			case "N":
				return "S";
			case "S":
				return "N";
			case "E":
				return "O";
			case "O":
				return "E";
			default:
				System.out.println("Pb direction sbire");
				break;
			}
		case "L":
			switch (direction) {
			case "N":
				return "O";
			case "S":
				return "E";
			case "E":
				return "N";
			case "O":
				return "S";
			default:
				System.out.println("Pb direction sbire");
				break;
			}
		case "R":
			switch (direction) {
			case "N":
				return "E";
			case "S":
				return "O";
			case "E":
				return "S";
			case "O":
				return "N";
			default:
				System.out.println("Pb direction sbire");
				break;
			}
		default:
			return null;
		}
	}

	@Override
	public void move(String dir, Case[][] plateau) {
		int next_x = x;
		int next_y = y;
		String m_dir = choixDir(dir);
		switch (m_dir) {
		case "N":
			next_y--;
			break;
		case "S":
			next_y++;
			break;
		case "E":
			next_x++;
			break;
		case "O":
			next_x--;
			break;
		default:
			break;
		}
		nb_case--;
		Case c = getC(next_x, next_y, plateau);
		if (c != null) {
			plateau[x][y].setE(null);
			plateau[x][y].setRefresh(true);
			x = next_x;
			y = next_y;
			plateau[x][y].setE(this);
			plateau[x][y].setRefresh(true);
		}
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
	public void protect(Case[][] plateau) {
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
	public void wizz(Case[][] plateau) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pop(Case[][] plateau) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hit(Case[][] plateau) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pick(Case[][] plateau) {
		// TODO Auto-generated method stub

	}

	@Override
	public void kamikaze(Case[][] plateau) {
		// TODO Auto-generated method stub

	}

}
