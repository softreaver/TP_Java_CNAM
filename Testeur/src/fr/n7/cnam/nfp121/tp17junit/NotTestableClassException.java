package fr.n7.cnam.nfp121.tp17junit;

public class NotTestableClassException extends Error {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotTestableClassException() {
		super("Une class de test doit hériter de TestElementaire");
	}
}
