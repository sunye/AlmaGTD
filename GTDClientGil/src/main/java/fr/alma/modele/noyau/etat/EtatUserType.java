package fr.alma.modele.noyau.etat;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;


/**
 * La classe EtatUserType permettant de gérer en base les etats d'une tache
 * @author Université de Nantes
 * @version 1.0
 * @since 2009
 */

public class EtatUserType implements UserType {

	//
	public static final int ARCHIVEE_DB_VALUE = 2;
	//
	//
	public static final int EN_COURS_DB_VALUE = 5;
	public static final int SUPPRIMEE_DB_VALUE = 6;
	//

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		// Les Etat sont figés
		return cached;
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		// Les Etat sont figés
		return value;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y)	{
			return true;
		} if (x == null || y == null) {
			return false;
		}
		return x.equals(y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public boolean isMutable() {
		// Les Etat sont stateless, ils sont donc clairement figés (trad.) (?)
		return false;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
	throws HibernateException, SQLException {
		final String stateValue = rs.getString(names[0]);
		if (rs.wasNull()) {
			return null;
		}
		IEtat etat = null;
		try {
			etat = (IEtat) Class.forName(stateValue).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Can't instantiate instance of [" + stateValue + "]", e);
		}
		if (!(etat instanceof IEtat)) {
			throw new RuntimeException("Unknown CustomerState value [" + stateValue + "]");
		}
		return etat;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.VARCHAR);
		} else {
			if (value instanceof Etat) {
		       st.setString(index, value.getClass().getName());
		     } else {
		       throw new RuntimeException("Unknown CustomerState ["
		         + value + "]");
		     }
		}
	}

	@Override
	public Object replace(Object original, Object cible, Object proprietaire) throws HibernateException {
		// Les Etat sont figés
		return original;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class returnedClass() {
		return Etat.class;
	}

	@Override
	public int[] sqlTypes() {
		return new int[] {Types.VARCHAR};
	}

}
