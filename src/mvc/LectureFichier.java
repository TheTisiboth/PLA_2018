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

	public static ArrayList<String> lecture(String fichier) {

		ArrayList<String> tab = new ArrayList<String>(4);

		// lecture du fichier texte
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				tab.add(ligne);
			}
			br.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return tab;
	}

	public static void initialisation(String f1, String f2) {
		try {
			String fichier = f1;
			FileWriter fw = new FileWriter(fichier);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter fichierSortie = new PrintWriter(bw);
			fichierSortie.println("PAPA");
			fichierSortie.println("{X}");
			fichierSortie.println("*{H;B}");
			fichierSortie.println("*{H|{X>O}}");
			fichierSortie.println("*{X|O}");
			fichierSortie.close();
			System.out.println("Le fichier " + fichier + " a ete cree!");

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		try {
			String fichier2 = f2;
			FileWriter fw2 = new FileWriter(fichier2);
			BufferedWriter bw2 = new BufferedWriter(fw2);
			PrintWriter fichierSortie2 = new PrintWriter(bw2);
			fichierSortie2.println("MAMAN");
			fichierSortie2.println("*{X;O}");
			fichierSortie2.println("*{H;X}");
			fichierSortie2.println("*{X|O}");
			fichierSortie2.println("*{X|O}");
			fichierSortie2.close();
			System.out.println("Le fichier " + fichier2 + " a ete cree!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				while ((ligne = br.readLine()).equals("")) {

				}
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
