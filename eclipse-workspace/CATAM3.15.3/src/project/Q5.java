package project;

import java.util.ArrayList;

public class Q5 {
	
	public static void main(String[] args) throws NotPositiveFormException {
		
		int[] ds = { -39, -55, -56, -63, -68, -80, -84, -95, -96, -111, -120, -975, -2331, -19427, -63499 };
		
		for (int d : ds) {
			
			ArrayList<BQF> fs = BQF.reducedPrimBQFsByDisc(d);
			
			System.out.print(d + ": " + fs.size() + ": ");
			for (BQF f : fs) {
				// System.out.print(f.toString() + " ");
				BQF m = new BQF(f);
				boolean isIdentity = (m.getA() == 1 && (m.getB() == 0 || m.getB() == 1));
				int order = 1;
				while (!isIdentity) {
					m = BQF.reduce(BQF.compose(m, f));
					isIdentity = (m.getA() == 1 && (m.getB() == 0 || m.getB() == 1));
					order++;
				}
				System.out.print(order + ", ");
			}
			System.out.println();
			
		}
		
	}
	
}
