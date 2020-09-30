package fr.n7.cnam.nfp121.tp17fabrique;

import java.util.*;

class Main {
	public static String toString(List<?> liste) {
		String resultat = "";
		for (Object o : liste) {
			resultat += "\n - " + o;
		}
		return resultat;
	}

	public static void main(String[] args) throws Exception {
		Listeur listeur = new ListeurFabriquesStatiques();
		String nom = args.length > 0 ?  args[0] : "fr.n7.cnam.nfp121.tp17fabrique.C";
		System.out.println("Fabriques statiques de " + nom + " : "
				+ toString(listeur.getMethodes(nom)));
	}
}
