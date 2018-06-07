package mvc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


import edu.ricm3.game.*;

public class EndPage extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	JPanel main;
	GameView m_view;
	JLabel m_text;
	Model m_model;
	GameController m_controller;
	JButton rules;
	Dimension d;
	int largeur;
	int hauteur;
	JButton m_rejouer;

	public EndPage(GameModel mod, GameView m_view) {
		largeur = 1200;
		hauteur =600;
		d = new Dimension(largeur, hauteur);
		JPanel panel1= new JPanel();
		panel1.setLayout(new FlowLayout());
		JPanel panel2= new JPanel();
		panel2.setLayout(new FlowLayout());
		m_model = (Model)mod;
		this.m_view =m_view;

		this.setTitle("EndPage");
		this.setLayout(new BorderLayout());
		this.setSize(d);
		
		//Création des police d'écriture
		Font font = new Font("Sherif", Font.BOLD, 24);
		Font fonNumber = new Font("Sherif", Font.BOLD, 50);
		
		//Mis en place de l'écran Ouest
		int entrelayout =75;
		JLabel txt= new JLabel("Joueur 1 : ", SwingConstants.CENTER);
		txt.setFont(font);
		Dimension preferredSize = new Dimension(350, 50);
		txt.setPreferredSize(preferredSize);
		panel1.add(txt);
		
		txt= new JLabel("Nombre de case parcourues par le joueur", SwingConstants.CENTER);
		txt.setPreferredSize(preferredSize);
		panel1.add(txt);
		
		txt= new JLabel(""+ m_model.statistique.getNombrecase_parcouru1(), SwingConstants.CENTER);
		preferredSize =new Dimension(350, entrelayout);
		txt.setFont(fonNumber);
		txt.setPreferredSize(preferredSize);
		panel1.add(txt);
		
		
		txt =new JLabel("Nombre de fois que le joueur a été tué ", SwingConstants.CENTER);
		txt.setPreferredSize(preferredSize);
		panel1.add(txt);
		
		txt= new JLabel(""+ m_model.statistique.getJoueur1_kill(), SwingConstants.CENTER);
		preferredSize =new Dimension(350, entrelayout);
		txt.setFont(fonNumber);
		txt.setPreferredSize(preferredSize);
		panel1.add(txt);
		
		txt =new JLabel("Nombre de Zbire invoqués ", SwingConstants.CENTER);
		txt.setPreferredSize(preferredSize);
		panel1.add(txt);
		
		txt= new JLabel(""+ m_model.statistique.getNombre_zbire1(), SwingConstants.CENTER);
		preferredSize =new Dimension(350, entrelayout);
		txt.setFont(fonNumber);
		txt.setPreferredSize(preferredSize);
		panel1.add(txt);
		
		
		preferredSize = new Dimension(350, 600);
		panel1.setPreferredSize(preferredSize);
		this.add(panel1, BorderLayout.WEST);
		
		
		//Mis en place de l'écran Est
		JLabel txt1 = new JLabel("Joueur 2 : ", SwingConstants.CENTER);
		txt1.setFont(font);
		preferredSize = new Dimension(350, 50);
		txt1.setPreferredSize(preferredSize);
		panel2.add(txt1);
		
		txt1 = new JLabel("Nombre de case parcourues par le joueur", SwingConstants.CENTER);
		txt1.setPreferredSize(preferredSize);
		panel2.add(txt1);
		
		txt1= new JLabel(""+ m_model.statistique.getNombrecase_parcouru2(), SwingConstants.CENTER);
		preferredSize =new Dimension(350, entrelayout);
		txt1.setFont(fonNumber);
		txt1.setPreferredSize(preferredSize);
		panel2.add(txt1);
		
		txt1 = new JLabel("Nombre de fois que le joueur a été tué ", SwingConstants.CENTER);
		txt1.setPreferredSize(preferredSize);
		panel2.add(txt1);
		
		txt1= new JLabel(m_model.statistique.getJoueur2_kill()+"", SwingConstants.CENTER);
		preferredSize =new Dimension(350, entrelayout);
		txt1.setFont(fonNumber);
		txt1.setPreferredSize(preferredSize);
		panel2.add(txt1);
		
		txt1 = new JLabel("Nombre de Zbire invoqués ", SwingConstants.CENTER);
		txt1.setPreferredSize(preferredSize);
		panel2.add(txt1);
		
		txt1= new JLabel(m_model.statistique.getNombre_zbire2()+"", SwingConstants.CENTER);
		preferredSize =new Dimension(350, entrelayout);
		txt1.setFont(fonNumber);
		txt1.setPreferredSize(preferredSize);
		panel2.add(txt1);
		
		
		preferredSize = new Dimension(350, 600);
		panel2.setPreferredSize(preferredSize);
		this.add(panel2,BorderLayout.EAST);
		
		
		//Mis en place du panneau central
		JPanel panel_center = new JPanel();
		panel_center.setLayout(new BorderLayout());
		
		JPanel pan_graph = new JPanel();
		pan_graph.setLayout(new FlowLayout());
		
		JLabel stat= new JLabel("STATISTIQUE",SwingConstants.CENTER);
		preferredSize = new Dimension(500, 80);
		stat.setPreferredSize(preferredSize);
		stat.setFont(fonNumber);
		pan_graph.add(stat);
		
		Graphs graphs = new Graphs();
		graphs.set_model(m_model);
		preferredSize = new Dimension(500, 320);

		
		graphs.setPreferredSize(preferredSize);
		pan_graph.add(graphs);
		
		JLabel titre  = new JLabel("Graphique du score en fonction du temps",SwingConstants.CENTER);
		preferredSize =new Dimension(500, 40);
		titre.setPreferredSize(preferredSize);
		pan_graph.add(titre, preferredSize);
		
		panel_center.add(pan_graph, BorderLayout.CENTER);
		
		JButton rejouer = new JButton("Retour à l'accueil");
		preferredSize = new Dimension(200, 40);
		rejouer.setPreferredSize(preferredSize);
		rejouer.setBorderPainted(false);
		rejouer.setContentAreaFilled(false);
		rejouer.setFocusPainted(false);
		panel_center.add(rejouer, BorderLayout.SOUTH);
		rejouer.addActionListener(this);
		m_rejouer =rejouer;
		this.add(panel_center, BorderLayout.CENTER);
		
		

		
		this.doLayout();
		this.setResizable(false);
		this.setVisible(true);
		
		
		// hook window events so that we exit the Java Platform
		// when the window is closed by the end user.
		this.addWindowListener(new WindowListener(m_model));

		this.setLocationRelativeTo(null);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if(s == m_rejouer) {
			dispose();
		}
	}
	


}
