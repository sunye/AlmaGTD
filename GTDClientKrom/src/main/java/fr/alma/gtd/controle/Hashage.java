package fr.alma.gtd.controle;

//Start of user code Hashage.import
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//End of user code

/**
 * Classe Hashage.
 */
public class Hashage {

	/**
	 * Constructeur.
	 */
	public Hashage() {
	}
			
	/**
	 * Methode hasher.
	 */
	public static String hasher(String chaine){
		//Start of user code Hashage.hasher
		byte[] bytes = chaine.getBytes();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(bytes);
			bytes = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String resultat = new String(bytes);
		return resultat;
		//End of user code
	}
	
}