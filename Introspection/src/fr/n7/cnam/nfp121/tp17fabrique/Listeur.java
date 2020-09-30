package fr.n7.cnam.nfp121.tp17fabrique;

import java.lang.reflect.*;

interface Listeur {
	java.util.List<Method> getMethodes(String nomClasse) throws Exception;
}
