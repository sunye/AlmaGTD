package fr.alma.modele.noyau;

/**
 * Classe Frequence représentant la répétition d'une tache
 * @author Université de Nantes
 * @version 1.0
 * @since 2009
 */
public class Frequence extends EntiteGTD implements Comparable<Frequence> {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = 4138644392016828190L;
	
	/**
	 *le nom de la fréquence.
	 */
	private String nom;
	
	/**
	 * Enumération
	 */
	public static final Frequence EVENEMENT_PONCTUEL = new Frequence("Ponctuel");
	public static final Frequence JOURNALIERE = new Frequence("Journalière");
	public static final Frequence HEBDOMADAIRE = new Frequence("Hebdomadaire");
	public static final Frequence MENSUELLE = new Frequence("Mensuelle");

	
	private static int nextOrdinal = 0;
	private static int ordinal = nextOrdinal++;
	
	/**
	 * Constructeur.
	 */
	private Frequence(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return nom;
	}

	@SuppressWarnings("static-access")
	@Override
	public int compareTo(Frequence autreFreq) {
		return ordinal - autreFreq.ordinal;
	}

	/**
	 * récupère les différentes valeurs de la fréquence.
	 * Constructeur.
	 */
	public static Frequence[] values() {
		Frequence[] f = {EVENEMENT_PONCTUEL, JOURNALIERE, HEBDOMADAIRE, MENSUELLE};
		return f;
	}

	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}