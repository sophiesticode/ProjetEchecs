package appli;

import details.Coord;
import details.Couleur;
import elementsPartie.IPlateauJeu;
import sortesPieces.*;

public class FabriquePieces {
	
	/**
	 * Permet de creer les pieces qui vont composer un echiquier
	 * @param e le plateau a initialiser
	 */
	public static void initialiserFinaleEchecs(IPlateauJeu e) {
		assert(!(e == null));
		Coord roiN = new Coord('e', '8');
		Coord roiB = new Coord('e', '6');
		Coord tourB = new Coord ('b', '7');
		Piece RN = new Roi(roiN, Couleur.NOIR);
		Piece RB = new Roi(roiB, Couleur.BLANC);
		Piece TB = new Tour(tourB, Couleur.BLANC);
		e.ajouterPiece(RN);
		e.ajouterPiece(RB);
		e.ajouterPiece(TB);
	}
}
