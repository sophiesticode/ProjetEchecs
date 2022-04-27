package sortesPieces;

import details.Coord;
import details.Couleur;
import details.Direction;
import elementsPartie.IPiece;

public class Tour extends Piece {
	
	/*Le nombre maximal de pas d'une tour*/
	private static final int MAX = 8;
	
	/**
	 * Un constructeur de tour
	 * @param c la coordonnee de la tour
	 * @param cl la couleur de la tour
	 */
	public Tour(Coord c, Couleur cl) {
		super(c, cl);
	}
	
	/**
	 * @Return le symbole d'une tour
	 */
	public char getSymbole() {
		return 't';
	}
	
	/**
	 * @Return true si la tour a le droit 
	 * d'emprunter cette direction dans une partie
	 */
	public boolean directionPossible(Direction d) {
		switch(d) {
		case NORD :
		case EST :
		case SUD :
		case OUEST : return true;
		default : return false;
		}
	}
	
	/**
	 * @Return le nombre de pas maximum posssibles de la tour
	 */
	@Override
	public int getNbPasMax() {
		return MAX;
	}
	/**
	 * Recupere la tour courante
	 * @return la tour actuelle
	 */
	
	@Override
	public IPiece copie() {
		return new Tour(getCoord().copie(), getColor());
	}
}
