
public final class IdeeImpl implements Idee {
	
	private static final long serialVersionUID = -215990646577487111L;

	private final String nom;
	
	private final String proprietaire;
	
	public IdeeImpl(final String n, final String p) {
		super();
		this.nom = n;
		this.proprietaire = p;
	}
	
	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public String getProprietaire() {
		return this.proprietaire;
	}
	
	public String toString() {
		return this.nom + " - " + this.proprietaire;
	}

}
