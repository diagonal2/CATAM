package project;

import java.util.ArrayList;

public class Q6 {
	
	public static boolean isAmbiguous(BQF f) {
		
		boolean tmp1 = (f.getB() == 0);
		boolean tmp2 = (f.getA() == f.getB() && f.getA() < f.getC());
		boolean tmp3 = (f.getA() == f.getC());
		return tmp1 || tmp2 || tmp3;
		
	}
	
	public static ArrayList<BQF> reducedPrimAmbiguousBQFsByDisc(int d) {
		
		ArrayList<BQF> fs = BQF.reducedPrimBQFsByDisc(d);
		for (int i = 0; i < fs.size(); i++) {
			if (!isAmbiguous(fs.get(i))) {
				fs.remove(i);
				i--;
			}
		}
		return fs;
		
	}
	
	public static void main(String[] args) {
		
		int[] ds = { -231, -663, -1092 };
		
		for (int d : ds) {
			System.out.print(d + ": ");
			ArrayList<BQF> fs = reducedPrimAmbiguousBQFsByDisc(d);
			for (BQF f : fs) System.out.print(f.toString() + ", ");
			System.out.println();
		}
		
	}
	
}
