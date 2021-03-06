package donneespartagees;


/**
 * donneespartagees/_IIdeeStub.java .
 * Generated by the IDL-to-Java compiler (portable), version "3.2"
 * from C:/Users/Florian/workspace2/alma-gtd/idl/donneespartagees.idl
 * mercredi 6 janvier 2010 22 h 46 CET
 */


/**
 * Interface representant les idees.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public class _IIdeeStub extends org.omg.CORBA.portable.ObjectImpl implements donneespartagees.IIdee
{

	public String Nom ()
	{
		org.omg.CORBA.portable.InputStream $in = null;
		try {
			org.omg.CORBA.portable.OutputStream $out = _request ("_get_Nom", true);
			$in = _invoke ($out);
			String $result = $in.read_string ();
			return $result;
		} catch (org.omg.CORBA.portable.ApplicationException $ex) {
			$in = $ex.getInputStream ();
			String _id = $ex.getId ();
			throw new org.omg.CORBA.MARSHAL (_id);
		} catch (org.omg.CORBA.portable.RemarshalException $rm) {
			return Nom (        );
		} finally {
			_releaseReply ($in);
		}
	} // Nom

	public void Nom (String newNom)
	{
		org.omg.CORBA.portable.InputStream $in = null;
		try {
			org.omg.CORBA.portable.OutputStream $out = _request ("_set_Nom", true);
			$out.write_string (newNom);
			$in = _invoke ($out);
			return;
		} catch (org.omg.CORBA.portable.ApplicationException $ex) {
			$in = $ex.getInputStream ();
			String _id = $ex.getId ();
			throw new org.omg.CORBA.MARSHAL (_id);
		} catch (org.omg.CORBA.portable.RemarshalException $rm) {
			Nom (newNom        );
		} finally {
			_releaseReply ($in);
		}
	} // Nom

	public String Description ()
	{
		org.omg.CORBA.portable.InputStream $in = null;
		try {
			org.omg.CORBA.portable.OutputStream $out = _request ("_get_Description", true);
			$in = _invoke ($out);
			String $result = $in.read_string ();
			return $result;
		} catch (org.omg.CORBA.portable.ApplicationException $ex) {
			$in = $ex.getInputStream ();
			String _id = $ex.getId ();
			throw new org.omg.CORBA.MARSHAL (_id);
		} catch (org.omg.CORBA.portable.RemarshalException $rm) {
			return Description (        );
		} finally {
			_releaseReply ($in);
		}
	} // Description

	public void Description (String newDescription)
	{
		org.omg.CORBA.portable.InputStream $in = null;
		try {
			org.omg.CORBA.portable.OutputStream $out = _request ("_set_Description", true);
			$out.write_string (newDescription);
			$in = _invoke ($out);
			return;
		} catch (org.omg.CORBA.portable.ApplicationException $ex) {
			$in = $ex.getInputStream ();
			String _id = $ex.getId ();
			throw new org.omg.CORBA.MARSHAL (_id);
		} catch (org.omg.CORBA.portable.RemarshalException $rm) {
			Description (newDescription        );
		} finally {
			_releaseReply ($in);
		}
	} // Description

	public boolean DansLaPoubelle ()
	{
		org.omg.CORBA.portable.InputStream $in = null;
		try {
			org.omg.CORBA.portable.OutputStream $out = _request ("_get_DansLaPoubelle", true);
			$in = _invoke ($out);
			boolean $result = $in.read_boolean ();
			return $result;
		} catch (org.omg.CORBA.portable.ApplicationException $ex) {
			$in = $ex.getInputStream ();
			String _id = $ex.getId ();
			throw new org.omg.CORBA.MARSHAL (_id);
		} catch (org.omg.CORBA.portable.RemarshalException $rm) {
			return DansLaPoubelle (        );
		} finally {
			_releaseReply ($in);
		}
	} // DansLaPoubelle

	public void DansLaPoubelle (boolean newDansLaPoubelle)
	{
		org.omg.CORBA.portable.InputStream $in = null;
		try {
			org.omg.CORBA.portable.OutputStream $out = _request ("_set_DansLaPoubelle", true);
			$out.write_boolean (newDansLaPoubelle);
			$in = _invoke ($out);
			return;
		} catch (org.omg.CORBA.portable.ApplicationException $ex) {
			$in = $ex.getInputStream ();
			String _id = $ex.getId ();
			throw new org.omg.CORBA.MARSHAL (_id);
		} catch (org.omg.CORBA.portable.RemarshalException $rm) {
			DansLaPoubelle (newDansLaPoubelle        );
		} finally {
			_releaseReply ($in);
		}
	} // DansLaPoubelle

	public donneespartagees.IParticipant Createur ()
	{
		org.omg.CORBA.portable.InputStream $in = null;
		try {
			org.omg.CORBA.portable.OutputStream $out = _request ("_get_Createur", true);
			$in = _invoke ($out);
			donneespartagees.IParticipant $result = donneespartagees.IParticipantHelper.read ($in);
			return $result;
		} catch (org.omg.CORBA.portable.ApplicationException $ex) {
			$in = $ex.getInputStream ();
			String _id = $ex.getId ();
			throw new org.omg.CORBA.MARSHAL (_id);
		} catch (org.omg.CORBA.portable.RemarshalException $rm) {
			return Createur (        );
		} finally {
			_releaseReply ($in);
		}
	} // Createur

	public void Createur (donneespartagees.IParticipant newCreateur)
	{
		org.omg.CORBA.portable.InputStream $in = null;
		try {
			org.omg.CORBA.portable.OutputStream $out = _request ("_set_Createur", true);
			donneespartagees.IParticipantHelper.write ($out, newCreateur);
			$in = _invoke ($out);
			return;
		} catch (org.omg.CORBA.portable.ApplicationException $ex) {
			$in = $ex.getInputStream ();
			String _id = $ex.getId ();
			throw new org.omg.CORBA.MARSHAL (_id);
		} catch (org.omg.CORBA.portable.RemarshalException $rm) {
			Createur (newCreateur        );
		} finally {
			_releaseReply ($in);
		}
	} // Createur


	/**
	 * Supprime l'id�e, en la mettant a la poubelle.
	 */
	public void mettreALaPoubelle ()
	{
		org.omg.CORBA.portable.InputStream $in = null;
		try {
			org.omg.CORBA.portable.OutputStream $out = _request ("mettreALaPoubelle", true);
			$in = _invoke ($out);
			return;
		} catch (org.omg.CORBA.portable.ApplicationException $ex) {
			$in = $ex.getInputStream ();
			String _id = $ex.getId ();
			throw new org.omg.CORBA.MARSHAL (_id);
		} catch (org.omg.CORBA.portable.RemarshalException $rm) {
			mettreALaPoubelle (        );
		} finally {
			_releaseReply ($in);
		}
	} // mettreALaPoubelle


	/**
	 * Restaurer la tache depuis la poubelle.
	 */
	public void restaurer ()
	{
		org.omg.CORBA.portable.InputStream $in = null;
		try {
			org.omg.CORBA.portable.OutputStream $out = _request ("restaurer", true);
			$in = _invoke ($out);
			return;
		} catch (org.omg.CORBA.portable.ApplicationException $ex) {
			$in = $ex.getInputStream ();
			String _id = $ex.getId ();
			throw new org.omg.CORBA.MARSHAL (_id);
		} catch (org.omg.CORBA.portable.RemarshalException $rm) {
			restaurer (        );
		} finally {
			_releaseReply ($in);
		}
	} // restaurer

	public String IdentifiantServeur ()
	{
		org.omg.CORBA.portable.InputStream $in = null;
		try {
			org.omg.CORBA.portable.OutputStream $out = _request ("_get_IdentifiantServeur", true);
			$in = _invoke ($out);
			String $result = $in.read_string ();
			return $result;
		} catch (org.omg.CORBA.portable.ApplicationException $ex) {
			$in = $ex.getInputStream ();
			String _id = $ex.getId ();
			throw new org.omg.CORBA.MARSHAL (_id);
		} catch (org.omg.CORBA.portable.RemarshalException $rm) {
			return IdentifiantServeur (        );
		} finally {
			_releaseReply ($in);
		}
	} // IdentifiantServeur

	public void IdentifiantServeur (String newIdentifiantServeur)
	{
		org.omg.CORBA.portable.InputStream $in = null;
		try {
			org.omg.CORBA.portable.OutputStream $out = _request ("_set_IdentifiantServeur", true);
			$out.write_string (newIdentifiantServeur);
			$in = _invoke ($out);
			return;
		} catch (org.omg.CORBA.portable.ApplicationException $ex) {
			$in = $ex.getInputStream ();
			String _id = $ex.getId ();
			throw new org.omg.CORBA.MARSHAL (_id);
		} catch (org.omg.CORBA.portable.RemarshalException $rm) {
			IdentifiantServeur (newIdentifiantServeur        );
		} finally {
			_releaseReply ($in);
		}
	} // IdentifiantServeur

	public String DateDeDerniereModification ()
	{
		org.omg.CORBA.portable.InputStream $in = null;
		try {
			org.omg.CORBA.portable.OutputStream $out = _request ("_get_DateDeDerniereModification", true);
			$in = _invoke ($out);
			String $result = $in.read_string ();
			return $result;
		} catch (org.omg.CORBA.portable.ApplicationException $ex) {
			$in = $ex.getInputStream ();
			String _id = $ex.getId ();
			throw new org.omg.CORBA.MARSHAL (_id);
		} catch (org.omg.CORBA.portable.RemarshalException $rm) {
			return DateDeDerniereModification (        );
		} finally {
			_releaseReply ($in);
		}
	} // DateDeDerniereModification

	public void DateDeDerniereModification (String newDateDeDerniereModification)
	{
		org.omg.CORBA.portable.InputStream $in = null;
		try {
			org.omg.CORBA.portable.OutputStream $out = _request ("_set_DateDeDerniereModification", true);
			$out.write_string (newDateDeDerniereModification);
			$in = _invoke ($out);
			return;
		} catch (org.omg.CORBA.portable.ApplicationException $ex) {
			$in = $ex.getInputStream ();
			String _id = $ex.getId ();
			throw new org.omg.CORBA.MARSHAL (_id);
		} catch (org.omg.CORBA.portable.RemarshalException $rm) {
			DateDeDerniereModification (newDateDeDerniereModification        );
		} finally {
			_releaseReply ($in);
		}
	} // DateDeDerniereModification

	// Type-specific CORBA::Object operations
	private static String[] __ids = {
		"IDL:donneespartagees/IIdee:1.0", 
	"IDL:donneespartagees/IObjetServeur:1.0"};

	@Override
	public String[] _ids ()
	{
		return (String[])__ids.clone ();
	}

	private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
	{
		String str = s.readUTF ();
		String[] args = null;
		java.util.Properties props = null;
		org.omg.CORBA.Object obj = org.omg.CORBA.ORB.init (args, props).string_to_object (str);
		org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
		_set_delegate (delegate);
	}

	private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
	{
		String[] args = null;
		java.util.Properties props = null;
		String str = org.omg.CORBA.ORB.init (args, props).object_to_string (this);
		s.writeUTF (str);
	}
} // class _IIdeeStub
