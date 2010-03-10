package fr.univnantes.alma.gtd.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.univnantes.alma.gtd.controler.connexion.ControlerConnexion;
import fr.univnantes.alma.gtd.controler.connexion.IControlerConnexion;
import fr.univnantes.alma.gtd.controler.utilisateurs.ControlerUtilisateurs;
import fr.univnantes.alma.gtd.controler.utilisateurs.IControlerUtilisateurs;

public class Connexion extends JFrame {

	IControlerConnexion gestConn;
	IControlerUtilisateurs gestUtil;
	JPanel log = new JPanel();
	JPanel pass = new JPanel();
	JPanel pbutton =new JPanel();
	JTextField login = new JTextField();
	JPasswordField mdp = new JPasswordField();
	JLabel loginLab = new JLabel("Login :");
	JLabel mdpLab = new JLabel("Mot de passe :");
	JButton ok = new JButton("Connexion");
	JButton nouveaucompte = new JButton("Nouveau compte");

	public Connexion(String title) throws HeadlessException {
		super(title);
		gestConn = new ControlerConnexion();
		gestUtil = new ControlerUtilisateurs();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean test = gestConn.connect(login.getText(), mdp.getText());
				if (test){
					new GTD(login.getText());
					JFrame f = Connexion.this;
					f.dispose();
				}
				else {
					JOptionPane.showOptionDialog(Connexion.this, "Utilisateur inconnu","Connexion", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE, null, null, null);
				}
			}
		});
		nouveaucompte.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if ((login.getText().equalsIgnoreCase(""))
						||(mdp.getPassword().toString().equalsIgnoreCase(""))){							
					JOptionPane.showOptionDialog(Connexion.this,
							"Pour ajouter un compte, entrer un login et un mot de passe",
							"Ajout d'un compte utilisateur", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, null, null);
				}else{
					gestUtil.nouveauUtilisateur(login.getText(), mdp.getText());
					String msg = "L'utilisateur "+ login.getText().toString()+
									/*" avec le mot de passe " + mdp.getPassword()+ " ."*/
					"a bien ete ajoute et peux maintenant se connecter";
					JOptionPane.showOptionDialog(Connexion.this,
							msg,
							"Ajout d'un compte utilisateur", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, null, null);
				}
				
			}
		});
		
		JPanel cont = (JPanel) this.getContentPane();
	
		JLabel logo = new JLabel(new ImageIcon("ressources/david.jpg"));
		
		cont.setLayout(new BorderLayout());
		cont.add(logo,BorderLayout.NORTH);
		
		JPanel cent = new JPanel();
		cent.setLayout(new BoxLayout(cent,BoxLayout.Y_AXIS));
		pbutton.setLayout(new BoxLayout(pbutton, BoxLayout.X_AXIS));
		log.setLayout(new GridLayout());
		pass.setLayout(new GridLayout());
		log.add(loginLab);
		pass.add(mdpLab);
		log.add(login);
		pass.add(mdp);
		cent.add(log);
		cent.add(pass);
		cont.add(cent,BorderLayout.CENTER);
		pbutton.add(ok);
		pbutton.add(nouveaucompte);
		cont.add(pbutton,BorderLayout.SOUTH);
		
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(this.getParent());
		this.setVisible(true);
		
	}
	
	

}
