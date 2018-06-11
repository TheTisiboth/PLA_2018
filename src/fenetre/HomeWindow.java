package fenetre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import edu.ricm3.game.GameUI;
import mvc.Controller;
import mvc.Model;
import mvc.View;
import physic.entity.Joueur;

public class HomeWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel main;
	JLabel m_text;
	Dimension d;

	JTextField j1, j2;
	JButton play, rules, credits, fg1, fg2, fd1, fd2;
	String nom_j1, nom_j2;
	private JLabel spritePanel1, spritePanel2;
	BufferedImage[] sprites;

	Model model;
	Controller controller;
	View view;
	Timer m_timer;
	GameUI m_game;
	int perso1, perso2;

	public HomeWindow(Dimension d, GameUI game) {

		this.d = d;
		m_game = game;

		this.setTitle("COLORicm Deluxe Version 2.0");
		Container cont = this.getContentPane();
		cont.setSize(d);
		cont.setPreferredSize(d);
		cont.setMaximumSize(d);
		cont.setMinimumSize(d);

		JPanel img = new Background(d, 1);

		// Textfield Joueur 1

		j1 = new JTextField("Joueur 1");
		j1.setBounds(140, 100, 300, 100);
		j1.setForeground(Color.WHITE);
		j1.setFont(new Font("Helvetica", Font.BOLD, 20));
		j1.setOpaque(false);
		j1.setBorder(null);
		j1.setHorizontalAlignment(JTextField.CENTER);

		nom_j1 = j1.getText();

		j1.addActionListener(this);

		this.add(j1);

		// Fin Textfield Jour 1

		// Textfield Joueur 2

		j2 = new JTextField("Joueur 2");
		j2.setBounds(765, 100, 300, 100);
		j2.setForeground(Color.WHITE);
		j2.setFont(new Font("Helvetica", Font.BOLD, 20));
		j2.setOpaque(false);
		j2.setBorder(null);
		j2.setHorizontalAlignment(JTextField.CENTER);

		nom_j2 = j2.getText();

		j2.addActionListener(this);

		this.add(j2);

		// Fin Textfield Jour 2

		// Affichage Sprites Joueur 1
		perso1 = 0;
		perso2 = 1;
		sprites = splitSprite();
		spritePanel1 = new JLabel(new ImageIcon(sprites[247 + perso1 * 8]));
		spritePanel1.setBounds(240, 195, 100, 170);
		spritePanel1.setOpaque(false);
		this.add(spritePanel1);

		// Bouton flecheDroiteJ1

		fd1 = new JButton();
		fd1.setBounds(530, 247, 50, 80);
		fd1.setOpaque(false);
		fd1.setContentAreaFilled(false);
		fd1.setBorderPainted(false);
		fd1.addActionListener(this);
		this.add(fd1);

		// Bouton fleche gauche 1

		fg1 = new JButton();
		fg1.setBounds(20, 247, 50, 80);
		fg1.setOpaque(false);
		fg1.setContentAreaFilled(false);
		fg1.setBorderPainted(false);
		fg1.addActionListener(this);
		this.add(fg1);

		// Affichage Sprites Joueur 2
		spritePanel2 = new JLabel(new ImageIcon(sprites[247 + perso2 * 8]));
		spritePanel2.setBounds(850, 195, 100, 170);
		spritePanel2.setOpaque(false);
		this.add(spritePanel2);

		// Bouton flecheDroiteJ2

		fd2 = new JButton();
		fd2.setBounds(1140, 247, 50, 80);
		fd2.setOpaque(false);
		fd2.setContentAreaFilled(false);
		fd2.setBorderPainted(false);
		fd2.addActionListener(this);
		this.add(fd2);

		// Bouton fleche gauche 2

		fg2 = new JButton();
		fg2.setBounds(630, 247, 50, 80);
		fg2.setOpaque(false);
		fg2.setContentAreaFilled(false);
		fg2.setBorderPainted(false);
		fg2.addActionListener(this);
		this.add(fg2);

		// Bouton "Click to play"

		play = new JButton();
		play.setBounds(400, 490, 400, 100);
		play.setOpaque(false);
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);

		play.addActionListener(this);

		this.add(play);

		// Fin Bouton "Click to play"

		// Bouton "Rules"

		rules = new JButton();
		rules.setBounds(-120, 520, 300, 80);
		rules.setOpaque(false);
		rules.setContentAreaFilled(false);
		rules.setBorderPainted(false);

		rules.addActionListener(this);

		this.add(rules);

		// Fin Bouton "Rules"

		// Bouton "Crédits"

		credits = new JButton();
		credits.setBounds(980, 520, 300, 80);
		credits.setOpaque(false);
		credits.setContentAreaFilled(false);
		credits.setBorderPainted(false);

		credits.addActionListener(this);

		this.add(credits);

		// Fin Bouton "Crédits"

		// On ajoute le tout dans la fenetre
		cont.add(img, BorderLayout.CENTER);

		this.setSize(d);
		this.doLayout();
		this.setResizable(false);
		this.setVisible(true);

		this.pack();
		this.setLocationRelativeTo(null);

	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		if (d == null) {
			return;
		}
	}

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();

		// Récupération des noms des joueurs
		if (s == j1) {
			j1.setText(j1.getText());
		}
		if (s == j2) {
			j2.setText(j2.getText());
		}

		// Quand on clique sur le bouton "Play"
		if (s == play) {
			nom_j1 = j1.getText();
			nom_j2 = j2.getText();

			// construct the game elements: model, controller, and view.

			model = new Model(perso2, perso1);
			controller = new Controller(model);
			view = new View(model, controller);
			m_game.setM_controller(controller);
			m_game.setM_model(model);
			m_game.setM_view(view);

			model.setM_frame(new GameWindow(d, controller, view, model, nom_j1, nom_j2));
			m_game.createTimer();

			dispose();
		}

		// Quand on clique sur le bouton "Régles"
		if (s == rules) {
			new RulesWindow(d, m_game);
			dispose();
		}

		// Quand on clique sur le bouton "Crédits"
		if (s == credits) {
			new CreditsWindow(d, m_game);
			dispose();
		}

		// Boutons du joueur 1
		if (s == fd1) {
			do {
				perso1 = (perso1 == 4) ? 0 : perso1 + 1;
			} while (perso1 == perso2);
			spritePanel1.setIcon(new ImageIcon(sprites[247 + perso1 * 8]));
			this.validate();
		}
		if (s == fg1) {
			do {
				perso1 = (perso1 == 0) ? 4 : perso1 - 1;
			} while (perso1 == perso2);
			spritePanel1.setIcon(new ImageIcon(sprites[247 + perso1 * 8]));
			this.validate();
		}
		// Boutons du joueur 2
		if (s == fd2) {
			do {
				perso2 = (perso2 == 4) ? 0 : perso2 + 1;
			} while (perso1 == perso2);
			spritePanel2.setIcon(new ImageIcon(sprites[247 + perso2 * 8]));
			this.validate();
		}
		if (s == fg2) {
			do {
				perso2 = (perso2 == 0) ? 4 : perso2 - 1;
			} while (perso1 == perso2);
			spritePanel2.setIcon(new ImageIcon(sprites[247 + perso2 * 8]));
			this.validate();
		}

	}

	// divide the sprite image
	private BufferedImage[] splitSprite() {
		// LOAD SPRITE
		BufferedImage m_sprite;
		// credit : https://erikari.itch.io/elements-supremacy-assets
		File imageFile = new File("images/character.png");
		try {
			m_sprite = ImageIO.read(imageFile);

			BufferedImage[] m_sprites;
			int width = m_sprite.getWidth(null);
			int height = m_sprite.getHeight(null);
			int m_nrows = 12;
			int m_ncols = 24;

			m_sprites = new BufferedImage[m_nrows * m_ncols];
			int m_w = width / m_ncols;
			int m_h = height / m_nrows;
			for (int i = 0; i < m_nrows; i++) {
				for (int j = 0; j < m_ncols; j++) {
					int x = j * m_w;
					int y = i * m_h;
					m_sprites[(i * m_ncols) + j] = m_sprite.getSubimage(x, y, m_w, m_h);
				}
			}
			return m_sprites;
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
			return null;
		}
	}

}
