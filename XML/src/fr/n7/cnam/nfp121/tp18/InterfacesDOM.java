package fr.n7.cnam.nfp121.tp18;

/**
 * InterfacesDOM : Analyser un fichier de description des interfaces r�seau
 */

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;

public class InterfacesDOM {

	/** Afficher le nombre d'interfaces automatiques. */
	public static void afficherNombreInterfacesAutomatiques(Document doc) {
	    // Principe : r�cup�rer les << name >>, puis les compter
	    System.out.println("Nb interfaces automatiques = "
	            + doc.getElementsByTagName("name").getLength());
	}

	/** Afficher le nombre d'interfaces sp�cifi�es. */
	public static void afficherNombreInterfacesSpecifiees(Document doc) {
		// A COMPLETER...
	}

	/** Afficher les noms des interfaces automatiques. */
	public static void afficherNomsInterfacesAutomatiques(Document doc) {
		// A COMPLETER...
	}

	/**
	 * Afficher les noms des interfaces qui utilisent la passerelle
	 * 147.127.18.200
	 */
	public static void afficherNomsInterfacesPasserelle(Document doc) {
		// A COMPLETER...
	}

	/** Afficher les noms des interfaces qui sont d�finies mais non automatiques */
	public static void afficherInterfacesDefiniesNonAutomatiques(Document doc) {
		// A COMPLETER...
	}

	/**
	 * M�thode principale.
	 * @param args le nom du fichier xml et les options
	 */
	public static void main(String[] args) {
	    // Instancier la << factory >>
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	    try {
	        boolean erreur = args.length < 2;
	        if (!erreur) {
	            String fichier = args[0];

	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document document = builder.parse(fichier);

	            for (int indice = 1; indice < args.length; indice++) {
	                String option = args[indice];
	                if (option.equals("nbInterfacesAutomatiques")) {
	                    afficherNombreInterfacesAutomatiques(document);
	                } else if (option.equals("nbInterfacesSp�cifi�es")) {
	                    afficherNombreInterfacesSpecifiees(document);
	                } else if (option.equals("nomInterfacesAutomatiques")) {
	                    afficherNomsInterfacesAutomatiques(document);
	                } else if (option.equals("nomInterfacesPasserelle")) {
	                    afficherNomsInterfacesPasserelle(document);
	                } else if (option.equals("interfacesDefiniesNonAutomatiques")) {
	                    afficherInterfacesDefiniesNonAutomatiques(document);
	                } else if (option.equals("tout")) {
	                    afficherNombreInterfacesAutomatiques(document);
	                    afficherNombreInterfacesSpecifiees(document);
	                    afficherNomsInterfacesAutomatiques(document);
	                    afficherNomsInterfacesPasserelle(document);
	                    afficherInterfacesDefiniesNonAutomatiques(document);
	                } else {
	                    System.out.println("Information inconnue : " + option);
	                    erreur = true;
	                }

	            }
	        }

	        if (erreur) {
	            System.out.println();
	            System.out.println("Usage : java " + "InterfacesDOM"
	                    + " fichier.xml information...");
	            System.out.println();
	            System.out.println("Information : ");
	            System.out.println("   nbInterfacesAutomatiques : "
						+ "nombre d'interfaces automatiques");
	            System.out.println("   nbInterfacesSp�cifi�es : "
						+ "nombre d'interfaces sp�cifi�es");
	            System.out.println("   nomInterfacesAutomatiques : "
						+ "noms des interfaces automatiques");
	            System.out.println("   nomInterfacesPasserelle : "
						+ "noms des interfaces qui utilisent la passerelle "
						+ "147.127.18.200");
	            System.out.println("   interfacesDefiniesNonAutomatiques : "
						+ "noms des interfaces d�finies mais non automatiques");
	        }
	    } catch (SAXParseException e) {
	        System.out.println("Erreur au niveau SAX (DOM utilise SAX) : " + e);
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
