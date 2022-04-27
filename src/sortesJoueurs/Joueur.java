package sortesJoueurs;

import details.Couleur;
import elementsPartie.IJoueur;
import elementsPartie.IPlateauJeu;

public abstract class Joueur implements IJoueur {
	// La couleur du joueur
	private Couleur c;
	
	/**
	 * Un constructeur de Joueur
	 * @param cl la couleur de ce joueur
	 */
	public Joueur(Couleur cl) {
		this.c = cl;
	}
	
	/**
	 * Recupere la couleur du joueur
	 * @return la couleur du joueur
	 */
	public Couleur getColor() {
		return this.c;
	}
	
	/**
	 * Permet de jouer un tour sur l'echiquier
	 * @param e l'echiquier
	 */
	public abstract void jouerUnTour(IPlateauJeu e);
	
	/**
	 * Determine si la piece est un Humain
	 * @return true si le joueur peut saisir au clavier
	 */
	public boolean peutSaisir() {
		return false;
	}

}
