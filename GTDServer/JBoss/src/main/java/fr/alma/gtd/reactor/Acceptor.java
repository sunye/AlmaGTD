package fr.alma.gtd.reactor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Acceptor.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public final class Acceptor {
	
	/**
	 * L'instance de l'acceptor.
	 */
	private static Acceptor instance = new Acceptor();
	
	/**
	 * La map des jetons actifs et de l'identifiant correspondant.
	 */
	private Map<String, String> jetonActif;
	
	/**
	 * Le constructeur.
	 */
	private Acceptor() {
		super();
		this.jetonActif = new ConcurrentHashMap<String, String>();
	}
	
	/**
	 * Prend le jeton d'identification, et renvoie l'identifiant correspondant.
	 * @param jeton Le jeton d'identification.
	 * @return L'identifiant correspondant.
	 */
	public String decode(final String jeton) {
		return this.jetonActif.get(jeton);
	}
	
	/**
	 * Enregistre un nouveau jeton d'identification.
	 * @param jeton Le jeton.
	 * @return Indique si le jeton n'etait pas deja present.
	 */
	public boolean enregistre(final String jeton) {
		boolean result = false;
		
		if (!this.jetonActif.keySet().contains(this.encode(jeton))) {
			this.jetonActif.put(this.encode(jeton), jeton);
			result = true;
		}
		
		return result;
	}
	
	/**
	 * Supprimer un jeton d'identification.
	 * @param jeton Le jeton.
	 * @return Indique si le jeton etait deja present.
	 */
	public boolean retire(final String jeton) {
		boolean result = false;
		
		if (this.jetonActif.keySet().contains(this.encode(jeton))) {
			this.jetonActif.remove(this.encode(jeton));
			result = true;
		}
		
		return result;
	}
	
	/**
	 * Accepte la connection.
	 * @param jeton Le jeton d'identification.
	 * @return Indique si la personne est connectee.
	 */
	public boolean accept(final String jeton) {
		return this.jetonActif.keySet().contains(this.encode(jeton));
	}
	
	/**
	 * @return L'instance de l'acceptor.
	 */
	public static Acceptor getInstance() {
		return instance;
	}
	
	/**
	 * Permet de encoder en SHA-512 le string.
	 * @param str Le string a encoder.
	 * @return Le string encoder.
	 */
	public String encode(final String str) {
		String hash = "hash SHA-512 invalide";
		
		MessageDigest digest = null;
		
		try {
			digest = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e1) {
			System.out.println(">>Algorithme SHA-512 inconnu");
			e1.printStackTrace();
		}
		
		InputStream is = null;
		
		try {
            is = new ByteArrayInputStream(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
		
		final byte[] buffer = new byte[8192];
		int read = 0;
		try {
			while ((read = is.read(buffer)) > 0) {
				digest.update(buffer, 0, read);
			}		
			final byte[] shasum = digest.digest();
			final BigInteger bigInt = new BigInteger(1, shasum);
			hash = bigInt.toString(16);
		}
		catch (IOException e) {
			throw new RuntimeException(">>Impossible d'appliquer l'algorithme SHA-512", e);
		}
		finally {
			try {
				is.close();
			}
			catch (IOException e) {
				throw new RuntimeException(">>Impossible de fermer le flux d'entree pour le calcul SHA-512", e);
			}
		}
		
		return hash;
	}
	
}
