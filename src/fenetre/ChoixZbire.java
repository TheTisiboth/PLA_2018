package fenetre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.ricm3.game.GameUI;

public class ChoixZbire extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField j1;
	private String nom_j1;
	private JTextField j2;
	private String nom_j2;

	public ChoixZbire(Dimension d, GameUI game) {
		this.setTitle("COLORicm Deluxe Version 2.0");
		Container cont = this.getContentPane();
		cont.setSize(d);
		cont.setPreferredSize(d);
		cont.setMaximumSize(d);
		cont.setMinimumSize(d);
		
		
        

		JPanel img = new Background(d, 4);
		img.setLayout(null);

		JPanel eastPanel = new JPanel();
		
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

		JComboBox comboBox[] = new JComboBox[8];
		for (int i = 0; i < 8; i++) {
			comboBox[i] = new JComboBox();

		}
		int y = 200;
		for (int i = 0; i < 4; i++) {
			comboBox[i].setBounds(100, y, 100, 60);
			y += 60;
			eastPanel.add(comboBox[i]);
		}
		// this.add(comboBox);

		img.add(eastPanel);
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
