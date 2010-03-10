package fr.alma.modele.noyau;

import java.util.Date;
import java.util.List;

/**
 * Interface ITache
 * @author Université de Nantes
 * @version 1.0
 * @since 2009
 */
public interface ITache {

	public static final int PRIORITE = 0;
	public static final int TAUX_EFFORT = 1;
	public static final int DATE_DEBUT = 2;
	public static final int NOTES = 3;
	public static final int TAGS = 4;
	public static final int URLS = 5;
	public static final int FREQUENCE_REP = 6;
	public static final int DATE_ARRET_FREQUENCE_REP = 7;
	public static final int DATE_ECHEANCE = 8;
	public static final int CONTEXTE = 9;
	public static final int CONTACTS = 10;
	public static final int NOM = 11;

	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************

	public Long getId();

	public String getNom();

	public Integer getPriorite();

	public Integer getTauxEffort();

	public Date getDateDebut();

	public String getNotes();

	public Date getDateEcheance();

	public List<String> getUrls();

	public List<String> getTags();

	public Periodicite getPeriodicite();

	public String getContexte();

	public Long getIdProjet();

	public void setId(Long id);

	public void setNom(String nom);

	public void setPriorite(Integer priorite);

	public void setTauxEffort(Integer tauxEffort);

	public void setDateDebut(Date dateDebut);

	public void setNotes(String notes);

	public void setDateEcheance(Date dateEcheance);

	public void setUrls(List<String> urls);

	public void setTags(List<String> tags);

	public void setPeriodicite(Periodicite periodicite);

	public void setContexte(String contexte);

	public void setIdProjet(Long idProjet);
}