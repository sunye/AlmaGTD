import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Acceptor {
	
	private static Acceptor instance = new Acceptor();
	
	private Map<String, String> jetonActif;
	
	private Acceptor() {
		super();
		this.jetonActif = new ConcurrentHashMap<String, String>();
	}
	
	public void login(final String username, final String password, final CallBack<String> callback) {
		try {
			if ("stephane".equalsIgnoreCase(username) && "stephanepassword".equalsIgnoreCase(password)) {
				this.enregistre("stephanecleuuim");
				callback.onSucces("stephanecleuuim");
			} else {
				if ("alex".equalsIgnoreCase(username) && "alexpassword".equalsIgnoreCase(password)) {
					this.enregistre("alexcleuuim");
					callback.onSucces("alexcleuuim");
				} else {
					callback.onFailure(new Exception("Login error"));
				}
			}
		} catch(RemoteException r) {
			r.printStackTrace();
		}
	}
	
	public String decode(final String jeton) {
		return this.jetonActif.get(jeton);
	}
	
	public boolean enregistre(final String jeton) {
		boolean result = false;
		
		if (!this.jetonActif.keySet().contains(this.encode(jeton))) {
			this.jetonActif.put(this.encode(jeton), jeton);
			result = true;
		}
		
		return result;
	}
	
	public boolean retire(final String jeton) {
		return true;
	}
	
	public boolean accept(final String jeton) {
		// return this.jetonActif.keySet().contains(this.encode(jeton));
		return true;
	}
	
	public static Acceptor getInstance() {
		return instance;
	}
	
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
