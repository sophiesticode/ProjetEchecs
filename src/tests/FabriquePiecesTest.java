package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import appli.FabriquePieces;
import details.Coord;
import echiquier.Echiquier;

class FabriquePiecesTest {

	@Test
	void testInitialiserFinaleEchecs() {
		Echiquier e=new Echiquier();
		FabriquePieces.initialiserFinaleEchecs(e);
		assertNotNull(e.getPiece(new Coord('e', '6')));
		assertNotNull(e.getPiece(new Coord('e', '8')));
		assertNotNull(e.getPiece(new Coord('b', '7')));	
	}
}
