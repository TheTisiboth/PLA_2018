package mvc;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Statistique {
	private float[] score_joueur1;
	private float[] score_joueur2;
	private int nb_sbires1, nb_sbires2;
	private int nb_cases_parcourues1, nb_cases_parcourues2;
	private int joueur1_kill, joueur2_kill;
	private int index;

	public Statistique() {
		score_joueur1 = new float[((MesOptions.min * 60) / 10) + 1];
		score_joueur2 = new float[((MesOptions.min * 60) / 10) + 1];
		score_joueur1[0] = 0;
		score_joueur1[0] = 0;

		nb_sbires1 = 0;
		nb_sbires2 = 0;
		nb_cases_parcourues1 = 0;
		nb_cases_parcourues2 = 0;
		joueur1_kill = 0;
		joueur2_kill = 0;
		index = 1;
	}

	public float[] getScore_joueur1() {
		return score_joueur1;
	}

	public void plus_Score_joueur1(float score_joueur1) {
		this.score_joueur1[index] = (score_joueur1 / MesOptions.nb_cases) * 100;
	}

	public float[] getScore_joueur2() {
		return score_joueur2;
	}

	public void plus_Score_joueur2(float score_joueur2) {
		this.score_joueur2[index] = (score_joueur2 / MesOptions.nb_cases) * 100;
	}

	public int getNombre_zbire1() {
		return nb_sbires1;
	}

	public void plus_Nombre_zbire1() {
		this.nb_sbires1++;
	}

	public int getNombre_zbire2() {
		return nb_sbires2;
	}

	public void plus_Nombre_zbire2() {
		this.nb_sbires2++;
	}

	public int getNombrecase_parcouru1() {
		return nb_cases_parcourues1;
	}

	public void plus_Nombrecase_parcouru1() {
		this.nb_cases_parcourues1 = nb_cases_parcourues1 + 1;
	}

	public int getNombrecase_parcouru2() {
		return nb_cases_parcourues2;
	}

	public void plus_Nombrecase_parcouru2() {
		this.nb_cases_parcourues2 = nb_cases_parcourues2 + 1;
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
		
		// creation of lines behind the graph
		g2.setColor(Color.GRAY);
		for (int i = 1; i < 10; i++) {
			g2.draw(new Line2D.Double(50 + (40 * i), 100, 50 + (40 * i), 300));
			g2.drawString("" + (time * i) + "s", 40 + (40 * i), 315);
			g2.draw(new Line2D.Double(50, 300 - (20 * i), 450, 300 - (20 * i)));
			g2.drawString("" + 10 * i + "%", 20, 300 - (20 * i));

		}

		g2.setColor(Color.BLACK);
		// creation of axis
		g2.setStroke(new BasicStroke(4));

		g2.drawString("pourcentage (%)", 20, 80);
		g2.draw(new Line2D.Double(50, 100, 50, 300));
		g2.drawString("temps (s)", 400, 330);
		g2.draw(new Line2D.Double(50, 300, 450, 300));

		// arrows for axis

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
