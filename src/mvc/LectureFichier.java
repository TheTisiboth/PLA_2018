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

public class LectureFichier {

	public static void ecrire(String fichier, String s, boolean append) {

		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fichier, !append)));

			pw.println(s);

			pw.close();
		} catch (IOException exception) {
			System.out.println("Erreur lors de la lecture : " + exception.getMessage());
		}

	}

	public static ArrayList<String> lecture_automata(String fichier) {
		ArrayList<String> tab = new ArrayList<String>();
		// lecture du fichier texte
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne = "";
			String automate = null;
			boolean Accolade_ouvrante = false, Accolade_fermante = false, not_end = true;
			while (not_end) {
				automate = "";
				Accolade_ouvrante = false;
				Accolade_fermante = false;
//				while ((ligne = br.readLine()).equals("")) {
//
//				}
				while (ligne != null && (!Accolade_fermante || !Accolade_ouvrante)) {

					if (not_end) {
						automate += ligne + '\n';
						for (int i = 0; i < ligne.length(); i++) {
							if (ligne.charAt(i) == '{')
								Accolade_ouvrante = true;
							if (ligne.charAt(i) == '}')
								Accolade_fermante = true;
						}
					}
					// System.out.println("b");
					if (not_end)
						ligne = br.readLine();
					if (ligne == null)
						not_end = false;
				}
				tab.add(automate);

			}
			br.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return tab;
	}

	public static void main(String args[]) {
		String f1 = "moinsde4automate.txt";
		System.out.println("entree du programme");
		ArrayList<String> tab = lecture_automata(f1);
		System.out.println(tab.toString());

	}

}
