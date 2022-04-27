package elementsPartie;

import java.util.ArrayList;
import details.Coord;
import details.Couleur;
import details.Direction;
import echiquier.Echiquier;

public interface IPlateauJeu {
	public boolean matchNul(Couleur enCours);
	public boolean finDePartieAttaqueInevitableDetectee(Couleur enCours);
	public boolean finDePartieAttaque();
	public void ajouterPiece(IPiece piece);
	public boolean caseVide(int l, int c);
	public IPiece getPiece(Coord pos);
	public IPiece getAttaquante(IPiece p);
	public void deplacerPieces(Coord depart, Coord arrivee);
	public boolean dansLimitesPlateau(Coord pos);
	public void rechercherPieces(Couleur couleur, ArrayList<Coord> pieces);
	public void rechercherDirectionsPossibles(ArrayList<Coord> departs, 
			ArrayList<Coord> arr, IPiece p);
	public boolean cheminLibreVers(Coord cArr, Direction chemin, IPiece piece);
	public Echiquier copie();
}
