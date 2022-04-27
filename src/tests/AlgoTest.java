package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import details.Coord;
import details.Couleur;
import echiquier.Echiquier;
import sortesJoueurs.Algo;
import sortesJoueurs.Joueur;
import sortesPieces.Piece;
import sortesPieces.Roi;

class AlgoTest {

	@Test
	void DeplacementAlgoTest() {
		Joueur jB = new Algo(Couleur.BLANC);
		Echiquier e = new Echiquier();
		Piece roiN = new Roi(new Coord('f', '8'), Couleur.NOIR);
		Piece roiB = new Roi(new Coord('e', '6'), Couleur.BLANC);
		e.ajouterPiece(roiN);
		e.ajouterPiece(roiB);
		jB.jouerUnTour(e);
		assertTrue(!roiN.getCoord().estEgaleA(new Coord('e', '6')));
	}

}
