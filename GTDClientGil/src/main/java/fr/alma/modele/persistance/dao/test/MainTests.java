package fr.alma.modele.persistance.dao.test;

public class MainTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/////////////////////TEST UTILISATEURDAO///////////////////
		
		TestUtilisateurDAO testU = new TestUtilisateurDAO();
		try {
			testU.setUp();
		} catch (Exception e) {
			System.out.println("setup impossible sur testUtilisateurDAO :");
			System.out.println(e.getMessage());
		}
		System.out.println("création d'un utilisateur");
		testU.creerUtilisateur();
		System.out.println("récupération utilisateurs");
		testU.tousUtilisateur();
		System.out.println("L'utlisateur existe ?");
		testU.existeUtilisateur();
		
		////////////////TEST PROJETDAO//////////////////////////////
		TestProjetDao testP = new TestProjetDao();
		try {
			testP.setUp();
		} catch (Exception e) {
			System.out.println("setup impossible sur testProjetDAO :");
			System.out.println(e.getMessage());
		}
		System.out.println("création d'un projet");
		testP.creerProjet();
	}

}
