package proto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import edu.ricm3.game.Options;
import mvc.Controller;
import mvc.Model;
import mvc.View;

public class HomeWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel main;
	JLabel m_text;
	Dimension d;

	JTextField j1, j2;
	JButton play, rules, credits;

	String nom_j1, nom_j2;
	long m_start;
	long m_elapsed;
	long m_lastRepaint;
	long m_lastTick;
	int m_nTicks;
	int m_fps;
	String m_msg;

	Model model;
	Controller controller;
	View view;
	Timer m_timer;

	public HomeWindow(Dimension d) {

		

		this.d = d;

		this.setTitle("COLORicm Deluxe Version 2.0");
		this.setSize(d);
		this.setPreferredSize(d);
		this.setLayout(new BorderLayout());

		JPanel img = new Background(d, 1);

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

		// Bouton "Click to play"

		play = new JButton();
		play.setBounds(400, 490, 400, 100);
		play.setOpaque(false);
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);

		play.addActionListener(this);

		this.add(play);

		// Fin Bouton "Click to play"

		// Bouton "Rules"

		rules = new JButton();
		rules.setBounds(-120, 520, 300, 80);
		rules.setOpaque(false);
		rules.setContentAreaFilled(false);
		rules.setBorderPainted(false);

		rules.addActionListener(this);

		this.add(rules);

		// Fin Bouton "Rules"

		// Bouton "Crédits"

		credits = new JButton();
		credits.setBounds(980, 520, 300, 80);
		credits.setOpaque(false);
		credits.setContentAreaFilled(false);
		credits.setBorderPainted(false);

		credits.addActionListener(this);

		this.add(credits);

		// Fin Bouton "Crédits"

		// On ajoute le tout dans la fenetre
		this.add(img);

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
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		if (d == null) {
			return;
		}
		if ((width != 1200) || (height != 600)) {
			new HomeWindow(d);
			dispose();
		}
	}

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		if ((width != 1200) || (height != 600)) {
			new HomeWindow(d);
			dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();

		// Récupération des noms des joueurs
		if (s == j1) {
			j1.setText(j1.getText());
		}
		if (s == j2) {
			nom_j2 = j2.getText();
		}

		// Quand on clique sur le bouton "Play"
		if (s == play) {
			nom_j1 = j1.getText();
			nom_j2 = j2.getText();

			// construct the game elements: model, controller, and view.

			model = new Model();
			controller = new Controller(model);
			view = new View(model, controller);
			createTimer();
			new GameWindow(d, controller, view, model, nom_j1, nom_j2);

			dispose();
		}

		// Quand on clique sur le bouton "Régles"
		if (s == rules) {
			new RulesWindow(d);
			dispose();
		}

		// Quand on clique sur le bouton "Crédits"
		if (s == credits) {
			new CreditsWindow(d);
			dispose();
		}

	}

	/*
	 * Let's create a timer, it is the heart of the simulation, ticking periodically
	 * so that we can simulate the passing of time.
	 */
	private void createTimer() {
		int tick = 1; // one millisecond
		m_start = System.currentTimeMillis();
		m_lastTick = m_start;
		m_timer = new Timer(tick, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				tick();
			}
		});
		m_timer.start();
	}

	/*
	 * This is the period tick callback. We compute the elapsed time since the last
	 * tick. We inform the model of the current time.
	 */
	private void tick() {
		long now = System.currentTimeMillis() - m_start;
		long elapsed = (now - m_lastTick);
		m_elapsed += elapsed;
		m_lastTick = now;
		m_nTicks++;
		model.step(now);
		controller.step(now);

		elapsed = now - m_lastRepaint;
		if (elapsed > Options.REPAINT_DELAY) {
			double tick = (double) m_elapsed / (double) m_nTicks;
			long tmp = (long) (tick * 10.0);
			tick = tmp / 10.0;
			m_elapsed = 0;
			m_nTicks = 0;
			String txt = "Tick=" + tick + "ms";
			while (txt.length() < 15)
				txt += " ";
			txt = txt + m_fps + " fps   ";
			while (txt.length() < 25)
				txt += " ";
			if (m_msg != null)
				txt += m_msg;
			// System.out.println(txt);
			// m_text.setText(txt);
			// m_text.repaint();
			// m_view.paint();
			m_lastRepaint = now;
		}
	}

	public void setFPS(int fps, String msg) {
		m_fps = fps;
		m_msg = msg;
	}

}
