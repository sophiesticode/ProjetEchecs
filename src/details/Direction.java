package details;

public enum Direction {
	// trajectoires droites
	NORD(0, -1), 
	NORD_EST(1, -1), 
	EST(1, 0), 
	SUD_EST(1, 1),
	SUD(0, 1), 
	SUD_OUEST(-1, 1),
	OUEST(-1, 0), 	 
	NORD_OUEST(-1, -1),
	// trajectoires en L
	L_NORD_ESTN(1, -2), 
	L_NORD_EST(2, -1), 
	L_SUD_EST(2, 1), 
	L_SUD_ESTS(1, 2),
	L_SUD_OUESTS(-1, 2), 
	L_SUD_OUEST(-2, 1),
	L_NORD_OUEST(-2, -1), 	 
	L_NORD_OUESTN(-1, -2);
	
	// les abscisses et ordonnees
	private final int dx, dy;
	
	// le nombre de directions
	public static final int NB_DIR = 16;
	
	/**
	 * Un connstructeur de Direction
	 * @param dx l'abscisse x
	 * @param dy l'ordonnée y
	 */
	private Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	/**
	 * @return l'abscisse x de la direction
	 */
	public int getDx() {
		return dx;
	}
	
	/**
	 * @return l'ordonnee y de la direction
	 */
	public int getDy() {
		return dy;
	}
	
}
