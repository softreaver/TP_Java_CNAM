package fr.n7.cnam.nfp121.tp18;

import java.io.FileOutputStream;

import org.jdom2.*;
import org.jdom2.output.*;

/** Construire l'exemple de fichier /etc/network/interfaces donné dans le sujet.
  *
  * @author	Xavier Crégut
  * @version	$Revision$
  */
public class ConstruireInterfacesSujet {

	public static void main(String[] args) throws java.io.IOException {
		// Construire le document
		Element racine = new Element("interfaces");

		// auto lo
		Element autoElement = new Element("auto");
		Element nameForAutoElement = new Element("name");
		nameForAutoElement.setAttribute("value", "lo");
		autoElement.addContent(nameForAutoElement);
		racine.addContent(autoElement);

		Document document = new Document(racine, new DocType("interfaces",
					"interfaces.dtd"));

		// Afficher le document
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		FileOutputStream out = new FileOutputStream("./interfaces.xml");
		sortie.output(document, out);
		out.close();
	}

}
