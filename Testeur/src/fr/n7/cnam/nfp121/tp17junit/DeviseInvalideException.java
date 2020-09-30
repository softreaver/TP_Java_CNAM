package fr.n7.cnam.nfp121.tp17junit;

/** L'exception DeviseInvalideException indique des devises incompatibles sur
 * des opérations entre monnaies.
 *
 * @author	Xavier Crégut
 * @version	$Revision: 1.1 $
 */
public class DeviseInvalideException extends Exception {

	public DeviseInvalideException(String message) {
		super(message);
	}

}
