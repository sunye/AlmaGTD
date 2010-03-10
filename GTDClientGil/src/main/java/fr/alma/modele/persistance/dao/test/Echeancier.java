package fr.alma.modele.persistence.dao.test;

import java.util.ArrayList;
import java.util.List;

import fr.alma.modele.noyau.Tache;

public class Echeancier {
	private ArrayList<Tache> taches;
	
	public Echeancier(){
		super();
		this.taches = new ArrayList<Tache>();
	}
	
    
	public void recupererEcheance(List<Tache> taches){
		for(Tache t : taches){
			if(t.getDateEcheance() != null){
				this.taches.add(t);
			}
		}
	}
	
	/*
	 * la tache t1 a pour date : 15 janv. 2010 00:00:00
la tache t2 a pour date : 7 févr. 2010 00:00:00
la tache t3 a pour date : 10 mars 2010 00:00:00
la tache t4 a pour date : 6 nov. 2009 00:00:00
la tache ok a pour date : 23 oct. 2009 00:00:00
la tache ok2 a pour date : 7 mars 2009 00:00:00
	 */
	public void trierTaches(){
		Tache[] ltrie = new Tache[taches.size()];
		int k = 0;
		for(Tache t : taches){
			ltrie[k] = t;
			k++;
		}
		int MAX = k;
	    int i = 0;
	    int p = 0;
	    int j =0;
	    Tache x;
	    for (i = 1; i < MAX; i++) 
	    {
	        x = ltrie[i]; 
	        p = i-1;
	        while (ltrie[p].getDateEcheance().after(x.getDateEcheance()) && p-- > 0) {}
	        p++;    
	        for (j = i-1; j >= p; j--) {
	            ltrie[j+1] = ltrie[j]; 
	        }   
	        ltrie[p] = x;
	    }
	    ArrayList<Tache> retour = new ArrayList<Tache>();
	    for(int y=0;y<MAX;y++){
	    	retour.add(ltrie[y]);
	    }
	    this.setTaches(retour);
	}
	
	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(ArrayList<Tache> taches) {
		this.taches = taches;
	}
	
	
}
