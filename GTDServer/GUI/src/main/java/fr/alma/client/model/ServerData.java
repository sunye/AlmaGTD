package fr.alma.client.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.store.ListStore;


public class ServerData {
	
	private static List<Stock> dataStock;
	
	private static List<Idee> dataIdee;

	private static List<Tache> dataTache;
	
	private static List<Projet> dataProjet;

	@SuppressWarnings("deprecation")
	public static List<Stock> getStocks() {
		List<Stock> stocks = new ArrayList<Stock>();

		stocks.add(new Stock("45",0,"GTD", "Projet", "Chriss", new Date("01-SEP-2009")));
		stocks.add(new Stock("47",1,"Jouer de la musique", "Idee", "Stef", new Date("05-SEP-2009")));
		stocks.add(new Stock("99",2,"Ecrire un concerto", "Tache", "Alex", new Date("11-NOV-2009")));
		return stocks;

	}
	
	@SuppressWarnings("deprecation")
	public static List<Idee> getIdee() {
		List<Idee> stocks = new ArrayList<Idee>();
		stocks.add(new Idee("0",0,"Jouer de la musique", "Prendre ma guitare, la brancher et jouer avec.", "Stef", new Date("05-SEP-2010")));
		stocks.add(new Idee("0",0,"Jouer de la musique", "Prendre ma guitare, la brancher et jouer avec.", "Stef", new Date("05-OCT-2010")));
		stocks.add(new Idee("0",0,"Jouer de la musique", "Prendre ma guitare, la brancher et jouer avec.", "Stef", new Date("05-SEP-2010")));
		stocks.add(new Idee("0",0,"Jouer de la musique", "Prendre ma guitare, la brancher et jouer avec.", "Stef", new Date("05-DEC-2010")));
		stocks.add(new Idee("0",0,"Jouer de la musique", "Prendre ma guitare, la brancher et jouer avec.", "Stef", new Date("05-JAN-2010")));
		stocks.add(new Idee("0",0,"Jouer de la musique", "Prendre ma guitare, la brancher et jouer avec.", "Stef", new Date("05-MAR-2010")));
		stocks.add(new Idee("0",0,"Jouer de la musique", "Prendre ma guitare, la brancher et jouer avec.", "Stef", new Date("05-MAY-2010")));
		stocks.add(new Idee("0",0,"Jouer de la musique", "Prendre ma guitare, la brancher et jouer avec.", "Stef", new Date("05-JUN-2010")));
		stocks.add(new Idee("0",0,"Jouer de la musique", "Prendre ma guitare, la brancher et jouer avec.", "Stef", new Date("05-JUL-2010")));
		stocks.add(new Idee("0",0,"Jouer de la musique", "Prendre ma guitare, la brancher et jouer avec.", "Stef", new Date("05-AUG-2010")));
		stocks.add(new Idee("0",0,"Jouer de la musique", "Prendre ma guitare, la brancher et jouer avec.", "Benjamin", new Date("05-MAY-2010")));
		stocks.add(new Idee("0",0,"Jouer de la musique", "Prendre ma guitare, la brancher et jouer avec.", "Stef", new Date("05-MAY-2010")));
		stocks.add(new Idee("0",0,"Jouer de la musique", "Prendre ma guitare, la brancher et jouer avec.", "Stef", new Date("05-SEP-2010")));
		stocks.add(new Idee("0",0,"Jouer de la musique", "Prendre ma guitare, la brancher et jouer avec.", "Stef", new Date("05-JAN-2010")));
		stocks.add(new Idee("0",0,"Jouer de la musique", "Prendre ma guitare, la brancher et jouer avec.", "Stef", new Date("05-SEP-2010")));
		return stocks;

	}
	
	@SuppressWarnings("deprecation")
	public static List<Tache> getTache() {
		List<Tache> stocks = new ArrayList<Tache>();
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-NOV-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-OCT-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-AUG-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Benjamin", new Date("11-JUL-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-JUN-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-MAY-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Benjamin", new Date("11-FEB-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-JAN-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-DEC-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Benjamin", new Date("11-NOV-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-OCT-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-SEP-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-JUL-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-JUN-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-FEB-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-JAN-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-DEC-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-NOV-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-NOV-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Benjamin", new Date("11-NOV-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-NOV-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-NOV-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-NOV-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-NOV-2010"),4,"A faire"));
		stocks.add(new Tache("0",0,"Ecrire un concerto", "Home", "Alex", new Date("11-NOV-2010"),4,"A faire"));

		return stocks;

	}
	
