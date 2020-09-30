package fr.n7.cnam.nfp121.tp17junit;

import java.lang.reflect.*;
import java.util.*;

/** L'objectif est de faire un lanceur simple sans utiliser toutes les clases
  * de notre architecture JUnit.   Il permet juste de valider la compr�hension
  * de l'introspection en Java.
  */
public class LanceurIndependant {
	private int nbTestsLances;
	private int nbErreurs;
	private int nbEchecs;
	private List<Throwable> erreurs = new ArrayList<>();

	public LanceurIndependant(String... nomsClasses) {
	    System.out.println();
	    this.nbTestsLances = 0;
	    this.nbErreurs = 0;
	    this.nbEchecs = 0;

		// Lancer les tests pour chaque classe
		for (String nom : nomsClasses) {
			try {
				System.out.print(nom + " : ");
				this.testerUneClasse(nom);
				System.out.println();
			} catch (ClassNotFoundException e) {
				System.out.println(" Classe inconnue !");
			} catch (Exception e) {
				System.out.println(" Probl�me : " + e);
			}
		}

		// Afficher les erreurs
		for (Throwable e : erreurs) {
			System.out.println();
			e.printStackTrace();
		}

		// Afficher un bilan
		System.out.println();
		System.out.printf("%d tests lanc�s dont %d �checs et %d erreurs.\n",
				nbTestsLances, nbEchecs, nbErreurs);
	}


	public int getNbTests() {
		return this.nbTestsLances;
	}


	public int getNbErreurs() {
		return this.nbErreurs;
	}


	public int getNbEchecs() {
		return this.nbEchecs;
	}


	private void testerUneClasse(String nomClasse)
		throws ClassNotFoundException, InstantiationException,
						  IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		// R�cup�rer la classe
		Class<?> testedClass = Class.forName(nomClasse);

		// Instancier l'objet qui sera le r�cepteur des tests
		Object object = testedClass.getConstructor().newInstance();
		
		// V�rifier que la classe est bien un test �l�mentaire
		try {
			TestElementaire.class.cast(object);
		} catch (Exception e) {
			throw new NotTestableClassException();
		}
		
		// R�cup�rer les m�thodes de tests
		ArrayList<Method> testMethodsList = new ArrayList<Method>();
		for (Method method : testedClass.getMethods()) {
			if (
					method.getName().startsWith("test") &&
					method.getParameterCount() == 0 &&
					!Modifier.isStatic(method.getModifiers())
				) {
				testMethodsList.add(method);
			}
		}
		
		// Executer la m�thode de pr�paration
		testedClass.getMethod("preparer").invoke(object);

		// Ex�cuter les m�thods de test
		for (Method testMethod : testMethodsList) {
			try {
				this.nbTestsLances++;
				testMethod.invoke(object);
			} catch (InvocationTargetException e) {
				try {
					throw e.getCause();
				} catch (Echec echec) {
					this.nbEchecs++;
					System.out.println("[TEST FAILED] : " + testMethod.getName() + " : " + echec.getMessage());
				} catch (Throwable autre) {
					this.nbErreurs++;
					autre.printStackTrace();
				}
			}
		}
		
		// Executer la m�thode nettoyer
		testedClass.getMethod("nettoyer").invoke(object);
	}

	public static void main(String... args) {
		LanceurIndependant lanceur = new LanceurIndependant(args);
	}

}
