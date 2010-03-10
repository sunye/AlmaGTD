package fr.univnantes.alma.gtd.view;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import fr.univnantes.alma.gtd.model.gestionnaireressources.Date;

public class DatePanel extends JPanel{
	
	private JComboBox jour = new JComboBox();
	private JComboBox mois = new JComboBox();
	private JComboBox annee = new JComboBox();
	public DatePanel(){
		for(Integer i = 1; i < 32 ; i++){
			jour.addItem(i);
		}
		for(Integer i = 1; i < 13 ; i++){
			mois.addItem(i);
		}
		for(Integer i = 2009; i < 2100 ; i++){
			annee.addItem(i);
		}
		this.setLayout(new FlowLayout());
		this.add(jour);
		this.add(mois);
		this.add(annee);
	}
	
	public Date getDate(){
		return new Date((Integer)annee.getSelectedItem(),(Integer)mois.getSelectedItem(),
				(Integer)jour.getSelectedItem());
		
		
	}
	public void setDate(Date calendar){
		
		annee.setSelectedItem(calendar.getAnnee());
		mois.setSelectedItem(calendar.getMois());
		jour.setSelectedItem(calendar.getJour());	
		
	}
	

}
