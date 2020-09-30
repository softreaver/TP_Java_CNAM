package fr.n7.cnam.nfp121.tp17junit;

/** L'exception Echec permet de signaler l'erreur fonctionnelle d'un test.
 * @author	Xavier Cr�gut
 * @version	$Revision: 1.1 $
 */
public class Echec extends Error {
	public Echec() {
		super("condition non v�rifi�e");
	}
	public Echec(String message) {
		super(message);
	}
}
