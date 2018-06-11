package fenetre;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ricm3.game.GameUI;

public class RulesWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel main;
	JLabel m_text;
	JButton rules;
	Dimension d;
	GameUI m_game;

	public RulesWindow(Dimension d, GameUI game) {

		
		// change icon of the frame 
		ImageIcon icon = new ImageIcon("images/item_sbire.png");
		this.setIconImage(icon.getImage());
		
		this.d = d;
		m_game = game;

		this.setTitle("COLORicm Deluxe Version 2.0");
		Container cont = this.getContentPane();
		cont.setSize(d);
		cont.setPreferredSize(d);
		cont.setMaximumSize(d);
		cont.setMinimumSize(d);

		JPanel img = new Background(d, 3);

		rules = new JButton();
		rules.setBounds(-120, 10, 400, 80);
		rules.setOpaque(false);
		rules.setContentAreaFilled(false);
		rules.setBorderPainted(false);

		rules.addActionListener(this);

		this.add(rules);

		// Fin Bouton "Rules"

		// On ajoute le tout dans la fenetre
		cont.add(img, BorderLayout.CENTER);

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

		if (s == rules) {
			new HomeWindow(d, m_game);
			dispose();
		}

	}

}