	@SuppressWarnings("deprecation")
	public static List<Projet> getProjet() {
		List<Projet> stocks = new ArrayList<Projet>();
		stocks.add(new Projet("0",0,"GTD", "Work", "Benjamin", new Date("01-SEP-2010"),"Termine"));
		stocks.add(new Projet("0",0,"GTD", "Work", "Chriss", new Date("01-OCT-2010"),"Termine"));
		stocks.add(new Projet("0",0,"GTD", "Work", "Chriss", new Date("01-NOV-2010"),"Termine"));
		stocks.add(new Projet("0",0,"GTD", "Work", "Chriss", new Date("01-DEC-2010"),"Termine"));
		stocks.add(new Projet("0",0,"GTD", "Work", "Chriss", new Date("01-JAN-2010"),"Termine"));
		stocks.add(new Projet("0",0,"GTD", "Work", "Benjamin", new Date("01-FEB-2010"),"Termine"));
		stocks.add(new Projet("0",0,"GTD", "Work", "Chriss", new Date("01-MAR-2010"),"Termine"));
		stocks.add(new Projet("0",0,"GTD", "Work", "Chriss", new Date("01-APR-2010"),"Termine"));
		stocks.add(new Projet("0",0,"GTD", "Work", "Benjamin", new Date("01-MAY-2010"),"Termine"));
		stocks.add(new Projet("0",0,"GTD", "Work", "Chriss", new Date("01-JUN-2010"),"Termine"));
		stocks.add(new Projet("0",0,"GTD", "Work", "Chriss", new Date("01-JUL-2010"),"Termine"));
		stocks.add(new Projet("0",0,"GTD", "Work", "Chriss", new Date("01-AUG-2010"),"Termine"));
		stocks.add(new Projet("0",0,"GTD", "Work", "Benjamin", new Date("01-OCT-2010"),"Termine"));
		stocks.add(new Projet("0",0,"GTD", "Work", "Chriss", new Date("01-OCT-2010"),"Termine"));
		stocks.add(new Projet("0",0,"GTD", "Work", "Chriss", new Date("01-NOV-2010"),"Termine"));
		stocks.add(new Projet("0",0,"GTD", "Work", "Benjamin", new Date("01-DEC-2010"),"Termine"));
		stocks.add(new Projet("0",0,"GTD", "Work", "Chriss", new Date("01-DEC-2010"),"Termine"));
		stocks.add(new Projet("0",0,"GTD", "Work", "Chriss", new Date("01-DEC-2010"),"Termine"));
		stocks.add(new Projet("0",0,"GTD", "Work", "Chriss", new Date("01-DEC-2010"),"Termine"));
		stocks.add(new Projet("0",0,"GTD", "Work", "Chriss", new Date("01-JAN-2010"),"Termine"));
		stocks.add(new Projet("0",0,"GTD", "Work", "Chriss", new Date("01-MAY-2010"),"Termine"));

		return stocks;

	}

	public static ListStore<TypeChamp> getTypeChamp() {
		ListStore<TypeChamp> stocks = new ListStore<TypeChamp>();

		stocks.add(new TypeChamp("Tous"));
		stocks.add(new TypeChamp("Idee"));
		stocks.add(new TypeChamp("Tache"));
		stocks.add(new TypeChamp("Projet"));
		return stocks;
	}
	
	public static List<TypeChamp> getTacheChamp() {
		List<TypeChamp> stocks = new ArrayList<TypeChamp>();

		stocks.add(new TypeChamp("Tous"));
		stocks.add(new TypeChamp("Nom"));
		stocks.add(new TypeChamp("Créateur"));
		stocks.add(new TypeChamp("Contexte"));
		stocks.add(new TypeChamp("Priorite"));
		stocks.add(new TypeChamp("Avancement"));
		return stocks;
	}


	public static List<Stock> getSeekStocks() {
		return dataStock;
	}

	public static ListStore<TypeChamp> getIdeeChamp() {
		ListStore<TypeChamp> stocks = new ListStore<TypeChamp>();

		stocks.add(new TypeChamp("Tous"));
		stocks.add(new TypeChamp("Nom"));
		stocks.add(new TypeChamp("Créateur"));
		return stocks;
	}

	public static ListStore<TypeChamp> getProjetChamp() {
		ListStore<TypeChamp> stocks = new ListStore<TypeChamp>();

		stocks.add(new TypeChamp("Tous"));
		stocks.add(new TypeChamp("Nom"));
		stocks.add(new TypeChamp("Créateur"));
		stocks.add(new TypeChamp("Contexte"));
		stocks.add(new TypeChamp("Avancement"));
		return stocks;
	}

	public static List<PieChartData> getUserStats() {
		List<PieChartData> slices = new ArrayList<PieChartData>();
		slices.add(new PieChartData(200, "Stef","Stef"));
		slices.add(new PieChartData(100, "Alex", "Alex"));
		slices.add(new PieChartData(150, "Chriss", "Chriss"));
		slices.add(new PieChartData(5, "Benjamin", "Benjamin"));
		return slices;
	}

	public static List<Integer> getIdeeStats() {
		List<Integer> list = new ArrayList<Integer>();
		
		for (int t = 0; t < 12; t++) {  
			list.add((t+1)*2);  
		}
		
		return list;
	}

	public static List<Integer> getTacheStats() {
		List<Integer> list = new ArrayList<Integer>();
		
		for (int t = 0; t < 12; t++) {  
			list.add((t+1)*4);  
		}
		
		return list;
	}

	public static List<Integer> getProjetStats() {
		List<Integer> list = new ArrayList<Integer>();
		
		for (int t = 0; t < 12; t++) {  
			list.add((t+1)*3+9);  
		}
		
		return list;
	}
	
	/**
	 * @param dataStock the dataStock to set
	 */
	public static void setDataStock(List<Stock> dataStock) {
		ServerData.dataStock = dataStock;
	}

	public static void setDataIdee(List<Idee> result) {
		ServerData.dataIdee = result;		
	}
	
	/**
	 * @return the dataTache
	 */
	public static List<Tache> getDataTache() {
		return dataTache;
	}

	/**
	 * @param dataTache the dataTache to set
	 */
	public static void setDataTache(List<Tache> dataTache) {
		ServerData.dataTache = dataTache;
	}

	/**
	 * @return the dataProjet
	 */
	public static List<Projet> getDataProjet() {
		return dataProjet;
	}

	/**
	 * @param dataProjet the dataProjet to set
	 */
	public static void setDataProjet(List<Projet> dataProjet) {
		ServerData.dataProjet = dataProjet;
	}

	/**
	 * @return the dataStock
	 */
	public static List<Stock> getDataStock() {
		return dataStock;
	}

	/**
	 * @return the dataIdee
	 */
	public static List<Idee> getDataIdee() {
		return dataIdee;
	}

	
	
}