package echiquier;

import java.util.ArrayList;

import details.*;
import elementsPartie.IPiece;
import elementsPartie.IPlateauJeu;
import sortesPieces.Piece;

public class Echiquier implements IPlateauJeu {
	
	// la taille du plateau
	public static final int SIZE = 8;
	
	// le damier du jeu
	private IPiece[][] damier;
	
	// l'indicateur de roi attaqué
	private boolean roiAttaque;
	
	// le contructeur d'échiquier
	public Echiquier() {
		this.damier = new Piece[SIZE][SIZE];
	}
	
	/**
	 * Dessine le damier du jeu
	 * @return le damier
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(drawLetters());
		sb.append(System.getProperty("line.separator"));
		for (int line = 0; line < SIZE; line++) {
			sb.append(drawLines());
			sb.append(SIZE - line + " ");
			sb.append(drawCols(line));
			sb.append(SIZE - line);
			sb.append(System.getProperty("line.separator"));
		}
		sb.append(drawLines());
		sb.append(drawLetters());
		return sb.toString();
	}	
	
	/**
	 * Dessine une ligne du damier
	 * @return une ligne en tirets
	 */
	private String drawLines() {
		StringBuilder sb = new StringBuilder();
		for (int cpt = 0; cpt < SIZE; cpt++) {
			if (cpt == 0) {
				sb.append("   ---");
				continue;
			}
			if (cpt == SIZE - 1) {
				sb.append(" ---");
				sb.append(System.getProperty("line.separator"));
				break;
			}
			sb.append(" ---");
		}
		return sb.toString();
	}
	
	/**
	 * Dessine une colonne du damier
	 * @param line l'indice de la ligne courante
	 * @return une colonne en tirets
	 */
	private String drawCols(int line) {
		StringBuilder sb = new StringBuilder();
		for (int cpt = 0; cpt < SIZE; cpt++) {
			//s'il n'y a aucune pièce à cette coordonnée
			if (damier[line][cpt] == null) {
				if (cpt == SIZE - 1) {
					sb.append("|   | ");
					break;
				}
				sb.append("|   ");
				continue;
			}
			if (cpt == SIZE - 1) {
				sb.append("| ");
				sb.append(damier[line][cpt].dessiner());
				sb.append(" | ");
				break;
			}
			sb.append("| ");
			sb.append(damier[line][cpt].dessiner());
			sb.append(" ");
		}
		return sb.toString();
	}
	
	/**
	 * Dessine la ligne de lettres du damier
	 * @return la ligne de lettres
	 */
	private String drawLetters() {
		 StringBuilder sb = new StringBuilder();
	        sb.append("  ");
	        for (char carac = 'a'; carac <= 'h'; carac++) {
	            sb.append("  " + carac + " ");
	        }
	        return sb.toString();
	}
	
	// -------------------------------------------------------------------------
	
	/**
	 * Permet d'ajouter une piece sur l'echiquier
	 * @param piece la piece a placer
	 */
	@Override
	public void ajouterPiece(IPiece piece) {
		damier[piece.getY()][piece.getX()] = piece;
	}

	/**
	 * Determine si la case de position precisee ne contient aucune piece
	 * @param l la ligne sur l'echiquier
	 * @param c la colonne sur l'echiquier
	 * @return true si la case est nulle
	 */
	@Override
	public boolean caseVide(int l, int c) {
		return this.damier[l][c] == null;
	}
	
	/**
	 * Pour obtenir une piece sur l'echiquier
	 * @param pos la position de cette piece
	 * @return la piece sur l'echiquier coorespondant a cette position
	 * @return null si aucune piece ne s'y trouve
	 */
	@Override
	public IPiece getPiece(Coord pos) {
		return this.damier[pos.getY()][pos.getX()];
	}
	
