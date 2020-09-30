package fr.n7.cnam.nfp121.tp15;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Annuaire {
	private List<String> listePersonnes;
	private List<String> listeBureaux;
	private final TreeMap<String, ArrayList<String>> occupations;
	
	public Annuaire() {
		listePersonnes = new ArrayList<String>();
		listeBureaux = new ArrayList<String>();
		occupations = new TreeMap<String, ArrayList<String>>();
	}
	
	public void enregistrerArrivee(String personneName, String bureauName) {
		if (personneName == null || bureauName == null) {
			throw new NullPointerException();
		}
		if (listePersonnes.contains(personneName)) {
			throw new DejaPresentException();
		} else {
			listePersonnes.add(personneName);
			if ( !listeBureaux.contains(bureauName) ) {
				listeBureaux.add(bureauName);
				occupations.put(bureauName, new ArrayList<String>());
			}
			occupations.get(bureauName).add(personneName);
		}
	}
	
	public String bureau(String personneName) {
		if (personneName == null) {
			throw new NullPointerException();
		}
		if ( !listePersonnes.contains(personneName) ) {
			throw new PersonnelInconnuException();
		}
		String bureauFoundOrNull = null;
		for (java.util.Map.Entry<String, ArrayList<String>> affectation : occupations.entrySet()) {
			if (affectation.getValue().contains(personneName)) {
				bureauFoundOrNull = affectation.getKey();
				break;
			}
		}
		return bureauFoundOrNull;
	}
	
	public ArrayList<String> personnels() {
		return (ArrayList<String>) listePersonnes;
	}
	
	public ArrayList<String> personnels(String bureauName) {
		ArrayList<String> listToReturnArrayList = occupations.get(bureauName);
		return listToReturnArrayList != null ? listToReturnArrayList : new ArrayList<String>();
	}
	
	public ArrayList<String> bureaux() {
		return (ArrayList<String>) listeBureaux;
	}
	
	public TreeMap<String, ArrayList<String>> occupationBureaux() {
		return occupations;
	}
	
	public void enregistrerDepart(String personneName) {
		if (personneName == null) {
			throw new NullPointerException();
		}
		int indexOfPersonne = listePersonnes.indexOf(personneName);
		if ( indexOfPersonne == -1 ) {
			throw new PersonnelInconnuException();
		} else {		
			List<String>personnelDansBureauList = occupations.get(bureau(personneName));
			int indexPersonneAEnlever = personnelDansBureauList.indexOf(personneName);
			personnelDansBureauList.remove(indexPersonneAEnlever);
			listePersonnes.remove(indexOfPersonne);
		}
	}
	
	public void modifierBureau(String personneName, String bureauName) {
		if (personneName == null || bureauName == null) {
			throw new NullPointerException();
		}
		int indexOfPersonne = listePersonnes.indexOf(personneName);
		if ( indexOfPersonne == -1 ) {
			throw new PersonnelInconnuException();
		}
		
		String ancienBureau = bureau(personneName);
		occupations.get(ancienBureau).remove(indexOfPersonne);
		
		if ( !listeBureaux.contains(bureauName) ) {
			listeBureaux.add(bureauName);
			occupations.put(bureauName, new ArrayList<String>());
		}
		occupations.get(bureauName).add(personneName);
	}
	
}
