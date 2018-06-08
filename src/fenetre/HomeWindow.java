package fenetre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class HomeWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel main;
	JLabel m_text;
	Dimension d;

	JTextField j1, j2;
	JButton play, rules, credits,engrenage;

	String nom_j1, nom_j2;

	Model model;
	Controller controller;
	View view;
	Timer m_timer;
	GameUI m_game;

	public HomeWindow(Dimension d, GameUI game) {

		this.d = d;
		m_game = game;

		this.setTitle("COLORicm Deluxe Version 2.0");
		Container cont = this.getContentPane();
		cont.setSize(d);
		cont.setPreferredSize(d);
		cont.setMaximumSize(d);
		cont.setMinimumSize(d);
		// this.setSize(d);
		// this.setPreferredSize(d);
		// this.setLayout(new BorderLayout());

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

		// Engrenage
		engrenage = new JButton();
		engrenage.setBounds(380, 380, 430, 75);
		engrenage.setOpaque(false);
		engrenage.setContentAreaFilled(false);
		engrenage.setBorderPainted(false);

		engrenage.addActionListener(this);

		this.add(engrenage);
		
		// fin engrenage
		
		// Bouton "Click to play"

		play = new JButton();
		play.setBounds(360, 483, 498, 70);
		play.setOpaque(false);
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);

		play.addActionListener(this);

		this.add(play);

		// Fin Bouton "Click to play"

		// Bouton "Rules"

		rules = new JButton();
		rules.setBounds(-120, 520, 300, 50);
		rules.setOpaque(false);
		rules.setContentAreaFilled(false);
		rules.setBorderPainted(false);

		rules.addActionListener(this);

		this.add(rules);

		// Fin Bouton "Rules"

		// Bouton "Crédits"

		credits = new JButton();
		credits.setBounds(980, 520, 300, 50);
		credits.setOpaque(false);
		credits.setContentAreaFilled(false);
		credits.setBorderPainted(false);

		credits.addActionListener(this);

		this.add(credits);

		// Fin Bouton "Crédits"

		// On ajoute le tout dans la fenetre
		// this.add(img);
		cont.add(img, BorderLayout.CENTER);

		this.setSize(d);
		this.doLayout();
		this.setResizable(false);
		this.setVisible(true);

		// hook window events so that we exit the Java Platform
		// when the window is closed by the end user.
		// this.addWindowListener(new WindowListener(m_model));

		this.pack();
		this.setLocationRelativeTo(null);

	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		if (d == null) {
			return;
		}
//		if ((width != 1200) || (height != 600)) {
//			new HomeWindow(d, m_game);
//			dispose();
//		}
	}

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
//		if ((width != 1200) || (height != 600)) {
//			new HomeWindow(d, m_game);
//			dispose();
//		}
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

			model = new Model();
			controller = new Controller(model);
			view = new View(model, controller);
			m_game.setM_controller(controller);
			m_game.setM_model(model);
			m_game.setM_view(view);
			
			model.setM_frame(new GameWindow(d, controller, view, model, nom_j1, nom_j2));
			m_game.createTimer();

			dispose();
		}
		
		// Quand on clique sur le bouton "Chois Automates
		if(s == engrenage){
			new ChoixZbire(d,m_game);
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

	}

}
