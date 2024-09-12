package univ.ics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import univ.util.Tools;

/**
 * Classe permettant de charger le texte d'un ICS à partir d'un fichier local ou
 * d'une URL. Les données sont retournées sous forme d'un tableau de lignes
 *
 * @author Noémi Salaün, Joseph Lark
 */
public class ICSFinder {

	/**
	 * Récupère un ICS en local à partir du chemin du fichier
	 *
	 * @param path Le chemin du fichier ICS
	 * @return Un ArrayList contenant chaque ligne de l'ICS
	 * @throws FileNotFoundException
	 * @throws IOException  
	 */
	public static ArrayList<String> getLocal(String path) throws FileNotFoundException, IOException {
		return Tools.readFile(path);
	}

	/**
	 *
	 * Récupère un ICS sur Internet à partir d'une URL
	 *
	 * @param path L'adresse du fichier ICS
	 * @return Un ArrayList contenant chaque ligne de l'ICS
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static ArrayList<String> getURL(String path) throws MalformedURLException, IOException {
		ArrayList<String> ret = new ArrayList<>();

		URL u = new URL(path);
		InputStreamReader isr = new InputStreamReader(u.openStream());
		BufferedReader br = new BufferedReader(isr);

		Scanner scanner = new Scanner(br);
		while (scanner.hasNextLine()) {
			ret.add(scanner.nextLine());
		}

		return ret;
	}
}
