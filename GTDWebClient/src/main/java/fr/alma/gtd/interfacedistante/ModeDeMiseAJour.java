package fr.alma.gtd.interfacedistante;

/**
 * Les differents modes de mise a jour des donnees sur le serveur.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public enum ModeDeMiseAJour {
	
	/**
	 * Force la mise a jour des donnees sur le serveur.
	 * Si des donnees portant le meme identifiant serveur mais ayant une date de
	 * derniere modification plus recente se trouvent sur le serveur, les donnees
	 * (plus recentes) situees sur le serveur seront ecrasees.
	 */
	FORCE,
	
	/**
	 * Realise la mise a jour sans forcer.
	 * Si des donnees portant le meme identifiant serveur mais ayant une date de
	 * derniere modification plus recente se trouvent sur le serveur, les donnees
	 * (plus recentes) situees sur le serveur ne seront pas ecrasees. Un message
	 * d'erreur (onFailure) sera renvoye par le CallBack indiquant que des donnees
	 * plus recentes ont ete synchronisees.
	 */
	WARNING
}
