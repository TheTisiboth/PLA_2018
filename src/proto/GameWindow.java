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

	public GameWindow(Dimension d, GameController ctrl, GameView view, GameModel mod, String j1, String j2) {

		m_model = mod;
		m_view = view;
		m_controller = ctrl;
		this.j1 = j1;
		this.j2 = j2;

		this.setTitle("COLORicm Deluxe Version 2.0");
		this.setSize(d);
		this.setLayout(new BorderLayout());

		// Création du Label contenant l'image de fond, la view et tout les composants
		JLabel img = new JLabel(new ImageIcon("images/game.png"));
		img.setSize(d);
		img.setLayout(null);
		m_view.setBounds(120, 120, 960, 480);

		img.add(createNorthPanel(), BorderLayout.NORTH);
		img.add(m_view);
		img.add(createEastPanel(), BorderLayout.EAST);
		img.add(createWestPanel(), BorderLayout.WEST);

		// On ajoute le tout dans la fenetre
		this.add(img);

		this.doLayout();
		this.setResizable(false);
		this.setVisible(true);

		// hook window events so that we exit the Java Platform
		// when the window is closed by the end user.
		this.addWindowListener(new WindowListener(m_model));

		this.pack();
		this.setLocationRelativeTo(null);

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

		panel.add(bW1);
		panel.add(bW2);
		panel.add(bW3);
		panel.add(bW4);
		panel.setOpaque(false);
		panel.setBounds(1080, 120, 120, 480);

		return panel;
	}

}
