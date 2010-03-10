package fr.alma.gtd.sessions;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import fr.alma.gtd.donneespartagees.IParticipant;
import fr.alma.gtd.donneespartagees.ITag;
import fr.alma.gtd.isessions.IParticipantServiceRemote;
import fr.alma.gtd.isessions.ITagServiceRemote;

public class TestTagService {
	public static void main(String[] args) {
		try {
			
			IParticipant myParticipant = null;
			
			final Properties env = new Properties();
			env.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
			env.setProperty("java.naming.provider.url", "localhost:1099");
			env.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming"); 
			
			Context context = new InitialContext(env);
			IParticipantServiceRemote serviceP = (IParticipantServiceRemote) context.lookup("ParticipantService/remote");
			
			ITagServiceRemote service = (ITagServiceRemote) context.lookup("TagService/remote");
			
			//service.removeAll();
			
			IParticipant stef44 = serviceP.getParticipantByPseudo("Stef44").get(0);
			IParticipant stef55 = serviceP.getParticipantByPseudo("Stef55").get(0);
			//ITag t = service.creerTag(new Tag("MyTag1",participant));
			//ITag t2 = service.creerTag(new Tag("MyTag-ByStef44",participant));

			System.out.println("Tag crees par 'Stef44' ");
			List<ITag> lt = service.getTagByCreator(stef44.getIdentifiantServeur());
			for(ITag tag : lt){
				System.out.println("Resultat : "+tag.getIdentifiantServeur() + " - " + tag.getNom() + " - Create by : " + tag.getCreateur().getPseudonyme());
			}
			

			System.out.println("Tag crees par 'Stef55' ");
			lt = service.getTagByCreator(stef55.getIdentifiantServeur());
			for(ITag tag : lt){
				System.out.println("Resultat : "+tag.getIdentifiantServeur() + " - " + tag.getNom() + " - Create by : " + tag.getCreateur().getPseudonyme());
			}
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
}
