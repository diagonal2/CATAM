package project;

import java.util.ArrayList;

public class Q1 {
	
	public static void main(String[] args) {
		
		for (int d = -32; d < 0; d++) {
			
			if (d % 4 == 0 || d % 4 == -3) {
				
				System.out.print("d = " + d + ": ");
				
				ArrayList<BQF> fs = BQF.reducedBQFsByDisc(d);
				for (BQF f : fs) {
					System.out.print(f.toString() + " ");
					if (f.isPrimitive()) System.out.print("primitive, ");
					else System.out.print("not primitive, ");
				}
				
				System.out.println();
				
			}
			
		}
		
	}
	
}
