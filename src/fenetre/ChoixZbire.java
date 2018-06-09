package fenetre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import edu.ricm3.game.GameUI;
import mvc.MesOptions;
import ricm3.parser.Ast.AI_Definitions;
import ricm3.parser.Ast.Automaton;
import ricm3.parser.AutomataParser;

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
	String fichier;
	JPanel img;

	public ChoixZbire(Dimension d, GameUI game) {
		JPanel eastPanel = new JPanel();
		JPanel westPanel = new JPanel();
		eastPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
		westPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
		img = new Background(d, 7);
		fichier = "automata.txt";

		// on rafraichit les 8 menu deroulant, en fonction du fichier choisi (par defaut
		// : automata.txt
		refreshAutomate(fichier, eastPanel, westPanel);
		this.d = d;
		m_game = game;
		this.setTitle("COLORicm Deluxe Version 2.0");
		Container cont = this.getContentPane();
		cont.setSize(d);
		cont.setPreferredSize(d);
		cont.setMaximumSize(d);
		cont.setMinimumSize(d);

		img.setLayout(null);

		home = new JButton();
		home.setBounds(-120, 10, 400, 80);
		home.setOpaque(false);
		home.setContentAreaFilled(false);
		home.setBorderPainted(false);

		home.addActionListener(this);

		this.add(home);

		eastPanel.setBounds(160, 200, 300, 350);
		eastPanel.setOpaque(false);

		westPanel.setBounds(730, 200, 300, 350);
		westPanel.setOpaque(false);

		// choix fichier

		File repertoire = new File(".");
		FilenameFilter filter = new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		};
		File[] files = repertoire.listFiles(filter);
		JComboBox menu_fichier = new JComboBox<>(files);

		menu_fichier.setBounds(500, 120, 200, 35);
		img.add(menu_fichier);
		menu_fichier.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// fichier =(String)menu_fichier.getItemAt(menu_fichier.getSelectedIndex());
				Object o = menu_fichier.getItemAt(menu_fichier.getSelectedIndex());
				fichier = o.toString();
				System.out.println(fichier);
				refreshAutomate(fichier, eastPanel, westPanel);

			}
		});

		// Textfield Joueur 1

		j1 = new JTextField(MesOptions.nom_j1);
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

		j2 = new JTextField(MesOptions.nom_j2);
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


	void refreshAutomate(String file, JPanel eastPanel, JPanel westPanel) {
		try {
			// Liste des noms des automates
			automate = new LinkedList<String>();
			if (MesOptions.deja_parse) // si on a deja parse un fichier, il faut reinitialiser le parser
				AutomataParser.ReInit(new BufferedReader(new FileReader(fichier)));
			else // on cr�e une nouvelle instance du parser, si l'on ne l'a jamais fait
				new AutomataParser(new BufferedReader(new FileReader(fichier)));
			// On lance le parser
			AI_Definitions def = (AI_Definitions) AutomataParser.Run();
			// list contient la liste de tout les automates pars�
			LinkedList<Automaton> list = (LinkedList<Automaton>) def.getAutomata();
			Iterator<Automaton> iter = list.iterator();
			while (iter.hasNext()) {
				// on ajoute a la liste d'automate leur noms
				automate.add(iter.next().getName().getValue());
			}
			if (automate.size() < 4) {
				System.out.println("Veuillez mettre au moins 4 automates");
				throw new ricm3.parser.ParseException();
			}

			// Affichage des 8 menus deroulant
			if (MesOptions.deja_parse) {
				// on supprime tout les menu deroulants
				eastPanel.removeAll();
				westPanel.removeAll();
			}
			Dimension dimcombo = new Dimension(200, 35);
			JComboBox comboBox[] = new JComboBox[8];
			// on recr�e a chaque fois les menu deroulants (on aurait pu supprimer manuellement leur contenu)
			for (int i = 0; i < 8; i++) {
				comboBox[i] = new JComboBox();
				for (int j = 0; j < automate.size(); j++) {
					comboBox[i].addItem(automate.get(j));
				}
				int x = i % 4;
				comboBox[i].setSelectedIndex(i % 4);

			}

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
			MesOptions.deja_parse = true;
			this.validate();

		} catch (ricm3.parser.ParseException p) {
			System.out.println("L'automate ne respecte pas la grammaire, ou le fichier est invalide.\n"
					+ "Veuillez corriger vos automates, ou choisir un fichier valide");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

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
