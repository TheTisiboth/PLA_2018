package fenetre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.ricm3.game.GameController;
import edu.ricm3.game.GameModel;
import edu.ricm3.game.GameUI;
import edu.ricm3.game.GameView;
import edu.ricm3.game.WindowListener;
import mvc.Graphs;
import mvc.Model;
import mvc.Sounds;

public class EndPage extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel main;
	GameView m_view;
	JLabel m_text;
	Model m_model;
	GameController m_controller;
	JButton rules;
	Dimension d;
	int largeur, hauteur;
	JButton m_rejouer;
	GameUI gameUI;
	String j1, j2;

	public EndPage(GameModel mod, GameView m_view, GameUI game) {
		
		// change icon of the frame 
		ImageIcon icon = new ImageIcon("images/item_sbire.png");
		this.setIconImage(icon.getImage());
		
		this.gameUI = game;
		largeur = 1200;
		hauteur = 600;

		d = new Dimension(largeur, hauteur);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		m_model = (Model) mod;
		j1 = m_model.getName_j1();
		j2 = m_model.getName_j2();

		this.m_view = m_view;
		if (m_model.statistique.getScore_joueur1()[m_model.statistique.getScore_joueur1().length
				- 1] > m_model.statistique.getScore_joueur2()[m_model.statistique.getScore_joueur2().length - 1]) {
			main = new Background(d, 5);
		} else {
			main = new Background(d, 6);
		}

		this.setTitle("COLORicm Deluxe Version 2.0 - STATISTIQUES");
		this.setSize(d);
		main.setLayout(new BorderLayout());

		// Création des police d'écriture
		Font font = new Font("Sherif", Font.BOLD, 24);
		Font fonNumber = new Font("Sherif", Font.BOLD, 50);

		// Mis en place de l'écran Ouest
		int entrelayout = 65;
		JLabel txt = new JLabel(j1, SwingConstants.CENTER);
		txt.setForeground(Color.WHITE);
		txt.setFont(font);
		Dimension preferredSize = new Dimension(350, 140);
		txt.setPreferredSize(preferredSize);
		txt.setOpaque(false);
		panel1.add(txt);

		txt = new JLabel("", SwingConstants.CENTER);
		preferredSize = new Dimension(350, 60);

		txt.setPreferredSize(preferredSize);
		panel1.add(txt);

		txt = new JLabel("" + m_model.statistique.getNombrecase_parcouru1(), SwingConstants.CENTER);
		preferredSize = new Dimension(350, entrelayout);
		txt.setFont(fonNumber);
		txt.setPreferredSize(preferredSize);
		txt.setForeground(Color.WHITE);
		panel1.add(txt);

		txt = new JLabel(" ", SwingConstants.CENTER);
		txt.setPreferredSize(preferredSize);
		panel1.add(txt);

		txt = new JLabel("" + m_model.statistique.getJoueur1_Bonus(), SwingConstants.CENTER);
		preferredSize = new Dimension(350, entrelayout);
		txt.setFont(fonNumber);
		txt.setForeground(Color.WHITE);
		txt.setPreferredSize(preferredSize);
		panel1.add(txt);

		txt = new JLabel(" ", SwingConstants.CENTER);
		txt.setPreferredSize(preferredSize);
		panel1.add(txt);

		txt = new JLabel("" + m_model.statistique.getNombre_zbire1(), SwingConstants.CENTER);
		preferredSize = new Dimension(350, entrelayout);
		txt.setFont(fonNumber);
		txt.setForeground(Color.WHITE);
		txt.setPreferredSize(preferredSize);
		panel1.add(txt);

		preferredSize = new Dimension(350, 600);
		panel1.setPreferredSize(preferredSize);
		panel1.setOpaque(false);
		main.add(panel1, BorderLayout.WEST);

		// Mis en place de l'écran Est
		JLabel txt1 = new JLabel(j2, SwingConstants.CENTER);
		txt1.setFont(font);
		txt1.setForeground(Color.WHITE);
		preferredSize = new Dimension(350, 140);
		txt1.setPreferredSize(preferredSize);
		panel2.add(txt1);

		txt1 = new JLabel("", SwingConstants.CENTER);
		preferredSize = new Dimension(350, 60);
		txt1.setPreferredSize(preferredSize);
		panel2.add(txt1);

		txt1 = new JLabel("" + m_model.statistique.getNombrecase_parcouru2(), SwingConstants.CENTER);
		preferredSize = new Dimension(350, entrelayout);
		txt1.setFont(fonNumber);
		txt1.setForeground(Color.WHITE);
		txt1.setPreferredSize(preferredSize);
		panel2.add(txt1);

		txt1 = new JLabel(" ", SwingConstants.CENTER);
		txt1.setPreferredSize(preferredSize);
		panel2.add(txt1);

		txt1 = new JLabel(m_model.statistique.getJoueur2_Bonus() + "", SwingConstants.CENTER);
		preferredSize = new Dimension(350, entrelayout);
		txt1.setFont(fonNumber);
		txt1.setForeground(Color.WHITE);

		txt1.setPreferredSize(preferredSize);
		panel2.add(txt1);

		txt1 = new JLabel(" ", SwingConstants.CENTER);
		txt1.setPreferredSize(preferredSize);
		panel2.add(txt1);

		txt1 = new JLabel(m_model.statistique.getNombre_zbire2() + "", SwingConstants.CENTER);
		preferredSize = new Dimension(350, entrelayout);
		txt1.setForeground(Color.WHITE);

		txt1.setFont(fonNumber);
		txt1.setPreferredSize(preferredSize);
		panel2.add(txt1);

		preferredSize = new Dimension(350, 600);
		panel2.setPreferredSize(preferredSize);
		panel2.setOpaque(false);
		main.add(panel2, BorderLayout.EAST);

		// Mis en place du panneau central
		JPanel panel_center = new JPanel();
		panel_center.setLayout(new BorderLayout());

		JPanel pan_graph = new JPanel();
		pan_graph.setLayout(new FlowLayout());
		
		JLabel stat= new JLabel("",SwingConstants.CENTER);
		preferredSize = new Dimension(510, 80);

		stat.setPreferredSize(preferredSize);
		stat.setFont(fonNumber);
		pan_graph.add(stat);

		Graphs graphs = new Graphs();
		graphs.set_model(m_model);
		preferredSize = new Dimension(510, 340);
		graphs.setPreferredSize(preferredSize);
		// graphs.setOpaque(false);
		pan_graph.add(graphs);

		
		JLabel titre  = new JLabel("Graphique du score en fonction du temps",SwingConstants.CENTER);
		preferredSize =new Dimension(510, 40);

		titre.setPreferredSize(preferredSize);
		titre.setForeground(Color.WHITE);
		pan_graph.add(titre, preferredSize);
		pan_graph.setOpaque(false);

		panel_center.add(pan_graph, BorderLayout.CENTER);

		JButton rejouer = new JButton("");
		preferredSize = new Dimension(200, 70);
		rejouer.setPreferredSize(preferredSize);
		rejouer.setBorderPainted(false);
		rejouer.setContentAreaFilled(false);
		rejouer.setFocusPainted(false);
		panel_center.add(rejouer, BorderLayout.SOUTH);
		rejouer.addActionListener(this);
		m_rejouer = rejouer;
		panel_center.setOpaque(false);
		main.add(panel_center, BorderLayout.CENTER);

		this.add(main);

		this.doLayout();
		this.setResizable(false);
		this.setVisible(true);

		// hook window events so that we exit the Java Platform
		// when the window is closed by the end user.
		this.addWindowListener(new WindowListener(m_model));

		this.setLocationRelativeTo(null);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if (s == m_rejouer) {
			Sounds.clic_sound();
			new GameUI(d);
			dispose();
		}
	}
	public void windowClosing(WindowEvent e) {
		m_model.shutdown();
		System.exit(0);
	}

}
