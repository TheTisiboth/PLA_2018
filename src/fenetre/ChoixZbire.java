package fenetre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.ricm3.game.GameUI;
import ricm3.parser.*;
import ricm3.parser.Ast.Automaton;
import ricm3.parser.Ast.Terminal;

public class ChoixZbire extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField j1;
	private String nom_j1;
	private JTextField j2;
	private String nom_j2;
	private JButton home;
	Dimension d;
	GameUI m_game;
	LinkedList<String> automate;

	public ChoixZbire(Dimension d, GameUI game) {
		automate = new LinkedList<String>();
		try {
			// def contient l'AI definition, premier élément de l'ast renvoyé par la fonction from_file
			Ast.AI_Definitions def = (Ast.AI_Definitions) AutomataParser.from_file("automata.txt");
			
			// list contient la liste de tout les automates parsé
			LinkedList<Automaton> list= (LinkedList<Automaton>)def.getAutomata();
			Iterator<Automaton> iter = list.iterator();
			while(iter.hasNext()) {
				// on ajoute a la liste d'automate leur noms
				automate.add(iter.next().getName().getValue());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.d = d;
		m_game = game;
		this.setTitle("COLORicm Deluxe Version 2.0");
		Container cont = this.getContentPane();
		cont.setSize(d);
		cont.setPreferredSize(d);
		cont.setMaximumSize(d);
		cont.setMinimumSize(d);

		JPanel img = new Background(d, 7);
		img.setLayout(null);

		home = new JButton();
		home.setBounds(-120, 10, 400, 80);
		home.setOpaque(false);
		home.setContentAreaFilled(false);
		home.setBorderPainted(false);

		home.addActionListener(this);

		this.add(home);

		JPanel eastPanel = new JPanel();
		eastPanel.setBounds(160, 200, 300, 350);
		eastPanel.setOpaque(false);

		JPanel westPanel = new JPanel();
		westPanel.setBounds(730, 200, 300, 350);
		westPanel.setOpaque(false);

		// Textfield Joueur 1

		j1 = new JTextField("Joueur 1");
		j1.setBounds(160, 80, 300, 100);
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
		j2.setBounds(735, 80, 300, 100);
		j2.setForeground(Color.WHITE);
		j2.setFont(new Font("Helvetica", Font.BOLD, 20));
		j2.setOpaque(false);
		j2.setBorder(null);
		j2.setHorizontalAlignment(JTextField.CENTER);

		nom_j2 = j2.getText();

		j2.addActionListener(this);

		this.add(j2);

		// Fin Textfield Jour 2

		// eastPanel.setLayout(new GridLayout(4, 1, 0, 30));
		eastPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
		westPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
		JComboBox comboBox[] = new JComboBox[8];
		for (int i = 0; i < 8; i++) {
			comboBox[i] = new JComboBox();
			for (int j = 0; j < automate.size(); j++) {
				comboBox[i].addItem(automate.get(j));
			}
			comboBox[i].setSelectedIndex(i%4);

		}
		Dimension dimcombo = new Dimension(200, 35);
		int y = 200;
		for (int i = 0; i < 4; i++) {
			comboBox[i].setPreferredSize(dimcombo);
			comboBox[i].setSize(dimcombo);
			comboBox[i].setMaximumSize(dimcombo);
			comboBox[i].setMinimumSize(dimcombo);
			y += 60;
			eastPanel.add(comboBox[i]);
		}

		y = 200;
		for (int i = 4; i < 8; i++) {
			comboBox[i].setPreferredSize(dimcombo);
			comboBox[i].setSize(dimcombo);
			comboBox[i].setMaximumSize(dimcombo);
			comboBox[i].setMinimumSize(dimcombo);
			y += 60;
			westPanel.add(comboBox[i]);
		}

		img.add(eastPanel);
		img.add(westPanel);
		cont.add(img, BorderLayout.CENTER);

		// this.setSize(d);
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
		Object s = e.getSource();
		if (s == home) {
			new HomeWindow(d, m_game);
			dispose();
		}
	}
}
