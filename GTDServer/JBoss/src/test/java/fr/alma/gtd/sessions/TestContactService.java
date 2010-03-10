package fr.alma.gtd.sessions;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import fr.alma.gtd.donneespartagees.IContact;
import fr.alma.gtd.isessions.IContactServiceRemote;

public class TestContactService {
	public static void main(String[] args) {
		try {
			
			IContact myContact = null;
			
			final Properties env = new Properties();
			env.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
			env.setProperty("java.naming.provider.url", "localhost:1099");
			env.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming"); 
			
			Context context = new InitialContext(env);
			IContactServiceRemote service = (IContactServiceRemote) context.lookup("ContactService/remote");
			
			//service.creerContact(new Contact("Stef44","stef.44@gmail.com","5 rue de rien","060701020304"));
			//service.creerContact(new Contact("Stef55","stef.55@gmail.com","5 rue de rien","060701020304"));

			System.out.println("Tous les contacts");
			List<IContact> lp = service.getAllContact();
			for(IContact contact : lp){
				System.out.println("Resultat : "+contact.getNom()+"/"+contact.getEmail()+"/"+contact.getTelephone()+contact.getAdresse());
				contact.setAdresse("6 rue de rien");
				service.updateContact(contact.getIdentifiantServeur(), contact);
			}
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
}
