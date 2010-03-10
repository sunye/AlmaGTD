package fr.alma.gtd.sessions;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IParticipant;
import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.donneesserveur.Contexte;
import fr.alma.gtd.donneesserveur.Tache;
import fr.alma.gtd.isessions.IContexteServiceRemote;
import fr.alma.gtd.isessions.IParticipantServiceRemote;
import fr.alma.gtd.isessions.ITacheServiceRemote;

public class TestTacheService {
	public static void main(String[] args) {
		try {
			
			final Properties env = new Properties();
			env.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
			env.setProperty("java.naming.provider.url", "localhost:1099");
			env.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming"); 
			
			Context context = new InitialContext(env);
			IParticipantServiceRemote serviceP = (IParticipantServiceRemote) context.lookup("ParticipantService/remote");
			IContexteServiceRemote serviceCtx = (IContexteServiceRemote) context.lookup("ContexteService/remote");
			ITacheServiceRemote service = (ITacheServiceRemote) context.lookup("TacheService/remote");
			
			service.removeAll();
			
			IContexte ctx = serviceCtx.creerContexte(new Contexte("Contexte1"));
			
			IParticipant p = null;
			IParticipant p2 = null;
			
			List<IParticipant> lp = serviceP.getParticipantByPseudo("Stef44");
			for(IParticipant participant : lp){
				p = participant;
			}
			lp = serviceP.getParticipantByPseudo("Stef55");
			for(IParticipant participant : lp){
				p2 = participant;
			}
		
			ITache myTache = new Tache("Tache1",0,1,ctx,p);
			service.creerTache(myTache);
			myTache = new Tache("Tache2-stef44",0,1,ctx,p);
			service.creerTache(myTache);
			myTache = new Tache("Tache1",0,1,ctx,p2);
			service.creerTache(myTache);
			myTache = new Tache("Tache2-stef55",0,1,ctx,p2);
			service.creerTache(myTache);

			System.out.println("Taches creees par 'Stef44' ");
			List<ITache> lt = service.getTacheByCreator(p.getIdentifiantServeur());
			for(ITache Tache : lt){
				System.out.println(Tache.getIdentifiantServeur()+ " - "+ Tache.getNom()+ " - " + Tache.getCreateur().getPseudonyme());
				myTache = Tache;
			}
			
			//myTache.setDescription("Description Updated");
			//service.updateTache(myTache.getIdentifiantServeur(), myTache);
			
			System.out.println("Taches creees par 'Stef55' ");
			lt = service.getTacheByCreator(p2.getIdentifiantServeur());
			for(ITache Tache : lt){
				System.out.println(Tache.getIdentifiantServeur()+ " - "+ Tache.getNom()+ " - " + Tache.getCreateur().getPseudonyme());
			}
			
			System.out.println("Taches dont le nom vaut 'Tache1' ");
			lt = service.getTacheByName("Tache1");
			for(ITache Tache : lt){
				System.out.println(Tache.getIdentifiantServeur()+ " - "+ Tache.getNom()+ " - "  + " - " + Tache.getCreateur().getPseudonyme());
			}
			

			
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
}
