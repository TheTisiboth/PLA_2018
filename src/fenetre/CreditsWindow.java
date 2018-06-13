package fenetre;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ricm3.game.GameController;
import edu.ricm3.game.GameModel;
import edu.ricm3.game.GameUI;
import edu.ricm3.game.GameView;
import edu.ricm3.game.WindowListener;
import mvc.Model;
import mvc.Sounds;

public class CreditsWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel main;
	GameView m_view;
	JLabel m_text;
	GameModel m_model;
	GameController m_controller;
	JButton return_button;
	Dimension d;
	GameUI m_game;

	public CreditsWindow(Dimension d, GameUI game) {
	
		this.d = d;
		m_game = game;

		this.setTitle("COLORicm Deluxe Version 2.0");
		Container cont = this.getContentPane();
		cont.setSize(d);
		cont.setPreferredSize(d);
		cont.setMaximumSize(d);
		cont.setMinimumSize(d);
		
		JPanel img = new Background(d, 4);
		
		// Début bouton "Rules"

		return_button = new JButton();
		return_button.setBounds(-120, 10, 400, 80);
		return_button.setOpaque(false);
		return_button.setContentAreaFilled(false);
		return_button.setBorderPainted(false);

		return_button.addActionListener(this);

		img.add(return_button);

		// Fin Bouton "Rules"

		// On ajoute le tout dans la fenetre
		cont.add(img, BorderLayout.CENTER);

		this.setSize(d);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.addWindowListener(new WindowListener(new Model(1, 2)));

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

		if (s == return_button) {
			Sounds.clic_sound();
			new HomeWindow(d, m_game);
			dispose();
		}

	}
	
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

}
