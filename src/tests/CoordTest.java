package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import details.Coord;
import details.Direction;

class CoordTest {

	@Test
	void trouverDirectionVersTest() {
		Coord c1 = new Coord(7, 1);
		Coord c2 = new Coord(1, 1);
		assertEquals(c1.trouverDirectionVers(c2), Direction.OUEST);
	}
	
	@Test
	void prendreUneDirection() {
		Coord c1 = new Coord('f', '7');
		Coord c2 = new Coord('g', '7');
		c1.prendreDirection(Direction.EST);
		assertEquals(c1.getX(), c2.getX());
		assertEquals(c1.getY(), c2.getY());
	}
	@Test
	void estEgaleATest() {
		Coord c1 = new Coord ('f', '7');
		Coord c2 = c1.copie();
		assertTrue(c1.estEgaleA(c2));
	}

}
