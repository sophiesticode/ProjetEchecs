package sortesPieces;

import details.Coord;
import details.Couleur;
import details.Direction;
import elementsPartie.IPiece;

public class Roi extends Piece{
	
	/**
	 * Un constructeur de roi
	 * @param c la coordonnee du roi
	 * @param cl la couleur du roi
	 */
	public Roi (Coord c, Couleur cl) {
		super(c, cl);
	}
	
	/**
	 * @Return le symbole d'un roi
	 */
	public char getSymbole() {
		return 'r';
	}
	
	/**
	 * @Return true si le roi a le droit 
	 * d'emprunter cette direction dans une partie
	 */
	public boolean directionPossible(Direction d) {
		switch(d) {
		case NORD :
		case NORD_EST :
		case EST :
		case SUD_EST :	
		case SUD :
		case SUD_OUEST :	
		case OUEST :
		case NORD_OUEST : return true;
		default : return false;
		}
	}

	/**
	 * @Return le nombre de pas maximum posssibles du roi
	 */
	@Override
	public int getNbPasMax() {
		return 1;
	}

	/**
	 * protocole de la classe Piece
	 * @return true si la piece est un roi
	 */
	public boolean craintEchec() {
		return true;
	}
	
	/**
	 * protocole de la classe Piece
	 * @return true si la piece peut mater a  elle-seule
	 */
	public boolean peutMaterALuiSeul() {
		return false;
	}
	
	/**
	 * Copie le roi actuel
	 * @return le roi actuel
	 */
	@Override
	public IPiece copie() {
		return new Roi(getCoord().copie(), getColor());
	}
}
