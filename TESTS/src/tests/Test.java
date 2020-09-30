package tests;

public class Test {

	static enum e {
		g,
		mg,
		L,
		mL
	}
	
	public static void main(String[] args) {
		e e1 = e.L;
		e e2 = e.L;
		e e3 = e1.mL;
		
		if (e1 == e2) {
			System.out.println("TRUE");
		} else {
			System.out.println("FALSE");
		}
		
		if (e1 == e3) {
			System.out.println("TRUE");
		} else {
			System.out.println("FALSE");
		}
	}
}
