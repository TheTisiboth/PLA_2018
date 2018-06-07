package fenetre;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ricm3.game.GameController;
import edu.ricm3.game.GameModel;
import edu.ricm3.game.GameUI;
import edu.ricm3.game.GameView;

public class CreditsWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel main;
	GameView m_view;
	JLabel m_text;
	GameModel m_model;
	GameController m_controller;
	JButton rules;
	Dimension d;
	GameUI m_game;

	public CreditsWindow(Dimension d, GameUI game) {

		this.d = d;
		m_game = game;

		this.setTitle("COLORicm Deluxe Version 2.0");
		this.setSize(d);
		this.setPreferredSize(d);
		this.setLayout(new BorderLayout());

		JPanel img = new Background(d,4);

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
		//this.addWindowListener(new WindowListener(m_model));

		this.pack();
		this.setLocationRelativeTo(null);

	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		if (d == null) {
			return;
		}
		if ((width != 1200) || (height != 600)) {
			new CreditsWindow(d, m_game);
			dispose();
		}
	}

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		if ((width != 1200) || (height != 600)) {
			new CreditsWindow(d, m_game);
			dispose();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();

		if (s == rules) {
			new HomeWindow(d, m_game);
			dispose();
		}

	}

}
