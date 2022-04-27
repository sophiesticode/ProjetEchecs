package appli;

import details.Couleur;
import sortesJoueurs.*;

public class FabriqueJoueur {
	
	/**
	 * Permet de creer un joueur
	 * @param typePartie le type de partie souhaite
	 * @param couleur la couleur du joueur a  creer
	 * @return le joueur cree
	 */
	public static Joueur creerJoueur(char typePartie, Couleur couleur)  {
		assert(couleur == Couleur.BLANC || couleur == Couleur.NOIR);
		switch(typePartie) {
		case '1' : return new Humain(couleur);
		case '2' : return (couleur == Couleur.BLANC) ? new Humain(couleur) : new Algo(couleur);
		case '3' : return new Algo(couleur);
		default : return null;
		}
	}

}
