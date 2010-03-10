package fr.alma.gtd.sessions;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import fr.alma.gtd.donneespartagees.IIdee;
import fr.alma.gtd.donneespartagees.IParticipant;
import fr.alma.gtd.isessions.IIdeeServiceRemote;
import fr.alma.gtd.isessions.IParticipantServiceRemote;

public class TestIdeeService {
	public static void main(String[] args) {
		try {
			
			IIdee myIdee = null;
			
			final Properties env = new Properties();
			env.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
			env.setProperty("java.naming.provider.url", "localhost:1099");
			env.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming"); 
			
			Context context = new InitialContext(env);
			IParticipantServiceRemote serviceP = (IParticipantServiceRemote) context.lookup("ParticipantService/remote");
			
			IIdeeServiceRemote service = (IIdeeServiceRemote) context.lookup("IdeeService/remote");
			
			//service.removeAll();
			
			IParticipant stef44 = serviceP.getParticipantByPseudo("Stef44").get(0);
			IParticipant stef55 = serviceP.getParticipantByPseudo("Stef55").get(0);
			
			//service.creerIdee(new Idee("Idee1","My Idee1 ",stef44));
			
			//service.creerIdee(new Idee("Idee2-ByStef44", "MyIdee2-ByStef44",stef44));
			//service.creerIdee(new Idee("Idee1","My Idee1 ",stef55));
			//service.creerIdee(new Idee("Idee2-ByStef5", "MyIdee2-ByStef55",stef55));

			System.out.println("Idees creees par 'Stef44' ");
			List<IIdee> lt = service.getIdeeByCreator(stef44.getIdentifiantServeur());
			for(IIdee idee : lt){
				System.out.println(idee.getIdentifiantServeur()+ " - "+ idee.getNom()+ " - " + idee.getDescription() + " - " + idee.getCreateur().getPseudonyme());
				myIdee = idee;
			}
			
			//myIdee.setDescription("Description Updated");
			//service.updateIdee(myIdee.getIdentifiantServeur(), myIdee);
			
			System.out.println("Idees creees par 'Stef55' ");
			lt = service.getIdeeByCreator(stef55.getIdentifiantServeur());
			for(IIdee idee : lt){
				System.out.println(idee.getIdentifiantServeur()+ " - "+ idee.getNom()+ " - " + idee.getDescription() + " - " + idee.getCreateur().getPseudonyme());
			}
			
			System.out.println("Idees dont le nom vaut 'Idee1' ");
			lt = service.getIdeeByName("Idee1");
			for(IIdee idee : lt){
				System.out.println(idee.getIdentifiantServeur()+ " - "+ idee.getNom()+ " - " + idee.getDescription() + " - " + idee.getCreateur().getPseudonyme());
			}
			

			
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
}
