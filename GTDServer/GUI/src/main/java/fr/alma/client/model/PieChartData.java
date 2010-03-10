package fr.alma.client.model;

import java.io.Serializable;

public class PieChartData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6184647837635031620L;

	private int valeur;
	
	private String label;
	
	private String text;
	
	public PieChartData() {}

	public PieChartData(int i, String label, String text) {
		this.valeur=i;
		this.label=label;
		this.text=text;
	}

	/**
	 * @return the valeur
	 */
	public int getValeur() {
		return valeur;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	
}
