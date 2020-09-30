package fr.n7.cnam.nfp121.tp17fabrique;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ListeurFabriquesStatiques implements Listeur {

	@Override
	public List<Method> getMethodes(String nomClasse) throws Exception {
		List<Method> methodsList = new ArrayList<Method>();
		Class<?> theClass = Class.forName(nomClasse);
		for (Method method : theClass.getDeclaredMethods()) {
			if (
					method.getReturnType() == theClass &&
					!this.contains(method.getParameterTypes(), theClass) &&
					Modifier.isStatic(method.getModifiers())
			)
			methodsList.add(method);
		}
		return methodsList;
	}
	
	private boolean contains(Class<?>[] parameterTypes, Class<?> subject) {
		boolean contain = false;
		for (Class<?> typeClass : parameterTypes) {
			if (typeClass == subject) {
				contain = true;
				break;
			}
		}
		return contain;
	}

}

//Modifier.isStatic(theClass.getMethods()[0].getModifiers())