	/**
	 * Deplace deux pieces sur l'echiquier 
	 * et avertit s'il y a lieu d'une attaque
	 * @param depart la coordonnee de depart
	 * @param arrivee la coordonnee d'arrivee
	 */
	@Override
	public void deplacerPieces(Coord depart, Coord arrivee) {
		if (!this.caseVide(arrivee.getY(), arrivee.getX())) {
			if(getPiece(arrivee).craintEchec()) {
				roiAttaque = true;
			}
		}
		IPiece transition = getPiece(depart);
		this.damier[depart.getY()][depart.getX()] = null;
		this.damier[arrivee.getY()][arrivee.getX()] = transition;
		this.damier[arrivee.getY()][arrivee.getX()].setCoord(arrivee);
	}
	
	/**
	 * Indique si le roi est attaque
	 * @return true si vrai
	 */
	public boolean finDePartieAttaque() {
		return roiAttaque;
	}
	
	/**
	 * Determine si la coordonnee est dans les limites de l'echiquier
	 * @param pos la coordonnee a analyser
	 * @return true si vrai
	 */
	public boolean dansLimitesPlateau(Coord pos) {
		return pos.getX() >= 0 && pos.getX() < SIZE 
				&& pos.getY() >= 0 && pos.getY()< SIZE;
	}
	
	/**
	 * Determine si le materiel est insuffisant 
	 * pour terminer la partie ou s'il y a pat
	 * @param enCours la couleur du joueur en cours
	 * @return true si vrai
	 */
	public boolean matchNul(Couleur enCours) {
		return materielInsuffisant() || pat(enCours);
	}
	
