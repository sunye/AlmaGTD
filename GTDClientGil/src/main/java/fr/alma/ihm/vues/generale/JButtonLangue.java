package fr.alma.ihm.vues.generale;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 * Classe JButtonLangue, représente les boutons de langues dans la fenêtre des préférences
 * @author Université de Nantes
 * @since 2009
 * @version 1.0
 */
public class JButtonLangue extends JButton {
	
	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = 3706747502665282038L;
	
	/**
	 * image associé au bouton
	 */
	public String lien = new String();
	
	/**
	 * Constructeur avec paramètre
	 * @param lien le chemin où se situe l'image
	 */
	public JButtonLangue(String lien){
		this.lien = lien;
		this.setPreferredSize(new Dimension(25,15));
	}

	/** 
	 * Dessine le fond
	 * @param g : Graphics 
	 */
	@Override
	public void paintComponent(Graphics g){
        try {
                Image img = ImageIO.read(new File(lien));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight() , this);
        } catch (IOException e) {
                e.printStackTrace();
        }    
	} 
}
