package fr.n7.cnam.nfp121.tp17junit;

/** L'exception Echec permet de signaler l'erreur fonctionnelle d'un test.
 * @author	Xavier Crégut
 * @version	$Revision: 1.1 $
 */
public class Echec extends Error {
	public Echec() {
		super("condition non vérifiée");
	}
	public Echec(String message) {
		super(message);
	}
}