	/**
	 * Determine s'il y a pat
	 * @param enCours la couleur du joueur en cours
	 * @return true si aucune piece du camp du roi ne peut se deplacer 
	 * sans mettre son roi en échec
	 */
	private boolean pat(Couleur enCours) {
		Coord cRoi = getCoordRoi(enCours);
		IPiece pRoi = getPiece(cRoi);
		if ((!(echecAuRoi(cRoi)) && (!(aCoteDePieceSensible(cRoi, pRoi))))) {
			for(int l = 0; l < SIZE; l++) {
				for(int c = 0; c < SIZE; c++) {
					if(this.caseVide(l, c)) continue;
					IPiece p = getPiece(new Coord(c, l));
					if (p.getColor() != enCours) continue;
					if(p.peutSeDeplacer(this)) return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Recherche les pieces restantes sur le plateau et
	 * determine si le materiel est insuffisant pour continuer la partie
	 * @return true s'il ne reste que 2 rois, 2 rois et 2 fous, 
	 * ou 2 rois et une autre piece.
	 */
	private boolean materielInsuffisant() {
		ArrayList<IPiece> tab = new ArrayList<>();
		for (int l = 0; l < SIZE; l++) {
			for (int c = 0; c < SIZE; c++) {
				if(this.caseVide(l, c)) continue;
				IPiece pTest = getPiece(new Coord (c, l));
				if(pTest.peutMaterALuiSeul()) return false;
				tab.add(pTest);
			}
		}
		return formeUneCombinaisonDeNulle(tab);
	}
	
	/**
	 * Determine si la partie forme une combinaison de nulle
	 * @param tab le tableau des pieces a analyser
	 * @return true s'il ne reste que 2 rois, 
	 * 2 rois et 2 autres etant insuffisantes pour continuer, 
	 * ou 2 rois et une autre piece.
	 */
	private boolean formeUneCombinaisonDeNulle(ArrayList<IPiece> tab) {
		int deux = 2, trois = 3, quatre = 4;
		if (tab.size() > quatre)
			return false;
		if (tab.size() == deux && tab.get(0).craintEchec() && tab.get(1).craintEchec())
			return true;
		if (tab.size() == trois) {
			for (int a = 0; a < trois; a++) {
				if (((Piece) tab.get(a)).pieceInsuffisantePourContinuer())
					return true;
			}
		}
		if(tab.size() == quatre) {
			boolean cpt = false;
			for (int b = 0; b < quatre; b++) {
				if (((Piece) tab.get(b)).pieceInsuffisantePourContinuer()) {
					if (cpt == true) return true;
					else cpt = true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Determine si la partie est terminee avec un echec et mat
	 * @param enCours la couleur du joueur en cours
	 * @return true si vrai
	 */
	public boolean finDePartieAttaqueInevitableDetectee(Couleur enCours) {
		return echecEtMat(enCours);
	}
	
	/**
	 * Determine s'il y a echec et mat
	 * @param enCours la couleur du joueur en cours
	 * @return true si le roi est attaquable et non couvrable par son camp
	 * @pre il y a echec au roi
	 */
	private boolean echecEtMat(Couleur enCours) {
		Coord cRoi = getCoordRoi(enCours);
		IPiece pRoi = getPiece(cRoi);
		
		if(echecAuRoi(cRoi)) {
			IPiece pAttaquante = getAttaquante(pRoi);
			System.out.println("CHESS par " + pAttaquante.dessiner());
			if (!(pRoi.peutSeDeplacer(this))) {
				if ((getAttaquante(pAttaquante) == null)
					&& !(echecCouvrable(pAttaquante, enCours))) {
						return true;
						// et l'�chec couvrable ???
				}
			}
		}
		return false;
	}
	
	/**
	 * Determine s'il y a echec au roi
	 * @param cRoi les coordonnees du roi
	 * @return true si le roi peut etre attaque par le camp adverse
	 */
	public boolean echecAuRoi(Coord cRoi) {
		IPiece pRoi = this.getPiece(cRoi);
		for (int l = 0; l < SIZE; l++) {
			for (int c = 0; c < SIZE; c++) {
				if(this.caseVide(l, c)) continue;
				if(damier[l][c].estAdverse(pRoi)
						&& this.damier[l][c].peutAllerEn(cRoi, this)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Copie cet echiquier
	 * @return l'echiquier actuel
	 */
	
	public Echiquier copie() {
		Echiquier copie = new Echiquier();
		for (int l = 0; l < SIZE; l++) {
			for (int c = 0; c < SIZE; c++) {
				if(!caseVide(l, c)) copie.ajouterPiece(damier[l][c].copie());
			}
		}
		return copie;
	}
	
	/**
	 * Determine si une piece a� une coordonnee indiquee est a deux cases d'un roi
	 * @param cPieceDepart la coordonnée de la piece pouvant
	 *  etre à proximite d'un roi
	 * @return true si vrai
	 */
	public boolean aCoteDePieceSensible(Coord cPieceDepart, IPiece p) {
		for (int cpt = 0; cpt < Direction.NB_DIR; cpt ++) {
			if(!p.directionPossible(Direction.values()[cpt])) continue;
			Coord pivot = cPieceDepart.copie();
			pivot.prendreDirection(Direction.values()[cpt]);
			if (!this.dansLimitesPlateau(pivot)) continue;
			IPiece cible = this.getPiece(pivot);
			if (this.caseVide(pivot.getY(), pivot.getX())) continue;
			if(cible.craintEchec() && cible != p) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Renvoie la piece attaquante d'une autre piece
	 * @param p la piece attaquee
	 * @return la piece attaquante
	 */
	@Override
	public IPiece getAttaquante(IPiece p) {
		for(int l = 0; l < SIZE; l++) {
			for(int c = 0; c < SIZE; c++) {
				if (caseVide(l, c)) continue;
				if(this.damier[l][c].peutAllerEn(p.getCoord(), this)
						&& damier[l][c].estAdverse(p))
					return this.damier[l][c];
			}
		}
		return null;
	}

	/**
	 * Determine si une piece pouvant attaquer le roi de couleur precisee
	 * peut se faire attaquer dans son déplacement vers le roi
	 * @param pAttaquante la piece pouvant attaquer le roi
	 * @param enCours la couleur du roi
	 * @return true si vrai
	 */
	private boolean echecCouvrable(IPiece pAttaquante, Couleur enCours) {
		Coord cRoi = getCoordRoi(enCours);
		IPiece pSosie = pAttaquante;
		Direction chemin = pAttaquante.getCoord().trouverDirectionVers(cRoi);
		while(!pSosie.getCoord().estEgaleA(cRoi)) {
			pSosie.faireUnPas(chemin);
			if(!(getDefenseuse(pSosie) == null))
				return true;
		}
		return false;
	}
	/**
	 * Recupere une piece pouvant couvrir son roi d'une piece attaquante
	 * @param att la piece attaquante
	 * @return la piece couvrant l'echec
	 */
	
	private IPiece getDefenseuse(IPiece att) {
		for (int l= 0; l < SIZE; l++) {
			for(int c = 0; c < SIZE; c++) {
				if (caseVide(l, c) || this.damier[l][c].craintEchec()) 
					continue;
				if(this.damier[l][c].peutAllerEn(att.getCoord(), this)
						&& damier[l][c].estAdverse(att))
					return this.damier[l][c];
			}
		}
		return null;
	}
	
	/**
	 * Determine si le chemin est libre vers une direction
	 * @param cArr la coordonnée d'arrivee
	 * @param d la direction a� emprunter
	 * @param p la piece
	 * @return true si toutes les cases sont vides sur son chemin
	 */
	public boolean cheminLibreVers(Coord cArr, Direction d, IPiece p) {
		int nbPas = p.getCoord().getNbPasVers(cArr, d);
		if(nbPas > p.getNbPasMax()) return false;
		Coord coordEnCours = p.getCoord().copie();
		
		for(int i = 0; i < nbPas-1; i++) {
			coordEnCours.prendreDirection(d);
			// Le chemin doit etre compose de cases vides
			if(!this.caseVide(coordEnCours.getY(), coordEnCours.getX()))
				return false;
		}
		return true;
	}
	
	/**
	 * Recuperer les coordonnees du roi du camp du joueur passe en parametre
	 * @param enCours la couleur du roi
	 * @return les coordonnees du roi
	 */
	public Coord getCoordRoi(Couleur enCours) {
		for(int l = 0; l < SIZE; l++) {
			for(int c = 0; c < SIZE; c++) {
				if (caseVide(l, c)) continue;
				if (this.damier[l][c].craintEchec() &&
						this.damier[l][c].getColor() == enCours) {
					return this.damier[l][c].getCoord();
				}
			}
		}
		return null;
	}
	
	// -------------------------------------------------------------------------
	
	/**
	 * Recherche les pieces du joueur de couleur precisee
	 * @param couleur la couleur des pions du joueur
	 * @param pieces le tableau dans lesquel placer les pieces
	 */
	public void rechercherPieces(Couleur couleur, ArrayList<Coord> pieces) {
		for(int l = 0; l < SIZE; l++) {
			for(int c = 0; c < SIZE; c++) {
				Coord test = new Coord(c, l);
				if (caseVide(l, c)) continue;
				if (getPiece(test).getColor() == couleur) {
					pieces.add(test);
				}
			}
		}
	}
	

	/**
	 * Recherche les directions possibles d'une piece sur le plateau
	 * et less case dans des listes
	 * @param departs les departs possibles de la piece
	 * @param arr les arrivees possibles de la piece
	 * @param p la piece
	 */
	@Override
	public void rechercherDirectionsPossibles(ArrayList<Coord> departs, 
			ArrayList<Coord> arr, IPiece p) {
		for (int cpt = 0; cpt < Direction.NB_DIR; cpt ++) {
			Direction dTest = Direction.values()[cpt];
			if(p.directionPossible(dTest)) {
				Coord test = p.getCoord().copie();
				int i = 0;
				boolean possible = false;
				do {
					test.prendreDirection(dTest);
					possible = p.peutAllerEn(test, this);
					if(possible) {
						arr.add(test.copie());
						departs.add(p.getCoord().copie());
					}
					else break;
					i++;
				} while(i < p.getNbPasMax());
			}
		}
	}

	
	
}
