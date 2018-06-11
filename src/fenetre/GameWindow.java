package fenetre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import edu.ricm3.game.GameController;
import edu.ricm3.game.GameModel;
import edu.ricm3.game.GameView;
import edu.ricm3.game.WindowListener;
import mvc.Model;

public class GameWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel main_panel;
	GameView m_view;
	GameView time_view;
	JLabel m_text;
	Model m_model;
	GameController m_controller;
	String j1, j2;
	Dimension dimension;
	long last_tick;
	int secondes, minutes;
	public JProgressBar progresseBar1,progresseBar2;
	JPanel image_background;
	public JLabel time, pourcentage1, pourcentage2, img_eclair1, img_eclair2, img_stop1, img_stop2;
	

	public GameWindow(Dimension d, GameController ctrl, GameView view, GameModel mod, String j1, String j2) {
		last_tick = 0L;
		this.dimension = d;
		m_model = (Model) mod;
		m_view = view;
		m_controller = ctrl;
		this.j1 = j1;
		this.j2 = j2;
		m_model.setName_j1(j1);
		m_model.setName_j2(j2);
		time = new JLabel();
		pourcentage1 = new JLabel();
		pourcentage2 = new JLabel();
		
		

		Container cont = this.getContentPane();

		this.setTitle("COLORicm Deluxe Version 2.0");
		cont.setSize(d);
		cont.setPreferredSize(d);
		cont.setMaximumSize(d);
		cont.setMinimumSize(d);

		JPanel img = new Background(d, 2);

		JPanel north = createNorthPanel();
		north.setOpaque(false);
		JPanel east = createEastPanel();
		east.setOpaque(false);
		JPanel west = createWestPanel();
		west.setOpaque(false);
		progresseBar1 = createBarreGauche();
		progresseBar2 = createBarreDroite();


		m_view.setBounds(120, 120, 960, 480);
		north.setBounds(0, 0, 1200, 80);
		east.setBounds(1100, 100, 100, 450);
		west.setBounds(0, 100, 100, 450);
		progresseBar1.setBounds(0,80,600,40);
		progresseBar2.setBounds(600,80,600,40);


		img.add(north);
		img.add(m_view);
		img.add(east);
		img.add(west);
		img.add(progresseBar1);
		img.add(progresseBar2);
		image_background = img;

		// On ajoute le tout dans la fenetre
		cont.add(img, BorderLayout.CENTER);

		this.setResizable(false);
		this.doLayout();
		this.setVisible(true);

		// hook window events so that we exit the Java Platform
		// when the window is closed by the end user.
		this.addWindowListener(new WindowListener(m_model));

		this.pack();
		this.setLocationRelativeTo(null);

		// let's hook the controller,
		// so it gets mouse events and keyboard events.
		m_view.addKeyListener(m_controller);
		m_view.addMouseListener(m_controller);
		m_view.addMouseMotionListener(m_controller);

		// grab the focus on this JPanel, meaning keyboard events
		// are coming to our controller. Indeed, the focus controls
		// which part of the overall GUI receives the keyboard events.
		m_view.setFocusable(true);
		m_view.requestFocusInWindow();

		m_controller.notifyVisible();
	}



	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		if (dimension == null) {
			return;
		}
	}

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
	}

	private JProgressBar createBarreGauche() {
		

		JProgressBar barreJ1 = new JProgressBar();
		barreJ1.setPreferredSize(new Dimension(600,40));
		barreJ1.setMaximumSize(new Dimension(600,40));
		barreJ1.setMinimumSize(new Dimension(600,40));
		barreJ1.setValue(100);
		barreJ1.setForeground(Color.RED);
		barreJ1.setStringPainted(true);
		
		


		return barreJ1;	
	}

	private JProgressBar createBarreDroite() {
	
		JProgressBar barreJ2 = new JProgressBar();
		barreJ2.setPreferredSize(new Dimension(600,40));
		barreJ2.setMaximumSize(new Dimension(600,40));
		barreJ2.setMinimumSize(new Dimension(600,40));
		barreJ2.setValue(100);
		barreJ2.setForeground(Color.BLUE);
		barreJ2.setStringPainted(true);
		
		return barreJ2;
		
	}
	
	
	// ----------------------NORTH PANEL------------------------------//

	// CENTER NORTH
	private JPanel createCenterNorthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setOpaque(false);
		panel.setBounds(0, 20, 200, 80);

		minutes = m_model.getMinutes();
		secondes = m_model.getSecondes();
		time = new JLabel();
		time.setText(minutes + ":0" + secondes);
		time.setForeground(Color.WHITE);
		time.setFont(new Font("Helvetica", Font.BOLD, 40));

		panel.repaint();
		time.repaint();

		Dimension size = time.getPreferredSize();
		time.setBounds(10, 0, size.width, size.height);

		panel.add(time);

		return panel;
	}

	// EAST NORTH
	private JPanel createEastNorthPanel() {

		// MAIN PANEL
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 500, 80);
		panel.setOpaque(false);

		// NOM DU JOUEUR 2 PANEL
		JPanel txtPanel = new JPanel();
		txtPanel.setBounds(320, 25, 160, 80);
		txtPanel.setOpaque(false);

		// NOM DU JOUEUR 2
		JLabel nom_j2 = new JLabel();
		nom_j2.setText(j2);
		nom_j2.setForeground(Color.WHITE);
		nom_j2.setFont(new Font("Helvetica", Font.BOLD, 20));
		nom_j2.setOpaque(false);
		txtPanel.add(nom_j2);
		panel.add(txtPanel);

		// ECLAIR PANEL
		JPanel eclairPanel = new JPanel();
		eclairPanel.setBounds(225, 0, 54, 80);
		eclairPanel.setOpaque(false);

		// ECLAIR
		img_eclair2 = new JLabel(new ImageIcon());
		img_eclair2.setOpaque(false);
		eclairPanel.add(img_eclair2);
		panel.add(eclairPanel);

		// STOP PANEL
		JPanel stopPanel = new JPanel();
		stopPanel.setBounds(150, 0, 75, 80);
		stopPanel.setOpaque(false);

		// STOP
		img_stop2 = new JLabel(new ImageIcon());
		img_stop2.setOpaque(false);
		stopPanel.add(img_stop2);
		panel.add(stopPanel);

		// POURCENTAGE PANEL
		JPanel pourcentagePanel = new JPanel();
		pourcentagePanel.setOpaque(false);
		pourcentagePanel.setBounds(20, 15, 100, 80);

		// POURCENTAGE
		pourcentage1.setText("0");
		pourcentage1.setOpaque(false);
		pourcentage1.setForeground(Color.WHITE);
		pourcentage1.setFont(new Font("Helvetica", Font.BOLD, 40));
		pourcentagePanel.add(pourcentage1);
		panel.add(pourcentagePanel);

		return panel;
	}

	// WEST NORTH
	private JPanel createWestNorthPanel() {

		// MAIN PANEL
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 500, 80);
		panel.setOpaque(false);

		// NOM DU JOUEUR 1 PANEL
		JPanel txtPanel = new JPanel();
		txtPanel.setBounds(0, 25, 160, 80);
		txtPanel.setOpaque(false);

		// NOM DU JOUEUR 1
		JLabel nom_j1 = new JLabel();
		nom_j1.setText(j1);
		nom_j1.setForeground(Color.WHITE);
		nom_j1.setFont(new Font("Helvetica", Font.BOLD, 20));
		nom_j1.setOpaque(false);
		txtPanel.add(nom_j1);
		panel.add(txtPanel);

		// ECLAIR PANEL
		JPanel eclairPanel = new JPanel();
		eclairPanel.setBounds(220, 0, 54, 80);
		eclairPanel.setOpaque(false);

		// ECLAIR
		img_eclair1 = new JLabel(new ImageIcon());
		img_eclair1.setOpaque(false);
		eclairPanel.add(img_eclair1);
		panel.add(eclairPanel);

		// STOP PANEL
		JPanel stopPanel = new JPanel();
		stopPanel.setBounds(275, 0, 75, 80);
		stopPanel.setOpaque(false);

		// STOP
		img_stop1 = new JLabel(new ImageIcon());
		img_stop1.setOpaque(false);
		stopPanel.add(img_stop1);
		panel.add(stopPanel);

		// POURCENTAGE PANEL
		JPanel pourcentagePanel = new JPanel();
		pourcentagePanel.setOpaque(false);
		pourcentagePanel.setBounds(380, 15, 100, 80);

		// POURCENTAGE
		pourcentage2.setText("0");
		pourcentage2.setOpaque(false);
		pourcentage2.setForeground(Color.WHITE);
		pourcentage2.setFont(new Font("Helvetica", Font.BOLD, 40));
		pourcentagePanel.add(pourcentage2);
		panel.add(pourcentagePanel);

		return panel;
	}

	// NORTH
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1200, 100);

		JPanel WestNorth = createWestNorthPanel();
		WestNorth.setBounds(0, 0, 500, 80);
		JPanel CenterNorth = createCenterNorthPanel();
		CenterNorth.setBounds(500, 16, 200, 80);
		JPanel EastNorth = createEastNorthPanel();
		EastNorth.setBounds(700, 0, 500, 80);

		panel.add(WestNorth, BorderLayout.WEST);
		panel.add(CenterNorth, BorderLayout.CENTER);
		panel.add(EastNorth, BorderLayout.EAST);

		panel.setOpaque(false);

		return panel;
	}

	// ----------------------EAST PANEL------------------------------//
	private JPanel createEastPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1));

		JLabel bE1 = new JLabel("EAST 1", SwingConstants.CENTER);

		JLabel bE2 = new JLabel("EAST 2", SwingConstants.CENTER);

		JLabel bE3 = new JLabel("EAST 3", SwingConstants.CENTER);

		JLabel bE4 = new JLabel("EAST 4", SwingConstants.CENTER);

		panel.add(bE1);
		panel.add(bE2);
		panel.add(bE3);
		panel.add(bE4);
		panel.setOpaque(false);
		panel.setBounds(0, 120, 120, 480);

		return panel;

	}

	// ----------------------WEST PANEL------------------------------//

	private JPanel createWestPanel() {

		// Creation de Panel à l'Ouest
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1));

		JLabel bW1 = new JLabel("WEST 1", SwingConstants.CENTER);

		JLabel bW2 = new JLabel("WEST 2", SwingConstants.CENTER);

		JLabel bW3 = new JLabel("WEST 3", SwingConstants.CENTER);

		JLabel bW4 = new JLabel("WEST 4", SwingConstants.CENTER);

		panel.add(bW1);
		panel.add(bW2);
		panel.add(bW3);
		panel.add(bW4);
		panel.setOpaque(false);
		panel.setBounds(1080, 120, 120, 480);

		return panel;
	}


}
