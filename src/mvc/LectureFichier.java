package mvc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.geometry.Side;

public class LectureFichier {

	public static void ecrire(String fichier, String s, boolean append) {

		try { // append : ajoute a la fin du fichier, sinon ça écrase le reste
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fichier, !append)));

			// on ecrit la string dans le fichier
			pw.println(s);

			pw.close();
		} catch (IOException exception) {
			System.out.println("Erreur lors de la lecture : " + exception.getMessage());
		}

	}

	public static ArrayList<String> lecture_automata(String fichier) {
		// Tab contient les automates présent dans le fichier, sous forme de
		// String
		ArrayList<String> tab = new ArrayList<String>();
		// lecture du fichier texte
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne = "";
			String automate = null;
			boolean Accolade_ouvrante = false, Accolade_fermante = false, not_end = true;
			// tant qu'on est pas arrivé a la fin du fichier
			while (not_end) {
				automate = "";
				Accolade_ouvrante = false;
				Accolade_fermante = false;
				// c'est la fin quand on lit null => fin de fichier
				// ou alors si on a trouvé une accolade fermante et ouvrante =>
				// fin de l'automate
				while (ligne != null && (!Accolade_fermante || !Accolade_ouvrante)) {

					if (not_end) {
						// automate contient tout l'automate (on y a joute ligne
						// par ligne
						automate += ligne + '\n';
						for (int i = 0; i < ligne.length(); i++) {
							// on vérifie si une accolade est presente
							if (ligne.charAt(i) == '{')
								Accolade_ouvrante = true;
							if (ligne.charAt(i) == '}')
								Accolade_fermante = true;
						}
					}
					// si ce n'est pas la fin, on relit une nouvelle ligne
					if (not_end)
						ligne = br.readLine();
					if (ligne == null)
						not_end = false;
				}
				// une fois tout un automate lu, on l'ajoute au tableau
				tab.add(automate);

			}
			br.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return tab;
	}

}
