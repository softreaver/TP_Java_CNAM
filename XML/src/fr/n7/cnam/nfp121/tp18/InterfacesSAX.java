package fr.n7.cnam.nfp121.tp18;

import javax.xml.parsers.*; // SAXParserFactory, SAXParser
import org.xml.sax.*; // XMLReader
import org.xml.sax.helpers.*; // XMLReader
import java.util.*;

/**
 * Calculer le nombre d'interfaces automatiques.
 * 
 * Dans cette version, on calcule le nombre d'interfaces dans un attribut de la
 * classe qui sera ensuite �tre exploit� par le reste de l'application.
 */
class NombreInterfacesAutomatiques extends DefaultHandler {

	private int nombreInterfaces;

	public int getNombreInterfaces() {
	    return nombreInterfaces;
	}

	public void startDocument() {
	    nombreInterfaces = 0;
	}

	public void startElement(String uriEspaceNom, String nom,
	        String nomQualifi�, Attributes attributs) throws SAXException {
	    if (nomQualifi�.equals("auto")) {
	        nombreInterfaces++;
	    }
	}
}

// //////////////////////////////////////////////////////////////////////////////

/** Calculer le nombre d'interfaces sp�cifi�es. */
class NombreInterfacesSpecifiees extends DefaultHandler {
		// A COMPLETER...
}

// //////////////////////////////////////////////////////////////////////////////

/** Constuit la liste des noms des interfaces automatiques. */
class NomsInterfacesAutomatiques extends DefaultHandler {
		// A COMPLETER...
}

// //////////////////////////////////////////////////////////////////////////////

/**
 * Constuit la liste des noms des interfaces qui utilisent la passerelle
 * 147.127.18.200
 * 
 * Obtenu � partir du "diagramme �tats - transitions"
 */
class NomsInterfacesPasserelle extends DefaultHandler {
		// A COMPLETER...
}

// //////////////////////////////////////////////////////////////////////////////

/**
 * Constuit la liste des noms des interfaces qui sont d�finies mais non
 * automatiques
 */
class InterfacesDefiniesNonAutomatiques extends DefaultHandler {
		// A COMPLETER...
}

// //////////////////////////////////////////////////////////////////////////////

/**
 * InterfacesSAX :  Analyser un fichier de description des interfaces r�seau
 */
public class InterfacesSAX {

	public static XMLReader creerXMLReader() throws SAXException,
	        ParserConfigurationException, java.io.IOException {
	    SAXParserFactory spf = SAXParserFactory.newInstance();
	    XMLReader xmlReader = spf.newSAXParser().getXMLReader();
	    xmlReader.setFeature("http://xml.org/sax/features/validation", true);
	    return xmlReader;
	}

	public static void analyserXML(String fichier, DefaultHandler handler)
	        throws SAXException, ParserConfigurationException,
	        java.io.IOException {
	    XMLReader xmlReader = creerXMLReader();
	    xmlReader.setContentHandler(handler);
	    xmlReader.parse(fichier);
	}

	/** Afficher le nombre d'interfaces automatiques. */
	public static void afficherNombreInterfacesAutomatiques(String fichier)
	        throws SAXException, ParserConfigurationException,
	        java.io.IOException {
	    NombreInterfacesAutomatiques handler = new NombreInterfacesAutomatiques();
	    analyserXML(fichier, handler);
	    System.out.println("Nb interfaces automatiques = "
	            + handler.getNombreInterfaces());
	}

	/** Afficher le nombre d'interfaces sp�cifi�es. */
	public static void afficherNombreInterfacesSpecifiees(String fichier)
	        throws SAXException, ParserConfigurationException,
	        java.io.IOException {
	    NombreInterfacesSpecifiees handler = new NombreInterfacesSpecifiees();
	    analyserXML(fichier, handler);
	    System.out.println("Nb interfaces automatiques = "
	            + handler.getNombreInterfaces());
	}

	/** Afficher les noms des interfaces automatiques. */
	public static void afficherNomsInterfacesAutomatiques(String fichier)
	        throws SAXException, ParserConfigurationException,
	        java.io.IOException {
	    NomsInterfacesAutomatiques handler = new NomsInterfacesAutomatiques();
	    analyserXML(fichier, handler);
		// A COMPLETER...
	}

	/**
	 * Afficher les noms des interfaces qui utilisent la passerelle
	 * 147.127.18.200
	 */
	public static void afficherNomsInterfacesPasserelle(String fichier)
	        throws SAXException, ParserConfigurationException,
	        java.io.IOException {
	    NomsInterfacesPasserelle handler = new NomsInterfacesPasserelle();
	    analyserXML(fichier, handler);
		// A COMPLETER...
	}

	/** Afficher les noms des interfaces qui sont d�finies mais non automatiques */
	public static void afficherInterfacesDefiniesNonAutomatiques(String fichier)
	        throws SAXException, ParserConfigurationException,
	        java.io.IOException {
	    InterfacesDefiniesNonAutomatiques handler = new InterfacesDefiniesNonAutomatiques();
	    analyserXML(fichier, handler);
		// A COMPLETER...
	}

	/*
	 * M�thode principale.
	 * @param args le nom du fichier xml et les options
	 */
	public static void main(String[] args) {
	    try {
	        boolean erreur = args.length < 2;
	        if (!erreur) {
	            String fichier = args[0];

	            for (int indice = 1; indice < args.length; indice++) {
	                String option = args[indice];
	                if (option.equals("nbInterfacesAutomatiques")) {
	                    afficherNombreInterfacesAutomatiques(fichier);
	                } else if (option.equals("nbInterfacesSp�cifi�es")) {
	                    afficherNombreInterfacesSpecifiees(fichier);
	                } else if (option.equals("nomInterfacesAutomatiques")) {
	                    afficherNomsInterfacesAutomatiques(fichier);
	                } else if (option.equals("nomInterfacesPasserelle")) {
	                    afficherNomsInterfacesPasserelle(fichier);
	                } else if (option.equals("interfacesDefiniesNonAutomatiques")) {
	                    afficherInterfacesDefiniesNonAutomatiques(fichier);
	                } else if (option.equals("tout")) {
	                    afficherNombreInterfacesAutomatiques(fichier);
	                    afficherNombreInterfacesSpecifiees(fichier);
	                    afficherNomsInterfacesAutomatiques(fichier);
	                    afficherNomsInterfacesPasserelle(fichier);
	                    afficherInterfacesDefiniesNonAutomatiques(fichier);
	                } else {
	                    System.out.println("Information inconnue : " + option);
	                    erreur = true;
	                }

	            }
	        }

	        if (erreur) {
	            System.out.println();
	            System.out.println("Usage : java " + "InterfacesSAX"
	                    + " fichier.xml information...");
	            System.out.println();
	            System.out.println("Information : ");
	            System.out
	                    .println("   nbInterfacesAutomatiques : nombre d'interfaces automatiques");
	            System.out
	                    .println("   nbInterfacesSp�cifi�es : nombre d'interfaces sp�cifi�es");
	            System.out
	                    .println("   nomInterfacesAutomatiques : noms des interfaces automatiques");
	            System.out
	                    .println("   nomInterfacesPasserelle : noms des interfaces qui utilisent la passerelle 147.127.18.200");
	            System.out.println("   interfacesDefiniesNonAutomatiques : "
						+ "noms des interfaces d�finies mais non automatiques");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
