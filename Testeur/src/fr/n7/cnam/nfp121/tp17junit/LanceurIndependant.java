package fr.n7.cnam.nfp121.tp17junit;

import java.lang.reflect.*;
import java.util.*;

/** L'objectif est de faire un lanceur simple sans utiliser toutes les clases
  * de notre architecture JUnit.   Il permet juste de valider la compréhension
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
				System.out.println(" Problème : " + e);
			}
		}

		// Afficher les erreurs
		for (Throwable e : erreurs) {
			System.out.println();
			e.printStackTrace();
		}

		// Afficher un bilan
		System.out.println();
		System.out.printf("%d tests lancés dont %d échecs et %d erreurs.\n",
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
		// Récupérer la classe
		Class<?> testedClass = Class.forName(nomClasse);

		// Instancier l'objet qui sera le récepteur des tests
		Object object = testedClass.getConstructor().newInstance();
		
		// Vérifier que la classe est bien un test élémentaire
		try {
			TestElementaire.class.cast(object);
		} catch (Exception e) {
			throw new NotTestableClassException();
		}
		
		// Récupérer les méthodes de tests
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
		
		// Executer la méthode de préparation
		testedClass.getMethod("preparer").invoke(object);

		// Exécuter les méthods de test
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
		
		// Executer la méthode nettoyer
		testedClass.getMethod("nettoyer").invoke(object);
	}

	public static void main(String... args) {
		LanceurIndependant lanceur = new LanceurIndependant(args);
	}

}
