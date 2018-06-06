package proto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import edu.ricm3.game.GameController;
import edu.ricm3.game.GameModel;
import edu.ricm3.game.GameUI;
import edu.ricm3.game.GameView;
import edu.ricm3.game.WindowListener;

public class HomeWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel main;
	JLabel m_text;
	GameController m_controller;
	GameModel m_model;
	GameView m_view;
	Dimension d;

	JTextField j1, j2;
	JButton play, rules, credits;

	String nom_j1, nom_j2;

	public HomeWindow(GameController ctrl, Dimension d, GameModel mod, GameView view) {
		m_model = mod;
		m_view = view;
		this.d = d;
		m_controller = ctrl;

		this.setTitle("Sample Game");
		this.setLayout(new BorderLayout());

		// Création du Label contenant l'image de fond, la view et tout les composants
		JLabel img = new JLabel(new ImageIcon("images/accueil.png"));
		img.setLayout(null);

		this.setSize(d);

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
		this.add(img);

		

		this.setSize(d);
		this.doLayout();
		this.setResizable(false);
		this.setVisible(true);

		// hook window events so that we exit the Java Platform
		// when the window is closed by the end user.
		this.addWindowListener(new WindowListener(m_model));

		this.pack();
		this.setLocationRelativeTo(null);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();

		//Récupération des noms des joueurs
		if (s == j1) {
			j1.setText(j1.getText());
		}
		if (s == j2) {
			nom_j2 = j2.getText();
		}

		//Quand on clique sur le bouton "Play"
		if (s == play) {
			nom_j1 = j1.getText();
			nom_j2 = j2.getText();

			new GameWindow(d, m_controller, m_view, m_model, nom_j1, nom_j2);
			dispose();
		}

		//Quand on clique sur le bouton "Régles"
		if (s == rules) {
			new RulesWindow(d, m_controller, m_view, m_model);
			dispose();
		}
		
		//Quand on clique sur le bouton "Crédits"
		if (s == credits) {
			new CreditsWindow(d, m_controller, m_view, m_model);
			dispose();
		}

	}

}
