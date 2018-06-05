package proto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import edu.ricm3.game.GameController;
import edu.ricm3.game.GameModel;
import edu.ricm3.game.GameView;
import edu.ricm3.game.WindowListener;

public class CreditsWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel main;
	GameView m_view;
	JLabel m_text;
	GameModel m_model;
	GameController m_controller;
	JButton rules;
	Dimension d;

	public CreditsWindow(Dimension d, GameController ctrl, GameView view, GameModel mod) {

		this.d = d;
		m_model = mod;
		m_view = view;
		m_controller = ctrl;

		this.setTitle("Crédits");
		this.setLayout(new BorderLayout());
		this.setSize(d);

		// Création du Label contenant l'image de fond, la view et tout les composants
		JLabel img = new JLabel(new ImageIcon("images/credits.png"));
		img.setLayout(null);

		rules = new JButton();
		rules.setBounds(-120, 10, 400, 80);
		rules.setOpaque(false);
		rules.setContentAreaFilled(false);
		rules.setBorderPainted(false);

		rules.addActionListener(this);

		this.add(rules);

		// Fin Bouton "Rules"

		// On ajoute le tout dans la fenetre
		this.add(img);

		m_text = new JLabel();
		m_text.setText("Starting up...");
		// m_frame.add(m_text, BorderLayout.NORTH);

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

		if (s == rules) {
			HomeWindow r = new HomeWindow(m_controller, d, m_model, m_view);
			dispose();
		}

	}

}
