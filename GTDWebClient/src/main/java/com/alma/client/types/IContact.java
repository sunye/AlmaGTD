package com.alma.client.types;

public interface IContact extends IObjetServeur {

	/**
	 * @return Nom associe a ce contact.
	 */
	String getNom();
	
	/**
	 * @param nom Nom associe a ce contact.
	 */
	void setNom(final String nom);
	
	/**
	 * 
	 * @return E-Mail associe a ce contact.
	 */
	String getEmail();
	
	/**
	 * @param nom Nom associe a ce contact.
	 */
	void setEmail(final String email);
	
	/**
	 * @return Adresse associee a ce contact.
	 */
	String getAdresse();
	
	/**
	 * @param nom Nom associe a ce contact.
	 */
	void setAdresse(final String adresse);
	
	/**
	 * @return Telephone associe a ce contact.
	 */
	String getTelephone();
	
	/**
	 * @param nom Nom associe a ce contact.
	 */
	void setTelephone(final String numeroTelephone);
}
