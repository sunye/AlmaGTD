package fr.alma.gtd.sessions;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import fr.alma.gtd.donneespartagees.Avancement;
import fr.alma.gtd.donneespartagees.IContact;
import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IParticipant;
import fr.alma.gtd.donneespartagees.IProjet;
import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.donneesserveur.Contexte;
import fr.alma.gtd.donneesserveur.Projet;
import fr.alma.gtd.isessions.IContactServiceRemote;
import fr.alma.gtd.isessions.IContexteServiceRemote;
import fr.alma.gtd.isessions.IParticipantServiceRemote;
import fr.alma.gtd.isessions.IProjetServiceRemote;
import fr.alma.gtd.isessions.ITacheServiceRemote;

public class TestProjetService {
	public static void main(String[] args) {
		try {
			
			final Properties env = new Properties();
			env.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
			env.setProperty("java.naming.provider.url", "localhost:1099");
			env.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming"); 
			
			Context context = new InitialContext(env);
			IParticipantServiceRemote serviceP = (IParticipantServiceRemote) context.lookup("ParticipantService/remote");
			IContexteServiceRemote serviceCtx = (IContexteServiceRemote) context.lookup("ContexteService/remote");
			IProjetServiceRemote service = (IProjetServiceRemote) context.lookup("ProjetService/remote");
			ITacheServiceRemote serviceTache  = (ITacheServiceRemote) context.lookup("TacheService/remote");
			IContactServiceRemote serviceContact = (IContactServiceRemote) context.lookup("ContactService/remote");
			
			service.removeAll();
			serviceCtx.removeAll();
			IContexte ctx = serviceCtx.creerContexte(new Contexte("Contexte1"));
			IContexte ctx2 = serviceCtx.creerContexte(new Contexte("Contexte2"));
			
			List<IContact> listeContact = serviceContact.getAllContact();
			
			IParticipant p = null;
			IParticipant p2 = null;
			
			List<IParticipant> lpart = serviceP.getAllParticipant();
			List<IParticipant> lp = serviceP.getParticipantByPseudo("Stef44");
			for(IParticipant participant : lp){
				p = participant;
			}
			lp = serviceP.getParticipantByPseudo("Stef55");
			for(IParticipant participant : lp){
				p2 = participant;
			}
		
			ITache t1 = serviceTache.getTacheByCreator(p.getIdentifiantServeur()).get(0);
			ITache t2 = serviceTache.getTacheByCreator(p2.getIdentifiantServeur()).get(0);
			
			IProjet myProjet = new Projet("Projet1",ctx,p);
			myProjet = service.creerProjet(myProjet);
			myProjet.ajoutTache(t1);
			myProjet.ajoutTache(t2);
			myProjet.setAvancement(Avancement.AFAIRE);
			myProjet.setListeContacts(listeContact);
			myProjet.setListeDeParticipants(lpart);
			service.updateProjet(myProjet.getIdentifiantServeur(), myProjet);
			List<IProjet> lProjet = new ArrayList<IProjet>();
			lProjet.add(myProjet);
			
			
			myProjet = new Projet("Projet2-stef44",ctx2,p);
			myProjet = service.creerProjet(myProjet);
			myProjet.ajoutTache(t1);
			myProjet.ajoutTache(t2);
			myProjet.setAvancement(Avancement.AFAIRE);
			myProjet.setListeContacts(listeContact);
			myProjet.setListeDeParticipants(lpart);
			myProjet.setListeDeSousProjets(lProjet);
			service.updateProjet(myProjet.getIdentifiantServeur(), myProjet);
			myProjet = new Projet("Projet1",ctx,p2);
			service.creerProjet(myProjet);
			myProjet = new Projet("Projet2-stef55",ctx2,p2);
			service.creerProjet(myProjet);

			System.out.println("Projets creees par 'Stef44' ");
			List<IProjet> lt = service.getProjetByCreator(p.getIdentifiantServeur());
			for(IProjet Projet : lt){
				System.out.println(Projet.getIdentifiantServeur()+ " - "+ Projet.getNom()+ " - " + Projet.getCreateur().getPseudonyme());
				myProjet = Projet;
				
				System.out.println("Avancement : "+Projet.getAvancement()+" Ctx par def : "+Projet.getContexteParDefaut()+" Date de mod : "+Projet.getDateDeDerniereModification());
				
				System.out.println("Sous-projets : ");
				for(IProjet pr : myProjet.getListeDeSousProjets()){
					System.out.println(pr.getIdentifiantServeur()+ " - "+ pr.getNom()+ " - " + pr.getCreateur().getPseudonyme());
				}
				
				System.out.println("Taches incluses: ");
				for(ITache t : myProjet.getListeDeTaches()){
					System.out.println(t.getIdentifiantServeur()+ " - "+ t.getNom()+ " - " + t.getCreateur().getPseudonyme());
				}
				
				System.out.println("Contacts: ");
				for(IContact t : myProjet.getListeContacts()){
					System.out.println(t.getIdentifiantServeur()+ " - "+ t.getNom());
				}
				
				System.out.println("Participants : ");
				for(IParticipant t : myProjet.getListeDeParticipants()){
					System.out.println(t.getIdentifiantServeur()+ " - "+ t.getPseudonyme());
				}
			}
			
			//myProjet.setDescription("Description Updated");
			//service.updateProjet(myProjet.getIdentifiantServeur(), myProjet);
			
			System.out.println("Projets creees par 'Stef55' ");
			lt = service.getProjetByCreator(p2.getIdentifiantServeur());
			for(IProjet Projet : lt){
				System.out.println(Projet.getIdentifiantServeur()+ " - "+ Projet.getNom()+ " - " + Projet.getCreateur().getPseudonyme());
			}
			
			System.out.println("Projets dont le nom vaut 'Projet1' ");
			lt = service.getProjetByName("Projet1");
			for(IProjet Projet : lt){
				System.out.println(Projet.getIdentifiantServeur()+ " - "+ Projet.getNom()+ " - "  + " - " + Projet.getCreateur().getPseudonyme());
			}
			

			
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
}
