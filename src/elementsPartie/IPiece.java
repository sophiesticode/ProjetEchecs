package elementsPartie;

import details.Coord;
import details.Couleur;
import details.Direction;

public interface IPiece {
	public boolean mangeQueEnBiais();
	public boolean peutMaterALuiSeul();
	public boolean estAdverse(IPiece p);
	public boolean craintEchec();
	public Coord getCoord();
	public int getY();
	public int getX();
	public void setCoord(Coord cd);
	public Couleur getColor();
	public int getNbPasMax();
	public boolean peutAllerEn(Coord arrivee, IPlateauJeu e);
	public char dessiner();
	public void faireUnPas(Direction d);
	public boolean peutSeDeplacer(IPlateauJeu e);
	public boolean directionPossible(Direction d);
	public IPiece copie();
}
