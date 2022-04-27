package tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import appli.FabriqueJoueur;
import details.Couleur;
import sortesJoueurs.Joueur;

public class FabriqueJoueurTest {
	@Test
	void creerJoueurTest() {
        
        Joueur j1 = FabriqueJoueur.creerJoueur('1', Couleur.BLANC);
        Joueur j2 = FabriqueJoueur.creerJoueur('2', Couleur.NOIR);
        Joueur j3 = FabriqueJoueur.creerJoueur('2', Couleur.BLANC);
        Joueur j4 = FabriqueJoueur.creerJoueur('3', Couleur.BLANC);
        
        assertTrue(j1.peutSaisir() && j1.getColor() == Couleur.BLANC);
        assertTrue(!j2.peutSaisir() && j2.getColor() == Couleur.NOIR);
        assertTrue(j3.peutSaisir() && j3.getColor() == Couleur.BLANC);
        assertTrue(!j4.peutSaisir() && j4.getColor() == Couleur.BLANC);
        
    }

}
