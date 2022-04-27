package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import details.Coord;
import details.Couleur;
import details.Direction;
import elementsPartie.IPiece;
import sortesPieces.Roi;

class RoiTest {

	@Test
	void directionPossibleNordTest() {
		IPiece roiN = new Roi(new Coord('f', '8'), Couleur.NOIR);
		assertTrue(roiN.directionPossible(Direction.NORD));
	}
	
	@Test
	void directionPossibleNord_OuestTest() {
		IPiece roiN = new Roi(new Coord('f', '8'), Couleur.NOIR);
		assertTrue(roiN.directionPossible(Direction.NORD_OUEST));
	}
}
