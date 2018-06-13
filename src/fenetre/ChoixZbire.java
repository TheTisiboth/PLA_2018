package fenetre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.ricm3.game.GameUI;
import mvc.LectureFichier;
import mvc.MesOptions;
import mvc.Sounds;
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
	ArrayList<String> noms_automate, noms_automate_tmp;
	ArrayList<String> automate;
	String fichier;
	JPanel img;
	JComboBox comboBox[];
	JComboBox menu_fichier;
	int showMsgError;

	public ChoixZbire(Dimension d, GameUI game) {
		showMsgError = 0; // évite d'avoir un double affichage de message
							// d'erreur
		final JPanel eastPanel = new JPanel();
		final JPanel westPanel = new JPanel();
		eastPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
		westPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
		// Jpanel img contient l'image de fond
		img = new Background(d, 7);
		comboBox = new JComboBox[8];
		fichier = "save.txt";
		automate = LectureFichier.lecture_automata(fichier);

		// on rafraichit les 8 menus déroulants, en fonction du fichier choisi
		// par defaut : automata.txt

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

		img.add(home);

		eastPanel.setBounds(160, 200, 300, 350);
		eastPanel.setOpaque(false);

		westPanel.setBounds(730, 200, 300, 350);
		westPanel.setOpaque(false);

		// choix fichier

		File repertoire = new File(".");
		FilenameFilter filter = new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt"); // on filtre tout les fichiers
												// .txt
			}
		};
		File[] files = repertoire.listFiles(filter);

		JButton menu = new JButton("Choix robot");
		File repertoireCourant = null;
		try {
			repertoireCourant = new File(".").getCanonicalFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		menu.setBounds(500, 120, 200, 35);
		final JFileChooser fileChooser = new JFileChooser(repertoireCourant);
		FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("TEXT files (*.txt)", "txt");
		fileChooser.addChoosableFileFilter(txtFilter);
		fileChooser.setFileFilter(txtFilter);
		menu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileChooser.showOpenDialog((Component) e.getSource());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						fichier = file.toString();
					} catch (Exception ex) {
						System.out.println("problem accessing file" + file.getAbsolutePath());
					}
				} else {
					System.out.println("File access cancelled by user.");
				}
				refreshAutomate(eastPanel, westPanel);

			}
		});

		img.add(menu);
		// menu_fichier = new JComboBox<>(files); // menu deroulant contenant
		// tout
		// // les fichiers .txt
		//
		// menu_fichier.setBounds(500, 120, 200, 35);
		// menu_fichier.setSelectedItem(fichier);
		// for (int i = 0; i < menu_fichier.getItemCount(); i++) {
		// String s = menu_fichier.getItemAt(i).toString();
		// if ((menu_fichier.getItemAt(i)).toString().contains(fichier))
		// menu_fichier.setSelectedItem(menu_fichier.getItemAt(i));
		// }
		// img.add(menu_fichier);
		// menu_fichier.addItemListener(new ItemListener() {
		//
		// @Override
		// public void itemStateChanged(ItemEvent e) {
		// // lorsque l'on selectionne un nouveau fichier, on actualise
		// Object o = menu_fichier.getItemAt(menu_fichier.getSelectedIndex());
		// fichier = o.toString();
		// menu_fichier.setSelectedItem(fichier);
		// refreshAutomate(eastPanel, westPanel);
		// }
		// });

		refreshAutomate(eastPanel, westPanel);

		// Textfield Joueur 1

		j1 = new JTextField(MesOptions.nom_j1);
		j1.setBounds(160, 80, 300, 100);
		j1.setForeground(Color.WHITE);
		j1.setFont(new Font("Helvetica", Font.BOLD, 20));
		j1.setOpaque(false);
		j1.setBorder(null);
		j1.setHorizontalAlignment(JTextField.CENTER);

		setNom_j1(j1.getText());

		j1.addActionListener(this);

		img.add(j1);

		// Fin Textfield Jour 1

		// Textfield Joueur 2

		j2 = new JTextField(MesOptions.nom_j2);
		j2.setBounds(735, 80, 300, 100);
		j2.setForeground(Color.WHITE);
		j2.setFont(new Font("Helvetica", Font.BOLD, 20));
		j2.setOpaque(false);
		j2.setBorder(null);
		j2.setHorizontalAlignment(JTextField.CENTER);

		setNom_j2(j2.getText());

		j2.addActionListener(this);

		img.add(j2);

		// Fin Textfield Jour 2

		cont.add(img, BorderLayout.CENTER);

		this.setSize(d);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);

		// hook window events so that we exit the Java Platform
		// when the window is closed by the end user.
		// this.addWindowListener(new WindowListener(m_model));

		this.pack();
		this.setLocationRelativeTo(null);
	}

	void refreshAutomate(JPanel eastPanel, JPanel westPanel) {
		try {
			File monFichier = new File(fichier);
			if (!(monFichier.exists()) || monFichier.length() == 0) {
				fichier = "automata.txt";
			}
			noms_automate_tmp = new ArrayList<String>();
			noms_automate = new ArrayList<String>();

			// Contient tout les automtates de fichier
			automate = LectureFichier.lecture_automata(fichier);
			if (MesOptions.deja_parse)
				// si on a deja parse un fichier, il faut reinitialiser le
				// parser
				AutomataParser.ReInit(new BufferedReader(new FileReader(fichier)));
			else
				// on crée une nouvelle instance du parser, si l'on ne l'a
				// jamais fait
				new AutomataParser(new BufferedReader(new FileReader(fichier)));
			MesOptions.deja_parse = true;
			// On lance le parser
			AI_Definitions def = (AI_Definitions) AutomataParser.Run();
			// list contient la liste de tout les automates parsé
			LinkedList<Automaton> list = (LinkedList<Automaton>) def.getAutomata();
			Iterator<Automaton> iter = list.iterator();
			while (iter.hasNext()) {
				// on ajoute a la liste d'automate leur noms
				noms_automate.add(iter.next().getName().getValue());
			}
			for (int i = 0; i < noms_automate.size(); i++) {
				if (!noms_automate_tmp.contains(noms_automate.get(i)))
					// noms_automate_tmp contient les noms d'automates a ajouter
					// dans les menu deroulant
					noms_automate_tmp.add(noms_automate.get(i));

			}

			if (noms_automate.size() < 1) {
				String s = "Veuillez mettre au moins 1 automates";
				throw new ricm3.parser.ParseException(s);
			}

			// Affichage des 8 menus deroulant
			if (MesOptions.deja_parse) {
				// on supprime tout les menu deroulants
				eastPanel.removeAll();
				westPanel.removeAll();
			}
			Dimension dimcombo = new Dimension(200, 35);

			// on recréee a chaque fois les menu deroulants (on aurait pu
			// supprimer manuellement leur contenu)
			for (int i = 0; i < 8; i++) {
				comboBox[i] = new JComboBox();
				for (int j = 0; j < noms_automate_tmp.size(); j++) {
					comboBox[i].addItem(noms_automate_tmp.get(j));
				}
				comboBox[i].setSelectedItem(noms_automate.get(i % noms_automate.size()));

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

			this.validate();

		} catch (ricm3.parser.ParseException p) {
			if (showMsgError == 0) {
				String s = "L'automate ne respecte pas la grammaire, ou le fichier est invalide.\n"
						+ "Veuillez corriger vos automates, ou choisir un fichier valide.\n";
				if (p.getMessage() == "Veuillez mettre au moins 4 automates")
					s += p.getMessage();

				System.out.println(s);
				for (int i = 0; i < comboBox.length; i++) {
					if (comboBox != null && comboBox[i] != null)
						comboBox[i].setEnabled(false);
				}
				javax.swing.JOptionPane.showMessageDialog(null, s);

			}
			showMsgError = (showMsgError + 1) % 2;
			// p.printStackTrace();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if (s == home) {
			Sounds.clic_sound();
			if (comboBox[0].isEnabled()) {
				// si les boutons sont activé => fichier valide => on sauvegarde
				// dans save.txt
				boolean premiere_iteration = true;
				for (int i = 0; i < 8; i++) {
					// sauvegarde dans le fichier save.txt des noms des
					// automates choisis
					String aut_name = comboBox[i].getSelectedItem().toString();
					int indice = -1, j = 0;
					while (j < automate.size() && indice == -1) {
						if (automate.get(j).contains(aut_name))
							indice = j;
						j++;
					}
					// On réécrit l'automate complet
					LectureFichier.ecrire("save.txt", automate.get(indice), premiere_iteration);
					premiere_iteration = false;
				}
			}
			// si les boutons sont desactivé, c'est que le fichier etait
			// invalide, on garde alors save.txt inchangé
			new HomeWindow(d, m_game);
			dispose();
		}

		// Récupération des noms des joueurs
		if (s == j1) {
			j1.setText(j1.getText());
			MesOptions.nom_j1 = j1.getText();
		}
		if (s == j2) {
			j2.setText(j2.getText());
			MesOptions.nom_j2 = j2.getText();
		}
	}

	public String getNom_j1() {
		return nom_j1;
	}

	public void setNom_j1(String nom_j1) {
		this.nom_j1 = nom_j1;
	}

	public String getNom_j2() {
		return nom_j2;
	}

	public void setNom_j2(String nom_j2) {
		this.nom_j2 = nom_j2;
	}

}