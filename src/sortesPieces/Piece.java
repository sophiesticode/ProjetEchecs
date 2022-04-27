package sortesPieces;


import details.Coord;
import details.Couleur;
import details.Direction;
import echiquier.Echiquier;
import elementsPartie.IPiece;
import elementsPartie.IPlateauJeu;

public abstract class Piece implements IPiece {
	
	// la couleur d'une piece
	private Couleur color;
	
	// les coordonnees d'une piece
	private Coord c;
	
	// le constructeur d'une piece
	public Piece(Coord cd, Couleur col) {
		this.c = cd;
		this.color = col;
	}
	
	/**
	 * @return les coordonnees de la piece
	 */
	@Override
	public Coord getCoord() {
		return c;
	}
	
	/**
	 * @return la coordonnee Y de la piece
	 */
	@Override
	public int getY() {
		return c.getY();
	}
	
	/**
	 * @return la coordonnee X de la piece
	 */
	@Override
	public int getX() {
		return c.getX();
	}
	
	/**
	 * @return la couleur de la piece
	 */
	@Override
	public Couleur getColor() {
		return this.color;
	}
	
	/**
	 * Permet de mofifier la coordonnee de la piece
	 * @param cd la nouvelle coordonnee
	 */
	@Override
	public void setCoord(Coord cd) {
		this.c.setCoord(cd.getX(), cd.getY());
	}
	
	/**
	 * Permet de faire un pas dans une direction
	 * @param d la direction empruntee
	 */
	@Override
	public void faireUnPas(Direction d) {
		this.setCoord(new Coord (this.getX() + d.getDx(), 
				this.getY() + d.getDy()));
	}
	
	/**
	 * Determine si une piece peut se deplacer sur l'echiquier
	 * @param e l'echiquier
	 * @return true si vrai
	 */
	@Override
	public boolean peutSeDeplacer(IPlateauJeu e) {
		int size = 8;
		for (int line= 0; line < size; line++) {
			for(int col = 0; col < size; col++) {
				if(peutAllerEn(new Coord(col, line), e)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Determine si une piece peut aller a  une coordonnee de l'echiquier
	 * sans qu'il y ait echec au roi
	 * @param cArr la coordonnee d'arrivee
	 * @param e l'echiquier
	 * @return true si vrai
	 */
	@Override
	public boolean peutAllerEn(Coord cArr, IPlateauJeu e) {
		if(!(e.dansLimitesPlateau(cArr))) return false;
		if(this.getCoord().estEgaleA(cArr)) return false;
		Direction chemin = this.getCoord().trouverDirectionVers(cArr);
		if(chemin == null) return false;
		if(!directionPossible(chemin)) return false;
		if(!e.cheminLibreVers(cArr, chemin, this)) return false;
		if(e.caseVide(cArr.getY(), cArr.getX())) {
			if(craintEchec() && ((Echiquier) e).aCoteDePieceSensible(cArr, this))
				return false;
		} else {
			if(mangeQueEnBiais() && !peutAttaquerEn(chemin))
				return false;
			if(e.getPiece(cArr).getColor() == this.getColor())
				return false;
		}
		Echiquier cpe = e.copie();
		cpe.deplacerPieces(getCoord(), cArr);
		return(!(cpe.echecAuRoi(cpe.getCoordRoi(color))));
	}
	
	
	/**
	 * @Return true si la piece a le droit d'attaquer un roi
	 */
	@Override
	public boolean peutMaterALuiSeul() {
		return true;
	}
	
	/**
	 * @Return le caractere formate representant la piece
	 */
	@Override
	public char dessiner() {
		if (this.getColor() == Couleur.NOIR)
			return Character.toLowerCase(this.getSymbole());
		else
			return Character.toUpperCase(this.getSymbole());
	}
	
	/**
	 * @return le symbole d'une piece
	 */
	public abstract char getSymbole();
	
	/**
	 * @param p la piece a  observer
	 * @return true si une piece est adverse a  une autre
	 */
	@Override
	public boolean estAdverse(IPiece p) {
		return this.getColor() != p.getColor();
	}
	
	/**
	 * Determine si la piece ne matte pas lors d'une finale
	 * @return true si elle ne matte pas
	 */
	public boolean pieceInsuffisantePourContinuer() {
		return false;
	}
	
	/**
	 * Determine si la piece est un roi
	 * @return true si la piece est un roi
	 */
	@Override
	public boolean craintEchec() {
		return false;
	}
	
	/**
	 * Determine si la piece est un pion
	 * @return true si la piece est un pion
	 */
	@Override
	public boolean mangeQueEnBiais() {
		return false;
	}
	
	/**
	 * Determine si une piece peut attaquer dans une direction
	 * @param d la direction a  emprunter
	 * @return true si la piece a le droit d'attaquer dans cette direction
	 */
	private boolean peutAttaquerEn(Direction d) {
		return true;
	}
	
	/**
	 * Determine si une piece peut aller dans une direction
	 * @param d la direction a  emprunter
	 * @return true si la piece a le droit d'emprunter cette direction
	 */
	public abstract boolean directionPossible(Direction d);
}

