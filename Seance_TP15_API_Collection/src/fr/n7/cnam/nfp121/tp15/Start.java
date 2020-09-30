package fr.n7.cnam.nfp121.tp15;

import java.lang.reflect.Method;

public class Start {

	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println(args.length);
		if (args.length > 0) {
			String className = args[0];
			Class<?> myClass = Class.forName(className);
			Method[] methodsList = myClass.getDeclaredMethods();
			for (Method method : methodsList) {
				if (method.getAnnotatedParameterTypes().length == 2) {
					System.out.println(method.getName());
				}
			}
		}
	}
}
