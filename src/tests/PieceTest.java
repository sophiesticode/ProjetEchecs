package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import details.Coord;
import details.Couleur;
import details.Direction;
import echiquier.Echiquier;
import elementsPartie.IPiece;
import sortesPieces.Piece;
import sortesPieces.Roi;
import sortesPieces.Tour;

class PieceTest {

	@Test
	void estAdverseTest() {
		Echiquier e = new Echiquier();
		IPiece roiN = new Roi(new Coord('f', '8'), Couleur.NOIR);
		IPiece roiB = new Roi(new Coord('d', '8'), Couleur.BLANC);
		e.ajouterPiece(roiN);
		e.ajouterPiece(roiB);
		assertTrue(roiN.estAdverse(roiB));
	}
	
	@Test
	void faireUnPasTest() {
		Piece roiN = new Roi(new Coord('f', '6'), Couleur.NOIR);
		roiN.faireUnPas(Direction.NORD);
		assertTrue(roiN.getCoord().estEgaleA(new Coord('f', '7')));
	}
	
	@Test
	void peutSeDeplacerTest() {
		Echiquier e = new Echiquier();
		IPiece roiN = new Roi(new Coord('f', '8'), Couleur.NOIR);
		e.ajouterPiece(roiN);
		assertTrue(roiN.peutSeDeplacer(e));
	}
	
	@Test
	void peutAllerEnTest() {
		Echiquier e = new Echiquier();
		IPiece roiN = new Roi(new Coord('f', '8'), Couleur.NOIR);
		e.ajouterPiece(roiN);
		assertTrue(roiN.peutAllerEn(new Coord('f', '7'), e));
	}
	
	@Test
	void pieceSensibleTest() {
		IPiece roiN = new Roi(new Coord('f', '8'), Couleur.NOIR);
		IPiece tourN = new Tour(new Coord('f', '8'), Couleur.NOIR);
		assertTrue(roiN.craintEchec());
		assertFalse(tourN.craintEchec());
	}
}
