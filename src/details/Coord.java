package details;

import echiquier.Echiquier;

public class Coord {
	// le code ASCII de 'a'
	private static final int col = 97;
	
	// le code ASCII de '1'
	private static final int line = 49;
	
	// L'abscisse x
	private int x;
	
	// L'ordonnee y
	private int y;
	
	/**
	 *  Un constructeur de coordonnee du point de vue de l'utilisateur
	 * @param let la lettre d'abscisse
	 * @param chif le chiffre d'ordonnée
	 */
	public Coord(char let, char chif) {
		this.x = setX(let);
		this.y = setY(chif);
	}
	
	/**
	 * Un constructeur de coordonnee du point de vue de l'echiquier
	 * @param colonne le numero de colonne
	 * @param ligne le numero de ligne
	 */
	public Coord (int colonne, int ligne) {
		this.x = colonne;
		this.y = ligne;
	}
	
	/**
	 * Permet de modifier une coordonnee
	 * @param abs la nouvelle abscisse
	 * @param ord la nouvelle ordonnee
	 */
	public void setCoord(int abs, int ord) {
		x = abs;
		y = ord;
	}
	
	/**
	 * Permet de copier une coordonnee dans une nouvelle
	 * @return la nouvelle coordonnee
	 */
	public Coord copie() {
		return new Coord(this.x, this.y);
	}
	
	/**
	 * Permet de convertir une lettre en numero de 
	 * colonne utilisable par l'echiquier
	 * @param v la lettre
	 * @return le numero de colonne
	 */
	private int setX(char v) {
		return v% col;
	}
	
	/**
	 * Permet de convertir un numero de ligne 
	 * du point de vue de l'utilisateur en numero de 
	 * ligne utilisable par l'echiquier
	 * @param val le numero de ligne
	 * @return le numero de ligne correct pour l'echiquier
	 */
	private int setY(char val) {
		return (Echiquier.SIZE - 1) - (val % line); 
	}
	
	/**
	 * @return la valeur de l'abscisse x
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * @return la valeur de l'ordonnee y
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Permet d'emprunter une direction
	 * @param d la direction a� emprunter
	 */
	public void prendreDirection(Direction d) {
		this.x = x + d.getDx();
		this.y = y + d.getDy();
	}
	
	/**
	 * Permet de trouver une direction du point de vue des pions BLANCS
	 * @param cArr la coordonnee d'arrivee du chemin
	 * @return la direction empruntee
	 */
	public Direction trouverDirectionVers(Coord cArr) {
		Coord dir = getCoordChemin(cArr);
		// si chemin en L
		if(!(dir.getX() == 0 || dir.getY() == 0))
			if(Math.abs(dir.getX()) != Math.abs(dir.getY()))
				return directionEnLDe(dir);
		// si chemin droit
		if(dir.getX() == 0 && dir.getY() < 0) {
			return Direction.NORD;
		}
		if(dir.getX() > 0 && dir.getY() < 0) {
			return Direction.NORD_EST;
		}
		if(dir.getX() > 0 && dir.getY() == 0) {
			return Direction.EST;
		}
		if(dir.getX() > 0 && dir.getY() > 0) {
			return Direction.SUD_EST;
		}
		if(dir.getX() == 0 && dir.getY() > 0) {
			return Direction.SUD;
		}
		if(dir.getX() < 0 && dir.getY() > 0) {
			return Direction.SUD_OUEST;
		}
		if(dir.getX() < 0 && dir.getY() == 0) {
			return Direction.OUEST;
		}
		else return Direction.NORD_OUEST;

	}
	
	/**
	 * Determine la direction en L correspondante
	 * @param dir la coordonnee representant l
	 * e vecteur du chemin a� suivre
	 * @return la direction en L 
	 * ou null si erreur
	 */
	private Direction directionEnLDe(Coord dir) {
		// si chemin en L
		for(int i = 8; i < Direction.NB_DIR-1; i++) {
			Direction test = Direction.values()[i];
			if(test.getDx() == dir.getX()
					&& test.getDy() == dir.getY()){
				return Direction.values()[i];
			}
		}
		return null;
	}
	
	/**
	 * Evalue le nombre de pas necessaire au parcours d'une trajectoire
	 * @param cArr la coordonnee d'arrivee
	 * @param d la direction à emprunter
	 * @return le nombre de pas evalue
	 */
	public int getNbPasVers(Coord cArr, Direction d) {
		Coord cDep = this.copie();
		int nbPas = 0;
		while(!cDep.estEgaleA(cArr)) {
			cDep.prendreDirection(d);
			nbPas++;
		}
		return nbPas;
	}
	
	/**
	 * Determine si une coordonnée est egale a� une autre passee en parametre
	 * @param c la coordonnée à comparer
	 * @return true si elles sont égales
	 */
	public boolean estEgaleA(Coord c) {
		return (this.getX() == c.getX()) 
				&& (this.getY() == c.getY());
	}
	
	/**
	 * Evalue le vecteur correspondant a un chemin
	 * @param cArr la coordonnée d'arrivee
	 * @return la coordonnée du chemin a� emrpunter
	 */
	public Coord getCoordChemin(Coord cArr) {
		return new Coord(cArr.getX() - this.getX(), cArr.getY() - this.getY());
	}
}
