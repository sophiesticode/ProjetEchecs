package sortesJoueurs;

import java.util.ArrayList;
import java.util.Random;
import details.Coord;
import details.Couleur;
import elementsPartie.IPlateauJeu;

public class Algo extends Joueur {
	// les listes necessaires au deroulement des methodes
	private ArrayList<Coord> piecesJouables, departs, arriveesPossibles;
	
	// Le nombre max possible de pieces d'un joueur
	private static final int MAX = 16;
	
	/**
	 * Un constructeur d'Algorithme (Intelligence artificielle)
	 * @param c la couleur de ce joueur
	 */
	public Algo (Couleur c) {
		super(c);
		departs = new ArrayList<>();
		arriveesPossibles = new ArrayList<>();
		piecesJouables = new ArrayList<>(MAX);
	}
	
	/**
	 * Permet de jouer un tour automatiquement
	 * @param e l'Echiquier sur lequel jouer
	 */
	public void jouerUnTour(IPlateauJeu e) {
		e.rechercherPieces(super.getColor(), piecesJouables);
		for (int nb = 0; nb < piecesJouables.size(); nb++) {
			e.rechercherDirectionsPossibles(departs, arriveesPossibles, 
					e.getPiece(piecesJouables.get(nb)));
		}
		Random tirage = new Random();
		int alea = tirage.nextInt(arriveesPossibles.size());
		e.deplacerPieces(departs.get(alea), arriveesPossibles.get(alea));
		
		//end
		resetPiecesJouables();
		resetCoupsPossibles();
	}
	
	/**
	 * Supprime la liste des pieces jouables
	 */
	private void resetPiecesJouables() {
		piecesJouables.clear();
	}
	
	/**
	 * Supprime la liste des coups possibles
	 */
	private void resetCoupsPossibles() {
		departs.clear();
		arriveesPossibles.clear();
	}

}
