package mvc;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Statistique {
	private float[] score_joueur1;
	private float[] score_joueur2;
	private int nombre_zbire1, nombre_zbire2;
	private int nombrecase_parcouru1, nombrecase_parcouru2;
	private int joueur1_kill, joueur2_kill;
	private int index;

	public Statistique() {
		score_joueur1 = new float[((MesOptions.min * 60) / 10) + 1];
		score_joueur2 = new float[((MesOptions.min * 60) / 10) + 1];
		score_joueur1[0] = 0;
		score_joueur1[0] = 0;

		nombre_zbire1 = 0;
		nombre_zbire2 = 0;
		nombrecase_parcouru1 = 0;
		nombrecase_parcouru2 = 0;
		joueur1_kill = 0;
		joueur2_kill = 0;
		index = 1;
	}

	public float[] getScore_joueur1() {
		return score_joueur1;
	}

	public void plus_Score_joueur1(float score_joueur1) {
		this.score_joueur1[index] = (score_joueur1 / MesOptions.nombre_case) * 100;
	}

	public float[] getScore_joueur2() {
		return score_joueur2;
	}

	public void plus_Score_joueur2(float score_joueur2) {
		this.score_joueur2[index] = (score_joueur2 / MesOptions.nombre_case) * 100;
	}

	public int getNombre_zbire1() {
		return nombre_zbire1;
	}

	public void plus_Nombre_zbire1() {
		this.nombre_zbire1++;
	}

	public int getNombre_zbire2() {
		return nombre_zbire2;
	}

	public void plus_Nombre_zbire2() {
		this.nombre_zbire2++;
	}

	public int getNombrecase_parcouru1() {
		return nombrecase_parcouru1;
	}

	public void plus_Nombrecase_parcouru1() {
		this.nombrecase_parcouru1 = nombrecase_parcouru1 + 1;
	}

	public int getNombrecase_parcouru2() {
		return nombrecase_parcouru2;
	}

	public void plus_Nombrecase_parcouru2() {
		this.nombrecase_parcouru2 = nombrecase_parcouru2 + 1;
	}

	public int getJoueur1_Bonus() {
		return joueur1_kill;
	}

	public void plus_Joueur1_Bonus() {
		this.joueur1_kill = joueur1_kill + 1;
	}

	public int getJoueur2_Bonus() {
		return joueur2_kill;
	}

	public void plus_Joueur2_Bonus() {
		this.joueur2_kill = joueur2_kill + 1;
	}

	public void plus_index() {
		index++;
	}

	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		int time = MesOptions.min * 60 / 10;
		// création du quadrillage
		g2.setColor(Color.GRAY);
		for (int i = 1; i < 10; i++) {
			g2.draw(new Line2D.Double(50 + (40 * i), 100, 50 + (40 * i), 300));
			g2.drawString("" + (time * i) + "s", 40 + (40 * i), 315);
			g2.draw(new Line2D.Double(50, 300 - (20 * i), 450, 300 - (20 * i)));
			g2.drawString("" + 10 * i + "%", 20, 300 - (20 * i));

		}


		g2.setColor(Color.BLACK);
		// Création des axes
		g2.setStroke(new BasicStroke(4));

		g2.drawString("pourcentage (%)", 20, 80);
		g2.draw(new Line2D.Double(50, 100, 50, 300));
		g2.drawString("temps (s)", 400, 330);
		g2.draw(new Line2D.Double(50, 300, 450, 300));

		// Fleche pour axe

		g2.draw(new Line2D.Double(50, 100, 45, 105));
		g2.draw(new Line2D.Double(50, 100, 55, 105));
		g2.draw(new Line2D.Double(450, 300, 445, 295));
		g2.draw(new Line2D.Double(450, 300, 445, 305));

		g2.setStroke(new BasicStroke(3));

		for (int i = 0; i < score_joueur1.length - 1; i++) {

			g2.setColor(Color.RED);
			g2.draw(new Line2D.Double(50 + i * 400 / (score_joueur1.length - 1), 300 - score_joueur1[i] * 2,
					50 + (i + 1) * 400 / (score_joueur1.length - 1), 300 - score_joueur1[i + 1] * 2));
			g2.setColor(Color.BLUE);
			g2.draw(new Line2D.Double(50 + i * 400 / (score_joueur1.length - 1), 300 - score_joueur2[i] * 2,
					50 + (i + 1) * 400 / (score_joueur1.length - 1), 300 - score_joueur2[i + 1] * 2));

		}

	}

}
