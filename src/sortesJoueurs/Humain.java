package sortesJoueurs;

import java.util.Scanner;
import details.Coord;
import details.Couleur;
import elementsPartie.IPlateauJeu;

public class Humain extends Joueur {
	
	// Le nombre max de lettres dans une saisie
	private static final int maxMOT = 4;
	
	/**
	 * Un constructeur d'Humain
	 * @param c la couleur de l'humain
	 */
	public Humain(Couleur c) {
		super(c);
	}
	
	/**
	 * Permet de jouer un tour de jeu
	 * @param e l'echiquier sur lequel jouer
	 */
	public void jouerUnTour(IPlateauJeu e) {
		Scanner sc = new Scanner(System.in);
		String mot;
		boolean valide = false;
		do {
			mot = sc.nextLine();
			valide = saisieValide(mot, e);
			if(!valide)
				System.out.print("> ");
		} while(!valide);
		
		Coord depart = new Coord (mot.charAt(0), mot.charAt(1));
		Coord arrivee = new Coord (mot.charAt(2), mot.charAt(3));
		e.deplacerPieces(depart, arrivee);
	}
	
	/**
	 * Determine si une saisie de l'utilisateur est valide
	 * @param mot la saisie de l'utilisateur
	 * @param e l'echiquier sur lequel appliquer la saisie
	 * @return true si valide
	 */
	private boolean saisieValide(String mot, IPlateauJeu e) {
		
		if (mot.length()!= maxMOT)
			return false;
		
		Coord depart = new Coord (mot.charAt(0), mot.charAt(1));
		Coord arrivee = new Coord (mot.charAt(2), mot.charAt(3));
		
		if (!(e.dansLimitesPlateau(depart) && e.dansLimitesPlateau(arrivee))) 
			return false;
		if (e.caseVide(depart.getY(), depart.getX()))
			return false;
		if (e.getPiece(depart).getColor() != super.getColor())
			return false;
		if(depart.getX() == arrivee.getX() && depart.getY() == arrivee.getY())
			return false;
		return (e.getPiece(depart).peutAllerEn(arrivee, e));
	}
	
	/**
	 * Determine si la piece est un Humain
	 * @return true si le joueur peut saisir au clavier
	 */
	public boolean peutSaisir() {
		return true;
	}
}
