package proto;

import java.awt.*;

import javax.swing.*;

import edu.ricm3.game.GameController;
import edu.ricm3.game.GameModel;
import edu.ricm3.game.GameView;
import edu.ricm3.game.WindowListener;

public class GameWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel main;
	GameView m_view;
	JLabel m_text;
	GameModel m_model;
	GameController m_controller;

	public GameWindow(Dimension d, GameController ctrl, GameView view, GameModel mod, String j1, String j2) {

		m_model = mod;
		m_view = view;
		m_controller = ctrl;

		this.setTitle("Sample Game");
		this.setLayout(new BorderLayout());
		this.setSize(d);

		// Création du Label contenant l'image de fond, la view et tout les composants
		JLabel img = new JLabel(new ImageIcon("images/game.png"));
		img.setLayout(null);

		// On met la view au Centre
		m_view.setBounds(120, 120, 960, 480);
		img.add(m_view);

		JPanel Main = new JPanel(); //Panel qui contient tout situé au nord
		Main.setLayout(new GridLayout(1,3));

		JPanel Left = new JPanel();
		Left.setLayout(new BorderLayout());
		
		JLabel txt = new JLabel();
		txt.setText(j1);
		txt.setForeground(Color.WHITE);
		txt.setFont(new Font("Helvetica", Font.BOLD, 20));
		txt.setOpaque(false);
		Left.add(txt, BorderLayout.WEST);
		
		JLabel txt2 = new JLabel("Pourcentage");
		txt2.setOpaque(false);
		Left.add(txt2, BorderLayout.EAST);
		
		JPanel LeftCenter = new JPanel();
		LeftCenter.setLayout(null);
	
		JLabel txt3 = new JLabel(new ImageIcon("images/eclair_gauche.png"));

		txt3.setOpaque(false);
		txt3.setBounds(110,0,100,80);
		LeftCenter.add(txt3);
		
		JLabel txt4 = new JLabel(new ImageIcon("images/stop_gauche.png"));
		txt4.setOpaque(false);
		txt4.setBounds(180,100,200,80);
		LeftCenter.add(txt4);
		
		LeftCenter.setOpaque(false);
		Left.add(LeftCenter, BorderLayout.CENTER);
		

		Left.setOpaque(false);
		Main.add(Left);
		
		//
		
		JLabel Center = new JLabel("TIME");
		Center.setOpaque(false);
		Main.add(Center);
		
		
		//
		
		JPanel Right = new JPanel();
		Right.setLayout(new BorderLayout());
		
		JLabel txt5 = new JLabel("Pourcentage");
		txt5.setOpaque(false);
		Right.add(txt5, BorderLayout.WEST);
		
	
		JLabel txt6 = new JLabel();
		txt6.setText(j2);
		txt6.setForeground(Color.WHITE);
		txt6.setFont(new Font("Helvetica", Font.BOLD, 20));
		txt6.setOpaque(false);
		Right.add(txt6, BorderLayout.EAST);
		
		JPanel RightCenter = new JPanel();
		RightCenter.setLayout(null);
		
		JLabel txt7 = new JLabel(new ImageIcon("images/stop_droite.png"));
		txt7.setBounds(-50,0,100,80);
		txt7.setOpaque(false);
		RightCenter.add(txt7);
		
		JLabel txt8 = new JLabel(new ImageIcon("images/eclair_droite.png"));
		txt8.setOpaque(false);
		txt8.setBounds(10,0,100,80);
		RightCenter.add(txt8);
		
		RightCenter.setOpaque(false);
		Right.add(RightCenter, BorderLayout.CENTER);
		
		Right.setOpaque(false);
		Main.add(Right);

		Main.setBounds(0,0,1200,120);	
		Main.setOpaque(false);
		img.add(Main);
		
		// Creation de Panel à l'Est
		JPanel panEast = new JPanel();
		panEast.setLayout(new GridLayout(4, 1));
		

		JButton bE1 = new JButton("EAST 1");
		bE1.setBorderPainted(false);
		bE1.setContentAreaFilled(false);
		bE1.setFocusPainted(false);

		JButton bE2 = new JButton("EAST 2");
		bE2.setBorderPainted(false);
		bE2.setContentAreaFilled(false);
		bE2.setFocusPainted(false);

		JButton bE3 = new JButton("EAST 3");
		bE3.setBorderPainted(false);
		bE3.setContentAreaFilled(false);
		bE3.setFocusPainted(false);

		JButton bE4 = new JButton("EAST 4");
		bE4.setBorderPainted(false);
		bE4.setContentAreaFilled(false);
		bE4.setFocusPainted(false);

		panEast.add(bE1);
		panEast.add(bE2);
		panEast.add(bE3);
		panEast.add(bE4);
		panEast.setOpaque(false);
		panEast.setBounds(0, 120, 120, 480);
		img.add(panEast);

		// Creation de Panel à l'Ouest
		JPanel panWest = new JPanel();
		panWest.setLayout(new GridLayout(4, 1));

		JButton bW1 = new JButton("WEST 1");
		bW1.setBorderPainted(false);
		bW1.setContentAreaFilled(false);
		bW1.setFocusPainted(false);

		JButton bW2 = new JButton("WEST 2");
		bW2.setBorderPainted(false);
		bW2.setContentAreaFilled(false);
		bW2.setFocusPainted(false);

		JButton bW3 = new JButton("WEST 3");
		bW3.setBorderPainted(false);
		bW3.setContentAreaFilled(false);
		bW3.setFocusPainted(false);

		JButton bW4 = new JButton("WEST 4");
		bW4.setBorderPainted(false);
		bW4.setContentAreaFilled(false);
		bW4.setFocusPainted(false);

		panWest.add(bW1);
		panWest.add(bW2);
		panWest.add(bW3);
		panWest.add(bW4);
		panWest.setOpaque(false);
		panWest.setBounds(1080, 120, 120, 480);
		img.add(panWest);

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

}
