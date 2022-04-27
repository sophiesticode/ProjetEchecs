package appli;

import java.util.Scanner;

import details.Couleur;
import echiquier.Echiquier;
import elementsPartie.IJoueur;
import elementsPartie.IPlateauJeu;

public class AppliEchecs {
	
	// les couleurs des joueurs
	private static Couleur coulj1, coulj2;
    
	/**
	 * Construit la chaine de caracteres du menu
	 * @return la chaine de caracteres correspondant a�l'affichage du menu
	 */
	private static String affichageMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("------- MENU PRINCIPAL -------");
		sb.append(System.getProperty("line.separator"));
		sb.append("1 : H vs H :");
		sb.append(System.getProperty("line.separator"));
		sb.append("2 : H vs IA :");
		sb.append(System.getProperty("line.separator"));
		sb.append("3 : IA vs IA :");
		return sb.toString();
	}
	
	/**
	 * Determine si le choix de partie est valide
	 * @param c le choix du joueur
	 * @return true si valide
	 */
	private static boolean choixPartieValide(char c) {
		switch(c) {
		case '1':
		case '2':
		case '3': return true;
		default : return false;
		}
	}
	
	/**
	 * Determine si le choix d'abandon de partie est valide
	 * @param c le choix du joueur
	 * @return true si valide
	 */
	private static boolean choixAbandonValide(char c) {
		c = Character.toLowerCase(c);
		return (c == 'o')||(c == 'n');
	}
	
	/**
	 * Construit la chaine de caractères 
	 * indiquant le gagnant de la partie
	 * @return la chaine de caractère correspondant
	 * 		 à l'affichage du fin de partie avec gagnant
	 */
	private static String gagnant(Couleur cJGagnant) {
		StringBuilder sb = new StringBuilder();
		sb.append(" ---------------------------------");
		sb.append(System.getProperty("line.separator"));
		sb.append("| Les " + cJGagnant + "S ont gagne la partie ! |");
		sb.append(System.getProperty("line.separator"));
		sb.append(" ---------------------------------");
		return sb.toString();
	}
	
	/**
	 * Construit la chaine de caractères 
	 * indiquant une égalité dans la partie
	 * @return la chaine de caractère correspondant
	 * 		 à l'affichage du fin de partie avec égalité
	 */
	private static String egalite() {
		return "La partie s'est soldee par une egalite";
	}
	
	// le deroulement du jeu
	public static void main(String[] args) {
		int nbTours = 0;
		// menu principal
		System.out.println(affichageMenu());
		
		// choix du type de partie
		Scanner sc = new Scanner(System.in);
		char choixPartie;
		do {
			choixPartie = sc.next().charAt(0);
		} while(!choixPartieValide(choixPartie));
		
		// couleur des pieces
		coulj1 = Couleur.BLANC;
		coulj2 = Couleur.NOIR;
		
		// preparation de la partie choisie
		IPlateauJeu damier = new Echiquier();
		
		IJoueur j1 = FabriqueJoueur.creerJoueur(choixPartie, coulj1);
		IJoueur j2 = FabriqueJoueur.creerJoueur(choixPartie, coulj2);
		
		FabriquePieces.initialiserFinaleEchecs(damier);
		//FabriquePieces.initPartieOrginaleSansPions(damier);

		IJoueur jEnCours = j1;
		IJoueur jAdverse = j2;
		
		// deroulement de chaque tour
		do {
			nbTours++;
			System.out.println("---------------------------------------------------");
			System.out.println("C'est aux " + jEnCours.getColor() + "S de jouer \n");
			System.out.println(damier);
			
			// on recherche si la partie est terminee
			if(damier.finDePartieAttaqueInevitableDetectee(jEnCours.getColor())) {
				System.out.println(gagnant(jAdverse.getColor()));
				break;
			}
			else {
				if(damier.matchNul(jEnCours.getColor())) {
				System.out.println(egalite());
				break;
				}
			}
			// proposition de nulle
			char reponse = 'n';
			if(jEnCours.peutSaisir()) {
				System.out.println("Voulez-vous conclure sur un match nul ?");
				System.out.println("o:OUI ou n:NON ");
				do {
					reponse = sc.next().charAt(0);
				} while(!choixAbandonValide(reponse));
			}
			if(reponse == 'o') break;

			// deplacement de pieces
			if(jEnCours.peutSaisir()) {
				System.out.println("Jouer (format : a6a8) : ");
			}
			jEnCours.jouerUnTour(damier);
			
			// Si le roi adverse est attaque, 
			// la partie est terminee
			if(damier.finDePartieAttaque()) {
				System.out.println(damier);
				System.out.println(gagnant(jEnCours.getColor()));
				break;
			}
			
			// Changement de joueur
			jAdverse = (jEnCours == j1) ? j1 : j2;
			jEnCours = (jEnCours == j1) ? j2 : j1;
			
		} while(true);
		
		System.out.println("Partie finie en " + nbTours + " tours");
	}
}
