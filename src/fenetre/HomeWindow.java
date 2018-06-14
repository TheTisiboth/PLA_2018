package fenetre;

import java.awt.BorderLayout;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import edu.ricm3.game.GameUI;
import edu.ricm3.game.WindowListener;
import javafx.scene.control.CheckBox;
import mvc.Controller;
import mvc.MesOptions;
import mvc.Model;
import mvc.Sounds;
import mvc.View;
import ricm3.parser.AutomataParser;
import ricm3.parser.Ast.AI_Definitions;
import ricm3.parser.Ast.Automaton;
import ricm3.parser.ParseException;


public class HomeWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel main;
	JLabel m_text;
	Dimension d;

	JTextField j1, j2;

	JButton play, rules, fg1, fg2, fd1, fd2, credits, engrenage;
	public JCheckBox checkBox1, checkBox2;
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

		j1 = new JTextField(MesOptions.nom_j1);
		j1.setBounds(140, 100, 300, 100);
		j1.setForeground(Color.WHITE);
		j1.setFont(new Font("Helvetica", Font.BOLD, 20));
		j1.setOpaque(false);
		j1.setBorder(null);
		j1.setHorizontalAlignment(JTextField.CENTER);

		nom_j1 = j1.getText();

		j1.addActionListener(this);

		img.add(j1);

		// Fin Textfield Jour 1

		// Textfield Joueur 2

		j2 = new JTextField(MesOptions.nom_j2);
		j2.setBounds(765, 100, 300, 100);
		j2.setForeground(Color.WHITE);
		j2.setFont(new Font("Helvetica", Font.BOLD, 20));
		j2.setOpaque(false);
		j2.setBorder(null);
		j2.setHorizontalAlignment(JTextField.CENTER);

		nom_j2 = j2.getText();

		j2.addActionListener(this);

		img.add(j2);

		checkBox1 = new JCheckBox("", new CheckBoxIcon(216, 63, 130));
		checkBox2 = new JCheckBox("", new CheckBoxIcon(Color.BLUE));

		checkBox1.setBounds(235, 440, 20, 20);
		checkBox2.setBounds(950, 440, 20, 20);
		img.add(checkBox1);
		img.add(checkBox2);

		// Fin Textfield Jour 2

		// Engrenage
		engrenage = new JButton();
		engrenage.setBounds(380, 380, 430, 75);
		engrenage.setOpaque(false);
		engrenage.setContentAreaFilled(false);
		engrenage.setBorderPainted(false);

		engrenage.addActionListener(this);

		img.add(engrenage);

		// fin engrenage

		// fin engrenage

		// Affichage Sprites Joueur 1
		perso1 = 0;
		perso2 = 1;
		sprites = splitSprite();
		spritePanel1 = new JLabel(new ImageIcon(sprites[247 + perso1 * 8]));
		spritePanel1.setBounds(240, 195, 100, 170);
		spritePanel1.setOpaque(false);
		img.add(spritePanel1);

		// Bouton flecheDroiteJ1

		fd1 = new JButton();
		fd1.setBounds(465, 247, 50, 80);
		fd1.setOpaque(false);
		fd1.setContentAreaFilled(false);
		fd1.setBorderPainted(false);
		fd1.addActionListener(this);
		img.add(fd1);

		// Bouton fleche gauche 1

		fg1 = new JButton();
		fg1.setBounds(68, 247, 50, 80);
		fg1.setOpaque(false);
		fg1.setContentAreaFilled(false);
		fg1.setBorderPainted(false);
		fg1.addActionListener(this);
		img.add(fg1);

		// Affichage Sprites Joueur 2
		spritePanel2 = new JLabel(new ImageIcon(sprites[247 + perso2 * 8]));
		spritePanel2.setBounds(850, 195, 100, 170);
		spritePanel2.setOpaque(false);
		img.add(spritePanel2);

		// Bouton flecheDroiteJ2

		fd2 = new JButton();
		fd2.setBounds(1080, 247, 50, 80);
		fd2.setOpaque(false);
		fd2.setContentAreaFilled(false);
		fd2.setBorderPainted(false);
		fd2.addActionListener(this);
		img.add(fd2);

		// Bouton fleche gauche 2

		fg2 = new JButton();
		fg2.setBounds(690, 247, 50, 80);
		fg2.setOpaque(false);
		fg2.setContentAreaFilled(false);
		fg2.setBorderPainted(false);
		fg2.addActionListener(this);
		img.add(fg2);

		// Bouton "Click to play"

		play = new JButton();
		play.setBounds(360, 483, 498, 70);
		play.setOpaque(false);
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);

		play.addActionListener(this);

		img.add(play);

		// Fin Bouton "Click to play"

		// Bouton "Rules"

		rules = new JButton();
		rules.setBounds(-120, 520, 300, 50);
		rules.setOpaque(false);
		rules.setContentAreaFilled(false);
		rules.setBorderPainted(false);
		rules.addActionListener(this);

		img.add(rules);

		// Fin Bouton "Rules"

		// Bouton "Crédits"

		credits = new JButton();
		credits.setBounds(980, 520, 300, 50);
		credits.setOpaque(false);
		credits.setContentAreaFilled(false);
		credits.setBorderPainted(false);

		credits.addActionListener(this);
		img.add(credits);

		

		// Fin Bouton "Crédits"

		// On ajoute le tout dans la fenetre
		cont.add(img, BorderLayout.CENTER);

		this.addWindowListener(new WindowListener(new Model(1, 2)));
		this.setSize(d);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);

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
			MesOptions.nom_j1 = j1.getText();
		}
		if (s == j2) {
			j2.setText(j2.getText());
			MesOptions.nom_j2 = j2.getText();
		}

		// Quand on clique sur le bouton "Play"
		if (s == play) {

			Sounds.clic_sound();

			nom_j1 = j1.getText();
			nom_j2 = j2.getText();

			// construct the game elements: model, controller, and view.
			MesOptions.automates_j1 = new LinkedList<String>();
			MesOptions.automates_j2 = new LinkedList<String>();
			ArrayList<String> tab = new ArrayList<String>();

			try {
				if (MesOptions.deja_parse) // si on a deja parse un fichier, il
					// faut reinitialiser le parser
					AutomataParser.ReInit(new BufferedReader(new FileReader("save.txt")));
				else // on crée une nouvelle instance du parser, si l'on ne l'a
						// jamais fait
					new AutomataParser(new BufferedReader(new FileReader("save.txt")));
				MesOptions.deja_parse = true;
				// On lance le parser
				AI_Definitions def = (AI_Definitions) AutomataParser.Run();
				// list contient la liste de tout les automates parsé
				MesOptions.automates = def.make(); // créer la liste d'automates.
				LinkedList<Automaton> list = (LinkedList<Automaton>) def.getAutomata();
				
				Iterator<Automaton> iter = list.iterator();
				while (iter.hasNext()) {
					// on ajoute a la liste d'automate leur noms
					tab.add(iter.next().getName().getValue());
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			for (int i = 0; i < 4; i++) {
				MesOptions.automates_j1.add(tab.get(i));
				// TODO supprimer l'affichage
				System.out.println(tab.get(i));
			}
			for (int i = 4; i < 8; i++) {
				MesOptions.automates_j2.add(tab.get(i));
				System.out.println(tab.get(i));
			}
			
			model = new Model(perso2, perso1);
			controller = new Controller(model);
			view = new View(model, controller);
			m_game.setM_controller(controller);
			m_game.setM_model(model);
			m_game.setM_view(view);

			model.setM_frame(new GameWindow(d, controller, view, model, nom_j1, nom_j2));
			model.setIA1(checkBox1.isSelected());
			model.setIA2(checkBox2.isSelected());
			m_game.createTimer();

			dispose();
		}

		// Quand on clique sur le bouton "Choix Automates"
		if (s == engrenage) {
			Sounds.clic_sound();
			new ChoixZbire(d, m_game);
			dispose();
		}

		// Quand on clique sur le bouton "Règles"
		if (s == rules) {
			Sounds.clic_sound();
			new RulesWindow(d, m_game);
			dispose();
		}

		// Quand on clique sur le bouton "Crédits"
		if (s == credits) {
			Sounds.clic_sound();
			new CreditsWindow(d, m_game);
			dispose();
		}

		// Boutons du joueur 1
		if (s == fd1) {
			Sounds.clic_sound();
			do {
				perso1 = (perso1 == 4) ? 0 : perso1 + 1;
			} while (perso1 == perso2);
			spritePanel1.setIcon(new ImageIcon(sprites[247 + perso1 * 8]));
			this.validate();

		}
		if (s == fg1) {
			Sounds.clic_sound();
			do {
				perso1 = (perso1 == 0) ? 4 : perso1 - 1;
			} while (perso1 == perso2);
			spritePanel1.setIcon(new ImageIcon(sprites[247 + perso1 * 8]));
			this.validate();

		}
		// Boutons du joueur 2
		if (s == fd2) {
			Sounds.clic_sound();
			do {
				perso2 = (perso2 == 4) ? 0 : perso2 + 1;
			} while (perso1 == perso2);
			spritePanel2.setIcon(new ImageIcon(sprites[247 + perso2 * 8]));
			this.validate();

		}
		if (s == fg2) {
			Sounds.clic_sound();
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

	public void windowClosing(WindowEvent e) {

		System.exit(0);
	}

}

class CheckBoxIcon implements Icon {
	int r;
	int g;
	int b;
	Color c;

	public CheckBoxIcon(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;

	}

	public CheckBoxIcon(Color c) {
		this.c = c;
	}

	public void paintIcon(Component component, Graphics g, int x, int y) {
		AbstractButton abstractButton = (AbstractButton) component;
		ButtonModel buttonModel = abstractButton.getModel();
		Color color = (buttonModel.isSelected()) ? ((c != null) ? c : new Color(r, this.g, b)) : Color.WHITE;
		g.setColor(color);
		g.fillRect(0, 0, 20, 20);
		g.drawRect(0, 0, 20, 20);
		// g.drawImage(img, x, y, width, height, observer)

	}

	public int getIconWidth() {
		return 20;
	}

	public int getIconHeight() {
		return 20;
	}
}
