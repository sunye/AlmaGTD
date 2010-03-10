package fr.alma.modele.noyau;

import java.util.List;

/**
 * Interface IContact
 * @author Université de Nantes
 * @version 1.0
 * @since 2009
 */
public interface IContact {
	

	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************
	
	public List<Tache> getTaches();
	
	public String getNom();
	
	public String getAdresse();
	
	public String getEmail();
	
	public String getTel();
	
	public Long getId();
	
	public void setId(Long id);
	
	public void setTaches(List<Tache> taches);
	
	public void setNom(String nom);
	
	public void setAdresse(String adresse);
	
	public void setEmail(String email);
	
	public void setTel(String tel);
}