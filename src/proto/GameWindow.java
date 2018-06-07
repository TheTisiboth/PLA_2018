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
	String j1, j2;
	Dimension d;

	public GameWindow(Dimension d, GameController ctrl, GameView view, GameModel mod, String j1, String j2) {
		this.d = d;
		m_model = mod;
		m_view = view;
		m_controller = ctrl;
		this.j1 = j1;
		this.j2 = j2;

		this.setTitle("COLORicm Deluxe Version 2.0");
		this.setSize(d);
		this.setPreferredSize(d);
		this.setLayout(new BorderLayout());

		JPanel img = new Background(d,2);
				
		JPanel north = createNorthPanel();
		north.setOpaque(false);
		JPanel east = createEastPanel();
		east.setOpaque(false);
		JPanel west = createWestPanel();
		west.setOpaque(false);
		
		m_view.setBounds(120, 120, 960, 480);
		north.setBounds(0,0, 1200, 80);
		east.setBounds(1100, 100, 100, 450);
		west.setBounds(0, 100, 100, 450);
		
		img.add(north);
		img.add(m_view);
		img.add(east);
		img.add(west);

		// On ajoute le tout dans la fenetre
		this.add(img, BorderLayout.CENTER);

		//this.setResizable(true);
		this.doLayout();
		this.setVisible(true);

		// hook window events so that we exit the Java Platform
		// when the window is closed by the end user.
		this.addWindowListener(new WindowListener(m_model));
		
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
			new GameWindow(d, m_controller, m_view, m_model, j1, j2);
			dispose();
		}
	}

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		if ((width != 1200) || (height != 600)) {
			new GameWindow(d, m_controller, m_view, m_model, j1, j2);
			dispose();
		}
	}
	
	// ----------------------NORTH PANEL------------------------------//

	// CENTER NORTH
	private JPanel createCenterNorthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(200, 80));
		panel.setOpaque(false);
		panel.setLocation(0, 0);

		JLabel time = new JLabel("<html>3:00</html>");
		time.setForeground(Color.WHITE);
		time.setFont(new Font("Helvetica", Font.BOLD, 40));

		panel.add(time);

		Dimension size = time.getPreferredSize();
		time.setBounds(10, 0, size.width, size.height);

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
		txtPanel.setBounds(320, 0, 160, 80);
		txtPanel.setOpaque(false);

		// NOM DU JOUEUR 1
		JLabel nom_j1 = new JLabel();
		nom_j1.setText(j2);
		nom_j1.setForeground(Color.WHITE);
		nom_j1.setFont(new Font("Helvetica", Font.BOLD, 20));
		nom_j1.setOpaque(false);
		txtPanel.add(nom_j1);
		panel.add(txtPanel);

		// ECLAIR PANEL
		JPanel eclairPanel = new JPanel();
		eclairPanel.setBounds(225, 0, 54, 80);
		eclairPanel.setOpaque(false);

		// ECLAIR
		JLabel img_eclair = new JLabel(new ImageIcon("images/eclair_droite.png"));
		img_eclair.setOpaque(false);
		eclairPanel.add(img_eclair);
		panel.add(eclairPanel);

		// STOP PANEL
		JPanel stopPanel = new JPanel();
		stopPanel.setBounds(150, 0, 75, 80);
		stopPanel.setOpaque(false);

		// STOP
		JLabel img_stop = new JLabel(new ImageIcon("images/stop_droite.png"));
		img_stop.setOpaque(false);
		stopPanel.add(img_stop);
		panel.add(stopPanel);

		// POURCENTAGE PANEL
		JPanel pourcentagePanel = new JPanel();
		pourcentagePanel.setOpaque(false);
		pourcentagePanel.setBounds(20, 0, 100, 80);

		// POURCENTAGE
		JLabel pourcentage = new JLabel("15");
		pourcentage.setOpaque(false);
		pourcentage.setForeground(Color.WHITE);
		pourcentage.setFont(new Font("Helvetica", Font.BOLD, 40));
		pourcentagePanel.add(pourcentage);
		panel.add(pourcentagePanel);

		return panel;
	}

	// WEST NORTH OK
	private JPanel createWestNorthPanel() {

		// MAIN PANEL
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 500, 80);
		panel.setOpaque(false);

		// NOM DU JOUEUR 1 PANEL
		JPanel txtPanel = new JPanel();
		txtPanel.setBounds(0, 0, 160, 80);
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
		JLabel img_eclair = new JLabel(new ImageIcon("images/eclair_gauche.png"));
		img_eclair.setOpaque(false);
		eclairPanel.add(img_eclair);
		panel.add(eclairPanel);

		// STOP PANEL
		JPanel stopPanel = new JPanel();
		stopPanel.setBounds(275, 0, 75, 80);
		stopPanel.setOpaque(false);

		// STOP
		JLabel img_stop = new JLabel(new ImageIcon("images/stop_gauche.png"));
		img_stop.setOpaque(false);
		stopPanel.add(img_stop);
		panel.add(stopPanel);

		// POURCENTAGE PANEL
		JPanel pourcentagePanel = new JPanel();
		pourcentagePanel.setOpaque(false);
		pourcentagePanel.setBounds(380, 0, 100, 80);

		// POURCENTAGE
		JLabel pourcentage = new JLabel("15");
		pourcentage.setOpaque(false);
		pourcentage.setForeground(Color.WHITE);
		pourcentage.setFont(new Font("Helvetica", Font.BOLD, 40));
		pourcentagePanel.add(pourcentage);
		panel.add(pourcentagePanel);

		return panel;
	}

	// NORTH OK
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1200, 100);

		JPanel WestNorth = createWestNorthPanel();
		WestNorth.setBounds(0, 0, 500, 80);
		JPanel CenterNorth = createCenterNorthPanel();
		CenterNorth.setBounds(500, 0, 200, 80);
		JPanel EastNorth = createEastNorthPanel();
		EastNorth.setBounds(700, 0, 500, 80);

		panel.add(WestNorth, BorderLayout.WEST);
		panel.add(CenterNorth, BorderLayout.CENTER);
		panel.add(EastNorth, BorderLayout.EAST);

		panel.setOpaque(false);

		return panel;
	}

	// OK
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

	// OK

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
