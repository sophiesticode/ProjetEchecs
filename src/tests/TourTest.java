package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import details.Coord;
import details.Couleur;
import details.Direction;
import elementsPartie.IPiece;
import sortesPieces.Tour;

class TourTest {
	@Test
	void directionPossibleNordTest() {
		IPiece tourN = new Tour(new Coord('f', '8'), Couleur.NOIR);
		assertTrue(tourN.directionPossible(Direction.NORD));
	}
	
	@Test
	void directionPossibleNord_OuestTest() {
		IPiece tourN = new Tour(new Coord('f', '8'), Couleur.NOIR);
		assertFalse(tourN.directionPossible(Direction.NORD_OUEST));
	}	
}
