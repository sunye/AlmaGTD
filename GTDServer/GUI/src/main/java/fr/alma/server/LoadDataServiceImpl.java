package fr.alma.server;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.alma.client.model.Idee;
import fr.alma.client.model.PieChartData;
import fr.alma.client.model.Projet;
import fr.alma.client.model.ServerData;
import fr.alma.client.model.Stock;
import fr.alma.client.model.Tache;
import fr.alma.client.service.LoadDataService;
import fr.alma.gtd.donneespartagees.IIdee;
import fr.alma.gtd.donneespartagees.IProjet;
import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.interfacedistante.CallBack;
import fr.alma.gtd.interfacedistante.ServeurRMI;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class LoadDataServiceImpl extends RemoteServiceServlet implements
LoadDataService {

	private ServeurRMI serveur;

	private List<Stock> stocks;

	private List<Idee> idees;

	private List<Projet> projets;

	private List<Tache> taches;

	@Override
	public List<Idee> getIdee() {
		idees = new ArrayList<Idee>();
		try {			

			serveur = initServeur();

			//Connexion de l'utilisateur
			serveur.login("admin", "admin", new CallBack<String>() {

				@Override
				public void onSucces(String result) {
					try {
						//Upload de tous les projets
						serveur.telechargeInbox(result, new CallBack<List<IIdee>>() {

							@Override
							public void onSucces(List<IIdee> result) {

								int i = 0;
								for (IIdee idee : result) {
									Idee newIdee = new Idee(idee.getIdentifiantServeur(),i,idee.getNom(), idee.getDescription(),idee.getCreateur().getPseudonyme(), idee.getDateDeDerniereModification());
									idees.add(newIdee);
									i++;
								}
							}

							@Override
							public void onFailure(Exception e) {
								e.printStackTrace();
							}
						});
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void onFailure(Exception e) {
					e.printStackTrace();
				}
			});



		} catch (Exception e) {
			e.printStackTrace();

		}

		return ServerData.getIdee();
	}

	@Override
	public List<Integer> getIdeeStats() {
		List<Idee> ideeStats = getIdee();

		Date today = new java.util.Date(); 

		int years = today.getYear();
		int nb=0;

		HashMap<Integer,Integer> mois = new HashMap<Integer,Integer>();

		for (int i=0; i<12; i++) {
			mois.put(i, 0);
		}

		for (Idee proj : ideeStats) {
			if (((Date)proj.get("date")).getYear()==years) {
				nb = mois.get(((Date)proj.get("date")).getMonth()) + 1;
				mois.put(((Date)proj.get("date")).getMonth(), nb);
			}
		}

		Set<?> cles = mois.keySet();
		Iterator<?> it = cles.iterator();
		List<Integer> stats = new ArrayList<Integer>();
		while (it.hasNext()){
			Integer cle = (Integer) it.next(); 
			Integer valeur = mois.get(cle);
			stats.add(valeur);
		}

		return stats;	
	}

	/**
	 * Retourne la liste des projets
	 */
	@Override
	public List<Projet> getProjet() {

		projets = new ArrayList<Projet>();

		try {			

			serveur = initServeur();

			//Connexion de l'utilisateur
			serveur.login("admin", "admin", new CallBack<String>() {

				@Override
				public void onSucces(String result) {
					try {
						//Upload de tous les projets
						serveur.telechargeProjets(result, new CallBack<List<IProjet>>() {

							@Override
							public void onSucces(List<IProjet> result) {

								int i = 0;
								for (IProjet proj : result) {
									Projet newProjet = new Projet(proj.getIdentifiantServeur(),i,proj.getNom(), proj.getContexteParDefaut().getNom(),proj.getCreateur().getPseudonyme(), proj.getDateDeDerniereModification(), proj.getAvancement().name());
									projets.add(newProjet);
									i++;
								}
							}

							@Override
							public void onFailure(Exception e) {
								e.printStackTrace();
							}
						});
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void onFailure(Exception e) {
					e.printStackTrace();
				}
			});



		} catch (Exception e) {
			e.printStackTrace();

		}

		return ServerData.getProjet();
	}

	@Override
	public List<Integer> getProjetStats() {
		List<Projet> projetStats = getProjet();

		Date today = new java.util.Date(); 

		int years = today.getYear();
		int nb=0;

		HashMap<Integer,Integer> mois = new HashMap<Integer,Integer>();

		for (int i=0; i<12; i++) {
			mois.put(i, 0);
		}

		for (Projet proj : projetStats) {
			if (((Date)proj.get("date")).getYear()==years) {
				nb = mois.get(((Date)proj.get("date")).getMonth()) + 1;
				mois.put(((Date)proj.get("date")).getMonth(), nb);
			}
		}

		Set<?> cles = mois.keySet();
		Iterator<?> it = cles.iterator();
		List<Integer> stats = new ArrayList<Integer>();
		while (it.hasNext()){
			Integer cle = (Integer) it.next(); 
			Integer valeur = mois.get(cle);
			stats.add(valeur);
		}

		return stats;	
	}

	@Override
	public List<Stock> getSeekStocks() {
		return ServerData.getSeekStocks();
	}

	@Override
	public List<Stock> getStocks() {
		stocks = new ArrayList<Stock>();

		List<Idee> newIdees = getIdee();
		List<Tache> newTaches = getTache();
		List<Projet> newProjets = getProjet();

		int i = 0;
		for(Idee idee : newIdees) {
			stocks.add(new Stock(idee.getId(),i,(String)idee.get("name"),"Idee",(String)idee.get("createur"),(Date)idee.get("date")));
			i++;
		}

		for(Tache tache : newTaches) {
			stocks.add(new Stock(tache.getId(),i,(String)tache.get("name"),"Tache",(String)tache.get("createur"),(Date)tache.get("date")));
			i++;
		}

		for(Projet projet : newProjets) {
			stocks.add(new Stock(projet.getId(),i,(String)projet.get("name"),"Projet",(String)projet.get("createur"),(Date)projet.get("date")));
			i++;
		}

		return stocks;
	}

	@Override
	public List<Tache> getTache() {
		taches = new ArrayList<Tache>();

		try {			

			serveur = initServeur();

			//Connexion de l'utilisateur
			serveur.login("admin", "admin", new CallBack<String>() {

				@Override
				public void onSucces(String result) {
					try {
						//Upload de tous les projets
						serveur.telechargeTaches(result, new CallBack<List<ITache>>() {

							@Override
							public void onSucces(List<ITache> result) {

								int i = 0;
								for (ITache tache : result) {
									Tache newTache= new Tache(tache.getIdentifiantServeur(),i,tache.getNom(), tache.getContexte().getNom(),tache.getCreateur().getPseudonyme(), tache.getDateDeDerniereModification(), tache.getPriorite(), tache.getAvancement().name());
									taches.add(newTache);
									i++;
								}
							}

							@Override
							public void onFailure(Exception e) {
								e.printStackTrace();
							}
						});
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void onFailure(Exception e) {
					e.printStackTrace();
				}
			});



		} catch (Exception e) {
			e.printStackTrace();

		}

		return ServerData.getTache();
	}

	@Override
	public List<Integer> getTacheStats() {

		List<Tache> tachesStats = getTache();

		Date today = new java.util.Date(); 

		int years = today.getYear();
		int nb=0;

		HashMap<Integer,Integer> mois = new HashMap<Integer,Integer>();

		for (int i=0; i<12; i++) {
			mois.put(i, 0);
		}

		for (Tache tache : tachesStats) {
			if (((Date)tache.get("date")).getYear()==years) {
				nb = mois.get(((Date)tache.get("date")).getMonth()) + 1;
				mois.put(((Date)tache.get("date")).getMonth(), nb);
			}
		}

		Set<?> cles = mois.keySet();
		Iterator<?> it = cles.iterator();
		List<Integer> stats = new ArrayList<Integer>();
		while (it.hasNext()){
			Integer cle = (Integer) it.next(); 
			Integer valeur = mois.get(cle);
			stats.add(valeur);
		}

		return stats;

	}

	@Override
	public List<PieChartData> getUserStats() {
		List<Idee> newIdees = getIdee();
		List<Tache> newTaches = getTache();
		List<Projet> newProjets = getProjet();


		HashMap<String, Integer> stats = new HashMap<String, Integer>();		
		for(Idee idee : newIdees) {
			if (stats.get(idee.get("createur"))==null) {
				stats.put((String) idee.get("createur"), 1);
			} else {
				stats.put((String) idee.get("createur"), stats.get(idee.get("createur"))+1);
			}
		}

		for(Tache tache : newTaches) {
			if (stats.get(tache.get("createur"))==null) {
				stats.put((String) tache.get("createur"), 1);
			} else {
				stats.put((String) tache.get("createur"), stats.get(tache.get("createur"))+1);
			}
		}

		for(Projet projet : newProjets) {
			if (stats.get(projet.get("createur"))==null) {
				stats.put((String) projet.get("createur"), 1);
			} else {
				stats.put((String) projet.get("createur"), stats.get(projet.get("createur"))+1);
			}
		}

		List<PieChartData> slices = new ArrayList<PieChartData>();
		Set<?> cles = stats.keySet();
		Iterator<?> it = cles.iterator();
		while (it.hasNext()){
			String cle = (String) it.next(); 
			Integer valeur = stats.get(cle);
			slices.add(new PieChartData(valeur, cle,cle));
		}


		return slices;
	}


	/**
	 * Initialisation du serveur rmi
	 * @return le serveur rmi initialisé
	 */
	private ServeurRMI initServeur() {
		Context ctx;

		try {
			ctx = new InitialContext();

			serveur = (ServeurRMI) ctx.lookup("ServeurRMI/remote");

		} catch (NamingException e) {
			e.printStackTrace();
		}
		return serveur;
	}

	@Override
	public List<Stock> getDataStock(String input, String type) {
		ArrayList<Stock> list = new ArrayList<Stock>();
		int i = 0;

		if (type.equals("Tous")) {
			List<Idee> ideesSeek = getIdee();
			List<Tache> tachesSeek = getTache();
			List<Projet> projetsSeek = getProjet();

			for (Idee ide : ideesSeek) {
				if (((String)ide.get("name")).toLowerCase().contains(input.toLowerCase())){
					list.add(new Stock(ide.getId(),i, (String)ide.get("name"), "Idee", (String)ide.get("createur"), (Date)ide.get("date")));
					i++;
				}
			}

			for (Tache tach : tachesSeek) {
				if (((String)tach.get("name")).toLowerCase().contains(input.toLowerCase())){
					list.add(new Stock(tach.getId(),i, (String)tach.get("name"), "Tache", (String)tach.get("createur"), (Date)tach.get("date")));
					i++;
				}
			}

			for (Projet proj : projetsSeek) {
				if (((String)proj.get("name")).toLowerCase().contains(input.toLowerCase())){
					list.add(new Stock(proj.getId(),i, (String)proj.get("name"), "Projet", (String)proj.get("createur"), (Date)proj.get("date")));
					i++;
				}
			}
		} else if (type.equals("Idee")) {
			List<Idee> ideesSeek = getIdee();			
			for (Idee ide : ideesSeek) {
				if (((String)ide.get("name")).toLowerCase().contains(input.toLowerCase())){
					list.add(new Stock(ide.getId(),i, (String)ide.get("name"), "Idee", (String)ide.get("createur"), (Date)ide.get("date")));
					i++;
				}
			}
			
		} else if (type.equals("Tache")) {
			List<Tache> tachesSeek = getTache();
			for (Tache tach : tachesSeek) {
				if (((String)tach.get("name")).toLowerCase().contains(input.toLowerCase())){
					list.add(new Stock(tach.getId(),i, (String)tach.get("name"), "Tache", (String)tach.get("createur"), (Date)tach.get("date")));
					i++;
				}
			}
			
		} else if (type.equals("Projet")) {
			List<Projet> projetsSeek = getProjet();
			for (Projet proj : projetsSeek) {
				if (((String)proj.get("name")).toLowerCase().contains(input.toLowerCase())){
					list.add(new Stock(proj.getId(),i, (String)proj.get("name"), "Projet", (String)proj.get("createur"), (Date)proj.get("date")));
					i++;
				}
			}
		}

		return list;
	}

	@Override
	public List<Idee> getDataIdee(String text, String type) {
		ArrayList<Idee> list = new ArrayList<Idee>();
		int i = 0;
		
		List<Idee> ideesSeek = getIdee();
		if (type.equals("Tous")) {
			
			for (Idee ide : ideesSeek) {
				if (((String)ide.get("name")).toLowerCase().contains(text.toLowerCase())
						|| ((String)ide.get("createur")).toLowerCase().contains(text.toLowerCase())){
					list.add(new Idee(ide.getId(),i, (String)ide.get("name"),(String)ide.get("desc"), (String)ide.get("createur"), (Date)ide.get("date")));
					i++;
				}
			}

		} else if (type.equals("Nom")) {
			for (Idee ide : ideesSeek) {
				if (((String)ide.get("name")).toLowerCase().contains(text.toLowerCase())){
					list.add(new Idee(ide.getId(),i, (String)ide.get("name"),(String)ide.get("desc"), (String)ide.get("createur"), (Date)ide.get("date")));
					i++;
				}
			}
			
		} else if (type.equals("Créateur")) {
			for (Idee ide : ideesSeek) {
				if (((String)ide.get("createur")).toLowerCase().contains(text.toLowerCase())){
					list.add(new Idee(ide.getId(),i, (String)ide.get("name"),(String)ide.get("desc"), (String)ide.get("createur"), (Date)ide.get("date")));
					i++;
				}
			}
			
		}

		return list;
	}

	@Override
	public List<Tache> getDataTache(String text, String type) {
		ArrayList<Tache> list = new ArrayList<Tache>();
		int i = 0;
		
		List<Tache> tachesSeek = getTache();
		if (type.equals("Tous")) {
			
			for (Tache tach : tachesSeek) {
				if (((String)tach.get("name")).toLowerCase().contains(text.toLowerCase())
						|| ((String)tach.get("createur")).toLowerCase().contains(text.toLowerCase())
						|| ((String)tach.get("contexte")).toLowerCase().contains(text.toLowerCase())
						|| ((String)tach.get("priorite")).toLowerCase().contains(text.toLowerCase())
						|| ((String)tach.get("avancement")).toLowerCase().contains(text.toLowerCase())){
					list.add(new Tache(tach.getId(),i, (String)tach.get("name"),(String)tach.get("contexte"), (String)tach.get("createur"), (Date)tach.get("date"), (Integer)tach.get("priorite"),(String)tach.get("avancement")));
					i++;
				}
			}

		} else if (type.equals("Nom")) {
			for (Tache tach : tachesSeek) {
				if (((String)tach.get("name")).toLowerCase().contains(text.toLowerCase())){
					list.add(new Tache(tach.getId(),i, (String)tach.get("name"),(String)tach.get("contexte"), (String)tach.get("createur"), (Date)tach.get("date"), (Integer)tach.get("priorite"),(String)tach.get("avancement")));
					i++;
				}
			}
			
		} else if (type.equals("Créateur")) {
			for (Tache tach : tachesSeek) {
				if (((String)tach.get("createur")).toLowerCase().contains(text.toLowerCase())){
					list.add(new Tache(tach.getId(),i, (String)tach.get("name"),(String)tach.get("contexte"), (String)tach.get("createur"), (Date)tach.get("date"), (Integer)tach.get("priorite"),(String)tach.get("avancement")));
					i++;
				}
			}
			
		} else if (type.equals("Contexte")) {
			for (Tache tach : tachesSeek) {
				if (((String)tach.get("contexte")).toLowerCase().contains(text.toLowerCase())){
					list.add(new Tache(tach.getId(),i, (String)tach.get("name"),(String)tach.get("contexte"), (String)tach.get("createur"), (Date)tach.get("date"), (Integer)tach.get("priorite"),(String)tach.get("avancement")));
					i++;
				}
			}
			
		} else if (type.equals("Priorite")) {
			for (Tache tach : tachesSeek) {
				if ((String.valueOf(tach.get("priorite"))).toLowerCase().contains(text.toLowerCase())){
					list.add(new Tache(tach.getId(),i, (String)tach.get("name"),(String)tach.get("contexte"), (String)tach.get("createur"), (Date)tach.get("date"), (Integer)tach.get("priorite"),(String)tach.get("avancement")));
					i++;
				}
			}
			
		} else if (type.equals("Avancement")) {
			for (Tache tach : tachesSeek) {
				if (((String)tach.get("avancement")).toLowerCase().contains(text.toLowerCase())){
					list.add(new Tache(tach.getId(),i, (String)tach.get("name"),(String)tach.get("contexte"), (String)tach.get("createur"), (Date)tach.get("date"), (Integer)tach.get("priorite"),(String)tach.get("avancement")));
					i++;
				}
			}
			
		} 

		return list;
	}

	@Override
	public List<Projet> getDataProjet(String text, String type) {
		ArrayList<Projet> list = new ArrayList<Projet>();
		int i = 0;
		
		List<Projet> projetSeek = getProjet();
		if (type.equals("Tous")) {
			
			for (Projet proj : projetSeek) {
				if (((String)proj.get("name")).toLowerCase().contains(text.toLowerCase())
						|| ((String)proj.get("createur")).toLowerCase().contains(text.toLowerCase())
						|| ((String)proj.get("contexte")).toLowerCase().contains(text.toLowerCase())
						|| ((String)proj.get("avancement")).toLowerCase().contains(text.toLowerCase())){
					list.add(new Projet(proj.getId(),i, (String)proj.get("name"),(String)proj.get("contexte"), (String)proj.get("createur"), (Date)proj.get("date"), (String)proj.get("avancement")));
					i++;
				}
			}

		} else if (type.equals("Nom")) {
			for (Projet tach : projetSeek) {
				if (((String)tach.get("name")).toLowerCase().contains(text.toLowerCase())){
					list.add(new Projet(tach.getId(),i, (String)tach.get("name"),(String)tach.get("contexte"), (String)tach.get("createur"), (Date)tach.get("date"),(String)tach.get("avancement")));
					i++;
				}
			}
			
		} else if (type.equals("Créateur")) {
			for (Projet tach : projetSeek) {
				if (((String)tach.get("createur")).toLowerCase().contains(text.toLowerCase())){
					list.add(new Projet(tach.getId(),i, (String)tach.get("name"),(String)tach.get("contexte"), (String)tach.get("createur"), (Date)tach.get("date"), (String)tach.get("avancement")));
					i++;
				}
			}
			
		} else if (type.equals("Contexte")) {
			for (Projet tach : projetSeek) {
				if (((String)tach.get("contexte")).toLowerCase().contains(text.toLowerCase())){
					list.add(new Projet(tach.getId(),i, (String)tach.get("name"),(String)tach.get("contexte"), (String)tach.get("createur"), (Date)tach.get("date"), (String)tach.get("avancement")));
					i++;
				}
			}
			
		} else if (type.equals("Avancement")) {
			for (Projet tach : projetSeek) {
				if (((String)tach.get("avancement")).toLowerCase().contains(text.toLowerCase())){
					list.add(new Projet(tach.getId(),i, (String)tach.get("name"),(String)tach.get("contexte"), (String)tach.get("createur"), (Date)tach.get("date"), (String)tach.get("avancement")));
					i++;
				}
			}
			
		} 

		return list;
	}

}
