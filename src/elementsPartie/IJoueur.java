package elementsPartie;

import details.Couleur;

public interface IJoueur {
	public void jouerUnTour(IPlateauJeu e);
	public Couleur getColor();
	public boolean peutSaisir();
}
