package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import details.Coord;
import details.Couleur;
import echiquier.Echiquier;
import elementsPartie.IPiece;
import elementsPartie.IPlateauJeu;
import sortesPieces.Roi;
import sortesPieces.Tour;

class EchiquierTest {

	@Test
	void ajouterPieceTest() {
		IPlateauJeu e = new Echiquier();
		IPiece roiN = new Roi(new Coord('f', '8'), Couleur.NOIR);
		e.ajouterPiece(roiN);
		assertEquals(e.getPiece(new Coord('f', '8')).dessiner(), roiN.dessiner());
	}
	
	@Test
	void caseVideTest() {
		IPlateauJeu e = new Echiquier();
		assertTrue(e.caseVide(0, 3));
	}
	
	@Test
	void dansLimitesEchiquierTest() {
		IPlateauJeu e = new Echiquier();
		Coord cEnDehors = new Coord('f', '9');
		Coord cEnDedans = new Coord('f', '8');
		assertTrue(e.dansLimitesPlateau(cEnDedans));
		assertFalse(e.dansLimitesPlateau(cEnDehors));
	}
	
	@Test
	void deplacerPiecesTest() {
		IPlateauJeu e = new Echiquier();
		Coord cDep = new Coord('f', '8');
		Coord cArr = new Coord('f', '7');
		IPiece pRoi = new Roi(cDep, Couleur.NOIR);
		e.ajouterPiece(pRoi);
		e.deplacerPieces(cDep, cArr);
		assertTrue(pRoi.getCoord().estEgaleA(cArr));
	}
	
	@Test
	void echecAuRoiTest() {
		Echiquier e = new Echiquier();
		IPiece roiN = new Roi(new Coord('e', '8'), Couleur.NOIR);
		IPiece tourB = new Tour(new Coord('b', '7'), Couleur.BLANC);
		IPiece roiB = new Roi(new Coord('e', '6'), Couleur.BLANC);
		e.ajouterPiece(roiN);
		e.ajouterPiece(tourB);
		e.ajouterPiece(roiB);
		e.deplacerPieces(new Coord('b', '7'), new Coord('b', '8'));
		assertTrue(e.echecAuRoi(roiN.getCoord()));
	}
	
	@Test
	void echecEtMatTest() {
		IPlateauJeu e = new Echiquier();
		IPiece roiN = new Roi(new Coord('f', '8'), Couleur.NOIR);
		IPiece tourB = new Tour(new Coord('d', '7'), Couleur.BLANC);
		IPiece roiB = new Roi(new Coord('e', '6'), Couleur.BLANC);
		e.ajouterPiece(roiN);
		e.ajouterPiece(tourB);
		e.ajouterPiece(roiB);
		assertFalse(e.finDePartieAttaqueInevitableDetectee(Couleur.BLANC));
	}

	@Test
	void rechercherPiecesTest() {
		IPlateauJeu e = new Echiquier();
		IPiece tourB = new Tour(new Coord('d', '7'), Couleur.BLANC);
		IPiece roiB = new Roi(new Coord('e', '6'), Couleur.BLANC);
		e.ajouterPiece(tourB);
		e.ajouterPiece(roiB);
		ArrayList<Coord> piecesPlateau = new ArrayList<>();
		e.rechercherPieces(Couleur.BLANC, piecesPlateau);
		assertTrue(piecesPlateau.size() == 2);
		assertTrue(piecesPlateau.get(0).estEgaleA(new Coord('d', '7')));
	}
	
	/* Recherche des directions possibles du roi noir 
	lorsqu'il est non loin d'un autre roi
	Il ne reste que 4 directions dans ce cas.
	*/
	@Test
	void rechercherDirectionsPossiblesTest() {
		IPlateauJeu e = new Echiquier();
		IPiece roiN = new Roi(new Coord('f', '5'), Couleur.NOIR);
		IPiece roiB = new Roi(new Coord('d', '5'), Couleur.BLANC);
		e.ajouterPiece(roiN);
		e.ajouterPiece(roiB);
		ArrayList<Coord> dep = new ArrayList<>();
		ArrayList<Coord> arr = new ArrayList<>();
		e.rechercherDirectionsPossibles(dep, arr, roiN);
		assertEquals(dep.size(),5);
	}
	
	@Test
    void matchNulTest() {
        IPlateauJeu e = new Echiquier();
        IPiece roiB = new Roi(new Coord('a', '1'), Couleur.BLANC);
        IPiece roiN = new Roi(new Coord('a', '8'), Couleur.NOIR);
        e.ajouterPiece(roiN);
        e.ajouterPiece(roiB);
        assertTrue(e.matchNul( Couleur.BLANC));
    }
}
