package mvc;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Statistique {
	private float[] score_j1, score_j2;
	private int nb_sbires_j1, nb_sbires_j2;
	private int nb_cases_parcourues_j1, nb_cases_parcourues_j2;
	private int nb_bonus_j1, nb_bonus_j2;
	private int index;

	// constructor 
	public Statistique() {
		score_j1 = new float[((MesOptions.min * 60) / 10) + 1]; // score of player 1
		score_j2 = new float[((MesOptions.min * 60) / 10) + 1]; // score of player 2

		score_j1[0] = 0; // init
		score_j2[0] = 0; // init

		nb_sbires_j1 = 0; // countor for the number of sbires invoked (player 1)
		nb_sbires_j2 = 0; // countor for the number of sbires invoked (player 2)
		nb_cases_parcourues_j1 = 0; // number of cases runned (player 1)
		nb_cases_parcourues_j2 = 0; // number of cases runned (player 2)
		nb_bonus_j1 = 0; // number of bonus taken (player 1)
		nb_bonus_j2 = 0; // number of bonus taken (player 2)
		index = 1; // index of the array
	}

	public float[] getScore_joueur1() {
		return score_j1;
	}

	public void plus_Score_joueur1(float score_joueur1) {
		this.score_j1[index] = (score_joueur1 / MesOptions.nb_cases) * 100;
	}

	public float[] getScore_joueur2() {
		return score_j2;
	}

	public void plus_Score_joueur2(float score_joueur2) {
		this.score_j2[index] = (score_joueur2 / MesOptions.nb_cases) * 100;
	}

	public int getNombre_zbire1() {
		return nb_sbires_j1;
	}

	public void plus_Nombre_zbire1() {
		this.nb_sbires_j1++;
	}

	public int getNombre_zbire2() {
		return nb_sbires_j2;
	}

	public void plus_Nombre_zbire2() {
		this.nb_sbires_j2++;
	}

	public int getNbcases_parcourues1() {
		return nb_cases_parcourues_j1;
	}

	public void plus_Nbcases_parcourues1() {
		this.nb_cases_parcourues_j1 = nb_cases_parcourues_j1 + 1;
	}

	public int getNbcases_parcourues2() {
		return nb_cases_parcourues_j2;
	}

	public void plus_Nbcases_parcourues2() {
		this.nb_cases_parcourues_j2 = nb_cases_parcourues_j2 + 1;
	}

	public int getJoueur1_Bonus() {
		return nb_bonus_j1;
	}

	public void plus_Joueur1_Bonus() {
		this.nb_bonus_j1 = nb_bonus_j1 + 1;
	}

	public int getJoueur2_Bonus() {
		return nb_bonus_j2;
	}

	public void plus_Joueur2_Bonus() {
		this.nb_bonus_j2 = nb_bonus_j2 + 1;
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
		g2.drawString("Pourcentage (%)", 20, 80);
		g2.draw(new Line2D.Double(50, 100, 50, 300));
		g2.drawString("Temps (s)", 400, 330);
		g2.draw(new Line2D.Double(50, 300, 450, 300));

		// arrows for axis
		g2.draw(new Line2D.Double(50, 100, 45, 105));
		g2.draw(new Line2D.Double(50, 100, 55, 105));
		g2.draw(new Line2D.Double(450, 300, 445, 295));
		g2.draw(new Line2D.Double(450, 300, 445, 305));
		g2.setStroke(new BasicStroke(3));

		for (int i = 0; i < score_j1.length - 1; i++) {
			g2.setColor(Color.RED);
			g2.draw(new Line2D.Double(50 + i * 400 / (score_j1.length - 1), 300 - score_j1[i] * 2,
					50 + (i + 1) * 400 / (score_j1.length - 1), 300 - score_j1[i + 1] * 2));
			g2.setColor(Color.BLUE);
			g2.draw(new Line2D.Double(50 + i * 400 / (score_j1.length - 1), 300 - score_j2[i] * 2,
					50 + (i + 1) * 400 / (score_j1.length - 1), 300 - score_j2[i + 1] * 2));
		}
	}
}
